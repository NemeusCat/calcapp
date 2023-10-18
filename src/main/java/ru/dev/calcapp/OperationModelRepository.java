package ru.dev.calcapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationModelRepository extends JpaRepository<OperationModel, Long> {
    @Override
    List<OperationModel> findAll();

    @Override
    List<OperationModel> findAllById(Iterable<Long> longs);
}