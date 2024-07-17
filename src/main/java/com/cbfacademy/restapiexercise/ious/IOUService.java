package com.cbfacademy.restapiexercise.ious;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

/**
 * Service class to manage IOU objects.
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

    public IOU createIOU(IOU iou) throws IllegalArgumentException, OptimisticLockingFailureException {
        return repository.save(iou);
    }

    public IOU updateIOU(UUID id, IOU updatedIOU) throws NoSuchElementException {
        IOU iou = repository.findById(id).orElseThrow();

        iou.setBorrower(updatedIOU.getBorrower());
        iou.setLender(updatedIOU.getLender());
        iou.setAmount(updatedIOU.getAmount());

        return repository.save(iou);
    }

    public void deleteIOU(UUID id) throws NoSuchElementException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<IOU> getIOUsByBorrower(String borrower) {
        return repository.findByBorrowerIgnoreCase(borrower);
    }

    public List<IOU> getIOUsByLender(String lender) {
        return repository.findByLenderIgnoreCase(lender);
    }
}
