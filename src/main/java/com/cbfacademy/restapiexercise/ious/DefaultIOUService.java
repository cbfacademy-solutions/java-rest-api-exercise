package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Service;

import com.cbfacademy.restapiexercise.core.PersistenceException;

import java.util.List;
import java.util.UUID;

/**
 * Service class to manage IOU objects.
 */
@Service
public class DefaultIOUService implements IOUService {
    private final IOURepository repository;

    public DefaultIOUService(IOURepository iouRepository) {
        this.repository = iouRepository;
    }

    @Override
    public List<IOU> getAllIOUs() {
        try {
            return repository.retrieveAll();
        } catch (PersistenceException | IllegalArgumentException exception) {
            throw new IOUServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public IOU getIOU(UUID id) {
        try {
            IOU currentIOU = repository.retrieve(id);

            if (currentIOU != null) {
                return currentIOU;
            }

            throw new IOUNotFoundException("IOU not found");
        } catch (PersistenceException | IllegalArgumentException exception) {
            throw new IOUServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public IOU createIOU(IOU iou) {
        try {
            return repository.create(iou);
        } catch (PersistenceException | IllegalArgumentException exception) {
            throw new IOUServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public IOU updateIOU(UUID id, IOU updatedIOU) {
        try {
            IOU currentIOU = getIOU(id);

            currentIOU.setAmount(updatedIOU.getAmount());
            currentIOU.setBorrower(updatedIOU.getBorrower());
            currentIOU.setLender(updatedIOU.getLender());

            return repository.update(currentIOU);
        } catch (PersistenceException | IllegalArgumentException exception) {
            throw new IOUServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public void deleteIOU(UUID id) {
        try {
            repository.delete(getIOU(id));
        } catch (PersistenceException | IllegalArgumentException exception) {
            throw new IOUServiceException(exception.getMessage(), exception);
        }
    }
}
