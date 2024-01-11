package com.serdmannwi.practiceprograms.alulasbakery.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Job;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Product;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class StationResponse {
    @JsonProperty("stationId")
    private String id;
    @JsonProperty("workerId")
    private String workerId;
    @JsonProperty("workerName")
    private String workerName;
    @JsonProperty("stationName")
    private String name;
    @JsonProperty("stationStatus")
    private int status;
    @JsonProperty("currentProduct")
    private ProductResponse currentProduct;
    @JsonProperty("isRunning")
    private boolean isRunning;

    public StationResponse() {}

    public StationResponse(String id, String name, String workerId, String workerName, int status,
                           Product currentProduct, AtomicBoolean isPaused) {
        this.id = id;
        this.name = name;
        this.workerId = "";
        this.workerName = "";
        this.status = 0;
        this.currentProduct = productToResponse(currentProduct);
        this.isRunning = !isPaused.get();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean getIsRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public ProductResponse getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(ProductResponse currentProduct) {
        this.currentProduct = currentProduct;
    }

    public ProductResponse productToResponse(Product product) {
        if (product != null) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setFinished(product.isFinished());
            productResponse.setTimeForJob(product.getTimeForJob());

            return productResponse;
        }
        return null;
    }
}
