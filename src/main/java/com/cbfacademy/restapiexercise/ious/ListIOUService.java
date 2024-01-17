package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service class to manage IOU objects using in-memory List-based storage.
 */
@Service
public class ListIOUService implements IOUService {

    private final IOURepository repository;

    public ListIOUService(IOURepository repository) {
        this.repository = repository;
    }

    @Override
    public List<IOU> getAllIOUs() {
        return iterableToList(repository.findAll());
    }

    @Override
    public IOU getIOU(UUID id) {
        Optional<IOU> iou = getValidIOU(id);

        return iou.orElseThrow();
    }

    @Override
    public IOU createIOU(IOU iou) {
        return repository.save(iou);
    }

    @Override
    public IOU updateIOU(UUID id, IOU updatedIOU) {
        Optional<IOU> found = getValidIOU(id);

        IOU iou = found.orElseThrow();
        iou.setBorrower(updatedIOU.getBorrower());
        iou.setLender(updatedIOU.getLender());
        iou.setAmount(updatedIOU.getAmount());

        return repository.save(iou);
    }

    @Override
    public void deleteIOU(UUID id) {
        getValidIOU(id);
        repository.deleteById(id);
    }

    protected <T> List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    protected Optional<IOU> getValidIOU(UUID id) {
        Optional<IOU> found = repository.findById(id);

        if (!found.isPresent()) {
            throw new IllegalArgumentException("IOU not found.");
        }

        return found;
    }
}
