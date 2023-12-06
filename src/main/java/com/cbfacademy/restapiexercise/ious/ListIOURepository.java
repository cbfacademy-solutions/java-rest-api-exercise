package com.cbfacademy.restapiexercise.ious;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
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

    public void createIOUs() {
        ious = List.of(
                new IOU("Borrower1", "Lender1", BigDecimal.valueOf(1000), Instant.now()),
                new IOU("Borrower2", "Lender2", BigDecimal.valueOf(500), Instant.now()),
                new IOU("Borrower3", "Lender3", BigDecimal.valueOf(30), Instant.now()));
    }

    @Override
    public List<IOU> retrieveAll() {
        return ious;
    }

    @Override
    public IOU retrieve(UUID id) {
        return ious.stream()
                .filter(iou -> iou.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<IOU> searchByBorrower(String name) {
        return ious.stream().filter(x -> x.getBorrower().startsWith(name)).collect(Collectors.toList());
    }

    @Override
    public List<IOU> searchByLender(String name) {
        return ious.stream().filter(x -> x.getLender().startsWith(name)).collect(Collectors.toList());
    }

    @Override
    public IOU create(IOU iou) {
        ious.add(iou);

        return iou;
    }

    @Override
    public Boolean delete(UUID id) {
        return ious.removeIf(iou -> iou.getId().equals(id));
    }

    @Override
    public IOU update(IOU updatedIOU) {
        for (int i = 0; i < ious.size(); i++) {
            IOU iou = ious.get(i);

            if (iou.getId().equals(updatedIOU.getId())) {
                ious.set(i, updatedIOU);

                return updatedIOU;
            }
        }

        return null;
    }
}
