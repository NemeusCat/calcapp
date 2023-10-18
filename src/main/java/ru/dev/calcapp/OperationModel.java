package ru.dev.calcapp;

import jakarta.persistence.*;
// Сущность , для хранения с помощью ORM
@Entity
@Table(name = "operations")
public class OperationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "operation")
    private String operationName;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
