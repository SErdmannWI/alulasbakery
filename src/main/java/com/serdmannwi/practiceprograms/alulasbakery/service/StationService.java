package com.serdmannwi.practiceprograms.alulasbakery.service;

import com.serdmannwi.practiceprograms.alulasbakery.constants.ProductConstants;
import com.serdmannwi.practiceprograms.alulasbakery.constants.StationConstants;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Product;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Station;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.StationCallback;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class StationService implements StationCallback {
    private Map<Integer, Station> stations;
    private Deque<Product> productDeque;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public StationService() {
        this.stations = new HashMap<>();
        stations.put(1, StationConstants.STATION_ONE);
        stations.put(2, StationConstants.STATION_TWO);
        stations.put(3, StationConstants.STATION_THREE);
        stations.put(4, StationConstants.STATION_FOUR);
        stations.put(5, StationConstants.STATION_FIVE);
        stations.put(6, StationConstants.STATION_SIX);
        stations.put(7, StationConstants.STATION_SEVEN);
        stations.put(8, StationConstants.STATION_EIGHT);
        stations.put(9, StationConstants.STATION_NINE);
        stations.put(10, StationConstants.STATION_TEN);
        stations.values().forEach(station -> station.setCallback(this));
        productDeque = new ArrayDeque<>();
    }

    /**----------------------------------------- Station Retrieval Methods ------------------------------------------**/

    public Station getStation(int stationNumber) {
        return stations.get(stationNumber);
    }

    public List<Station> getAllStations() {
        return stations.values().stream().toList();
    }

    /**------------------------------------------- Station Action Methods -------------------------------------------**/

    @Override
    public void onProductFinished(int stationId, Product product) {
        System.out.println("Callback received from Station " + stationId + " with product " + product.getName());
        if (stationId < 10) {
            System.out.println("Station Service has recieved completed " + product.getName() + " from Station "
                + stationId);
            Station nextStation = getStation(stationId + 1);

            //Check if next Station is available. If yes, wait 3 seconds to simulate travel from one station to the next
            scheduler.schedule(() -> {
                if (nextStation.isAvailable()) {
                    nextStation.startProductWork(product);
                    processNextProduct();
                } else {
                    Station currentStation = getStation(stationId);
                    currentStation.setCurrentProduct(product);
                }
            }, 3, TimeUnit.SECONDS);

        } else {
            System.out.println("Product has finished.");
        }
    }

    public boolean isNextStationAvailable(int currentStationId) {
        int nextStationId = currentStationId + 1;
        Station nextStation = stations.get(nextStationId);
        return nextStation != null && nextStation.isAvailable();
    }

    /**
     * Will need to update this to pause/ resume stations. For now, this will simply load the first Product into
     * Station 1
     */
    public void startAllStations() {
        System.out.println("Station Service starting processing");
        processNextProduct();
    }

    private void processNextProduct() {
        Product nextProduct = getNextProduct();
        if(nextProduct != null) {
            Station firstStation = stations.get(1);
            System.out.println("Processing product " + nextProduct.getName() + " at station " + firstStation.getName());
            firstStation.startProductWork(nextProduct);
        }
    }

    public void resumeAllStations() {
        stations.values().forEach(Station::resumeCycle);
    }

    public void pauseAllStations() {
        stations.values().forEach(Station::pauseCycle);
    }

    public void stopAllStations() {
        stations.values().forEach(Station::stopCycle);
    }

    public void startIndividualStation(int stationNumber) {
    }

    public void resumeIndividualStation(int stationNumber) {
        stations.get(stationNumber).resumeCycle();
    }

    public void pauseIndividualStation(int stationNumber) {
        stations.get(stationNumber).pauseCycle();
    }

    public void stopIndividualStation(int stationNumber) {
        stations.get(stationNumber).stopCycle();
    }

    /**------------------------------------------- Product Queue Methods --------------------------------------------**/

    public List<Product> viewProductQueue() {
        return new ArrayList<>(productDeque);
    }

    public Product getNextProduct() {
        if (!productDeque.isEmpty()) {
            return productDeque.pop();
        }
        return null;
    }

    public void addBread() {
        productDeque.push(ProductConstants.BREAD_PRODUCT);
        processIfStationOneAvailable();
    }

    public void addMuffin() {
        productDeque.push(ProductConstants.MUFFIN_PRODUCT);
        processIfStationOneAvailable();
    }

    public void addCroissant() {
        productDeque.push(ProductConstants.CROISSANT_PRODUCT);
        processIfStationOneAvailable();
    }

    private void processIfStationOneAvailable() {
        Station firstStation = stations.get(1);
        if (firstStation.isAvailable() && !productDeque.isEmpty()) {
            Product nextProduct = getNextProduct();
            System.out.println("Processing product " + nextProduct.getName() + " at station " + firstStation.getName());
            firstStation.startProductWork(nextProduct);
        }
    }
}
