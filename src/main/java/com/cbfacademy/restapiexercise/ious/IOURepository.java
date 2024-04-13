package com.cbfacademy.restapiexercise.ious;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The IOURepository interface defines the operations for managing IOUs in the system.
 * It provides methods for retrieving, saving, updating, and deleting IOU records.
 */
public interface IOURepository extends JpaRepository<IOU, UUID> {

    /**
     * Searches for IOUs where the borrower's name matches the provided string.
     *
     * @param borrower the name of the borrower
     * @return a list of IOUs that match the borrower's name
     */
    List<IOU> findByBorrowerIgnoreCase(String borrower);

    /**
     * Searches for IOUs where the lender's name matches the provided string.
     *
     * @param lender the name of the lender
     * @return a list of IOUs that match the lender's name
     */
    List<IOU> findByLenderIgnoreCase(String lender);

}
