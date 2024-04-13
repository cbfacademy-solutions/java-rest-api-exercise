package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Service class to manage IOU objects using in-memory List-based storage.
 */
@Service
public class IOUService {

    private final IOURepository repository;

    public IOUService(IOURepository repository) {
        this.repository = repository;
    }

    public List<IOU> getAllIOUs() {
        return repository.findAll();
    }

    public IOU getIOU(UUID id) throws NoSuchElementException {
        return repository.findById(id).orElseThrow();
    }

    public IOU createIOU(IOU iou) {
        return repository.save(iou);
    }

    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        IOU iou = repository.findById(id).orElseThrow();

        iou.setBorrower(updatedIOU.getBorrower());
        iou.setLender(updatedIOU.getLender());
        iou.setAmount(updatedIOU.getAmount());

        return repository.save(iou);
    }

    public void deleteIOU(UUID id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        }
    }
}
