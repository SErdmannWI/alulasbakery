package com.serdmannwi.practiceprograms.alulasbakery.service.model;

import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Station {

    @Id
    private String id;
    private String workerId;
    private String workerName;
    private String name;
    private int stationNumber;
    private int status;
    private Product currentProduct;
    private final AtomicBoolean isRunning;
    private final ScheduledExecutorService executorService;
    private StationCallback callback;

    public Station(String id, String name, int stationNumber) {
        this.id = id;
        this.name = name;
        this.stationNumber = stationNumber;
        this.workerId = "";
        this.workerName = "";
        this.status = 0;
        this.currentProduct = null;
        this.isRunning = new AtomicBoolean(false);
        executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void setCallback(StationCallback callback) {
        this.callback = callback;
    }

    /**-------------------------------------------- Station Cycle Methods -------------------------------------------**/
    /**
     * Starts a cycle where if the Station has a Product, performs work on that Product for
     * Product.getTimeForJob() seconds
     */
    public void startProductWork(Product product) {
        this.currentProduct = product;
        if (product != null) {
            System.out.println(this.name + " working on " + product.getName() + " for " + product.getTimeForJob()
                + " seconds");
            currentProduct.setFinished(false);
            updateStationStatus();
            executorService.schedule(() -> {
                currentProduct.setFinished(true);
                updateStationStatus();
                //Check if next station is available
                if (callback != null && !callback.isNextStationAvailable(stationNumber)) {
                    startRetryTask(product);
                }
                if (callback != null) {
                    System.out.println("Sending callback");
                    callback.onProductFinished(stationNumber, currentProduct);
                }
                currentProduct = null;
            }, product.getTimeForJob(), TimeUnit.SECONDS);
        }
    }

    public void updateStationStatus() {
        if (currentProduct == null) {
            status = 0;
        } else if (!currentProduct.isFinished()) {
            status = 1;
        } else {
            status = 4;
        }
    }

    private void startRetryTask(Product product) {
        executorService.scheduleWithFixedDelay(() -> {
            if (callback != null && callback.isNextStationAvailable(stationNumber)) {
                callback.onProductFinished(stationNumber, product);
                currentProduct = null; // Clear the product
                executorService.shutdown(); // Stop the retry task
            }
        }, 0, 5, TimeUnit.SECONDS); // Retry every 5 seconds, adjust as needed
    }

    public void pauseCycle() {
        isRunning.set(false);
    }

    public void resumeCycle() {
        isRunning.set(true);
    }

    public void stopCycle() {
        executorService.shutdownNow();
    }

    public boolean isAvailable() {
        return this.currentProduct == null;
    }

    /**--------------------------------------------- Getters and Setters --------------------------------------------**/

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

    public AtomicBoolean getIsRunning() {
        return isRunning;
    }

    public Product getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) {
        this.currentProduct = currentProduct;
    }
}
