package com.cbfacademy.restapiexercise.ious;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

@Repository
public class ListIOURepository implements IOURepository {

    private final List<IOU> ious = new ArrayList<>();

    @Override
    public List<IOU> findAll()  {
        return ious;
    }

    @Override
    public Optional<IOU> findById(UUID id) {
        return ious.stream()
                .filter(iou -> iou.getId().equals(id))
                .findFirst();
    }

    @Override
    public void deleteById(UUID id) {
        ious.removeIf(iou -> iou.getId().equals(id));
    }

    @Override
    public <S extends IOU> S save(S entity) {
        UUID id = entity.getId();

        if (findById(id).isPresent()) {
            for (int i = 0; i < ious.size(); i++) {
                IOU iou = ious.get(i);

                if (iou.getId().equals(id)) {
                    ious.set(i, entity);

                    return entity;
                }
            }
        }
        else {
            ious.add(entity);
        }

        throw new IllegalArgumentException("IOU not found.");
    }

    @Override
    public List<IOU> searchByBorrower(String name) {
        List<IOU> matchedIOUs = new ArrayList<IOU>();

        for (int i = 0; i < ious.size(); i++) {
            IOU iou = ious.get(i);

            if (iou.getBorrower().equals(name)) {
                matchedIOUs.add(iou);
            }
        }

        return matchedIOUs;
    }

    @Override
    public List<IOU> searchByLender(String name) {
        List<IOU> matchedIOUs = new ArrayList<IOU>();

        for (int i = 0; i < ious.size(); i++) {
            IOU iou = ious.get(i);

            if (iou.getLender().equals(name)) {
                matchedIOUs.add(iou);
            }
        }

        return matchedIOUs;
    }
    
}
