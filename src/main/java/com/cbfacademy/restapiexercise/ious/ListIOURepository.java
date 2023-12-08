package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Repository;

import com.cbfacademy.restapiexercise.core.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service class to manage IOU objects using in-memory List-based storage.
 */
@Repository
public class ListIOURepository implements IOURepository {
    private List<IOU> ious = new ArrayList<IOU>();

    @Override
    public List<IOU> retrieveAll() {
        try {
            return ious;
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot retrieve IOUs");
        }
    }

    @Override
    public IOU retrieve(UUID id) {
        validate(id);

        try {
            return ious.stream()
                    .filter(iou -> iou.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot retrieve IOU");
        }
    }

    @Override
    public List<IOU> searchByBorrower(String name) {
        validate(name);

        try {
            return ious.stream().filter(x -> x.getBorrower().startsWith(name)).collect(Collectors.toList());
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot search IOUs");
        }
    }

    @Override
    public List<IOU> searchByLender(String name) {
        validate(name);

        try {
            return ious.stream().filter(x -> x.getLender().startsWith(name)).collect(Collectors.toList());
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot search IOUs");
        }
    }

    @Override
    public IOU create(IOU iou) {
        validate(iou);

        try {
            ious.add(iou);

            return iou;
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot create IOU");
        }
    }

    @Override
    public void delete(IOU iou) {
        validate(iou);

        try {
            ious.removeIf(currentIou -> iou.getId().equals(currentIou.getId()));
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot delete IOU");
        }
    }

    @Override
    public IOU update(IOU iou) {
        validate(iou);

        try {
            for (int i = 0; i < ious.size(); i++) {
                IOU currentIOU = ious.get(i);

                if (currentIOU.getId().equals(iou.getId())) {
                    ious.set(i, iou);

                    return iou;
                }
            }
        } catch (RuntimeException exception) {
            throw new PersistenceException("Cannot update IOU");
        }

        throw new PersistenceException("Cannot update IOU");
    }

    protected void validate(IOU iou) throws IllegalArgumentException {
        if (iou == null) {
            throw new IllegalArgumentException("IOU cannot be null");
        }
    }

    protected void validate(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    protected void validate(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }
}
