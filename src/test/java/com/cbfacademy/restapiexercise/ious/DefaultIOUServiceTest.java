package com.cbfacademy.restapiexercise.ious;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultIOUServiceTest {

    @InjectMocks
    private DefaultIOUService service;

    @Mock
    private IOURepository mockRepository;
    private IOU iou1, iou2;

    @BeforeEach
    void setUp() {
        iou1 = new IOU("Borrower1", "Lender1", BigDecimal.valueOf(1000), Instant.now());
        iou2 = new IOU("Borrower2", "Lender2", BigDecimal.valueOf(500), Instant.now());
    }

    @Test
    @DisplayName("Retrieving all IOUs before population should return empty list")
    void getAllIOUsInitiallyEmpty() {
        // Arrange
        when(mockRepository.retrieveAll()).thenReturn(List.of());

        // Act
        List<IOU> ious = service.getAllIOUs();

        // Assert
        assertTrue(ious.isEmpty());
    }

    @Test
    @DisplayName("Retrieving all IOUs after population should return correct values")
    void getAllIOUsAfterAdding() {
        // Arrange
        when(mockRepository.retrieveAll()).thenReturn(List.of(iou1, iou2));

        // Act
        List<IOU> ious = service.getAllIOUs();

        // Assert
        assertEquals(2, ious.size());
        assertTrue(ious.contains(iou1));
        assertTrue(ious.contains(iou2));
    }

    @Test
    @DisplayName("Retrieving IOU should return correct values")
    void getIOUExisting() {
        // Arrange
        when(mockRepository.retrieve(iou1.getId())).thenReturn(iou1);

        // Act
        IOU found = service.getIOU(iou1.getId());

        // Assert
        assertEquals(iou1.getId(), found.getId());
    }

    @Test
    @DisplayName("Retrieving invalid IOU should throw IOUNotFoundException")
    void getIOUNonExisting() {
        // Arrange
        UUID badId = UUID.randomUUID();

        doThrow(new IOUNotFoundException("Cannot retrieve IOU")).when(mockRepository)
                .retrieve(badId);

        // Act
        Executable executable = () -> service.getIOU(badId);

        // Assert
        assertThrows(IOUNotFoundException.class, executable);
    }

    @Test
    @DisplayName("Creating IOU should return correct values")
    void createIOU() {
        // Arrange
        when(mockRepository.create(any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        IOU created = service.createIOU(iou1);

        // Assert
        assertNotNull(created.getId());
        assertEquals(iou1.getBorrower(), created.getBorrower());
        assertEquals(iou1.getLender(), created.getLender());
        assertEquals(iou1.getAmount(), created.getAmount());
    }

    @Test
    @DisplayName("Updating IOU should return updated values")
    void updateIOUExisting() {
        // Arrange
        when(mockRepository.create(any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(mockRepository.update(any(IOU.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        service.createIOU(iou1);
        IOU updatedIOU = new IOU("UpdatedBorrower", "UpdatedLender", BigDecimal.valueOf(1500), Instant.now());
        when(mockRepository.retrieve(iou1.getId())).thenReturn(iou1);
        IOU updated = service.updateIOU(iou1.getId(), updatedIOU);

        // Assert
        assertNotNull(updated);
        assertEquals("UpdatedBorrower", updated.getBorrower());
        assertEquals("UpdatedLender", updated.getLender());
        assertEquals(BigDecimal.valueOf(1500), updated.getAmount());
    }

    @Test
    @DisplayName("Updating invalid IOU should throw IOUNotFoundException")
    void updateIOUNonExisting() {
        // Assert
        assertThrows(IOUNotFoundException.class, () -> service.updateIOU(UUID.randomUUID(),
                new IOU("Borrower3", "Lender3", BigDecimal.valueOf(200), Instant.now())));
    }

    @Test
    @DisplayName("Deleting IOU should execute IOURepository.delete()")
    void deleteIOUExisting() {
        // Arrange
        when(mockRepository.retrieve(iou1.getId())).thenReturn(iou1);
        doAnswer(invocation -> {
            return null;
        }).when(mockRepository).delete(iou1);

        // Act
        service.deleteIOU(iou1.getId());

        // Assert
        verify(mockRepository).delete(iou1);
    }

    @Test
    @DisplayName("Deleting invalid IOU should throw IOUNotFoundException")
    void deleteIOUNonExisting() {
        // Arrange
        UUID badId = UUID.randomUUID();

        // Act
        Executable executable = () -> service.deleteIOU(badId);

        // Assert
        assertThrows(IOUNotFoundException.class, executable);
    }
}
