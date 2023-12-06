package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service class to manage IOU objects.
 */
@Service
public class ListIOUService implements IOUService {
    private final IOURepository repository;

    public ListIOUService (IOURepository iouRepository) {
        this.repository = iouRepository;
    }

    @Override
    public List<IOU> getAllIOUs() {
        return repository.retrieveAll();
    }

    @Override
    public IOU getIOU(UUID id) {
        return repository.retrieve(id);
    }

    @Override
    public IOU createIOU(IOU iou) {
        return repository.create(iou);
    }

    @Override
    public IOU updateIOU(UUID id, IOU updatedIOU) {
        return repository.update(updatedIOU);
    }

    @Override
    public boolean deleteIOU(UUID id) {
        return repository.delete(id);
    }
}
