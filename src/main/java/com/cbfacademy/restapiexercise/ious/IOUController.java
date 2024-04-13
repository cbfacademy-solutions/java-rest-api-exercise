package com.cbfacademy.restapiexercise.ious;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Controller class to implement IOU API endpoints.
 */
@RestController
@RequestMapping("/api/ious")
public class IOUController {

    private final IOUService iouService;

    public IOUController(IOUService iouService) {
        this.iouService = iouService;
    }

    /**
     * Retrieve a list of all IOUs.
     *
     * @return A list of all IOUs and HttpStatus OK if successful.
     */
    @GetMapping
    public List<IOU> getAllIOUs() {
        try {
            List<IOU> ious = iouService.getAllIOUs();

            return ious;
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", exception);
        }
    }

    /**
     * Retrieve a specific IOU by its ID.
     *
     * @param id The ID of the IOU to retrieve.
     * @return The requested IOU and HttpStatus OK if found, or HttpStatus NOT_FOUND
     *         if the ID is not found.
     */
    @GetMapping("/{id}")
    public IOU getIOU(@PathVariable UUID id) {
        try {
            IOU iou = iouService.getIOU(id);

            return iou;
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", exception);
        }
    }

    /**
     * Create a new IOU.
     *
     * @param iou The IOU object to create.
     * @return The created IOU and HttpStatus CREATED if successful.
     */
    @PostMapping
    public ResponseEntity<IOU> createIOU(@RequestBody IOU iou) {
        try {
            IOU createdIOU = iouService.createIOU(iou);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdIOU);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", exception);
        }
    }

    /**
     * Update an existing IOU by ID.
     *
     * @param id         The ID of the IOU to update.
     * @param updatedIOU The updated IOU object.
     * @return The updated IOU and HttpStatus OK if successful, or HttpStatus
     *         NOT_FOUND if the ID is not found.
     */
    @PutMapping("/{id}")
    public IOU updateIOU(@PathVariable UUID id, @RequestBody IOU updatedIOU) {
        try {
            IOU iou = iouService.updateIOU(id, updatedIOU);

            return iou;
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", exception);
        }
    }

    /**
     * Delete an IOU by ID.
     *
     * @param id The ID of the IOU to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteIOU(@PathVariable UUID id) {
        try {
            iouService.deleteIOU(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "IOU Not Found", exception);
        } catch (RuntimeException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred", exception);
        }
    }
}
