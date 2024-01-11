package com.serdmannwi.practiceprograms.alulasbakery.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serdmannwi.practiceprograms.alulasbakery.constants.ProductConstants;
import com.serdmannwi.practiceprograms.alulasbakery.controller.model.ProductResponse;
import com.serdmannwi.practiceprograms.alulasbakery.controller.model.StationResponse;
import com.serdmannwi.practiceprograms.alulasbakery.controller.model.TestResponse;
import com.serdmannwi.practiceprograms.alulasbakery.repository.TestRecord;
import com.serdmannwi.practiceprograms.alulasbakery.service.StationService;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Product;
import com.serdmannwi.practiceprograms.alulasbakery.service.model.Station;
import io.swagger.v3.core.util.Json;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stations")
public class StationController {
    private final StationService stationService;
    private ObjectMapper mapper = new ObjectMapper();

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    /**------------------------------------------- View Station Endpoints -------------------------------------------**/

    @GetMapping("/getStation/all")
    public ResponseEntity<List<StationResponse>> getAllStations() {
        List<StationResponse> stations = stationService.getAllStations().stream()
            .map(this::stationToResponse).toList();
        return ResponseEntity.ok(stations);
    }

    @GetMapping("/getStation/{id}")
    public ResponseEntity<StationResponse> getStation(@PathVariable("id") int stationNumber) {
        return ResponseEntity.ok(stationToResponse(stationService.getStation(stationNumber)));
    }

    /**------------------------------------------ Station Action Endpoints ------------------------------------------**/

    /**
     * Starts Station Cycle
     * @return
     */
    @PutMapping("/stationAction/startAll")
    public ResponseEntity<List<StationResponse>> startAllStations() {
        stationService.startAllStations();
        List<StationResponse> stations = stationService.getAllStations().stream()
            .map(this::stationToResponse).toList();
        return ResponseEntity.ok(stations);
    }

    /**
     * Stops all Station cycles
     * @return
     */
    @PutMapping("/stationAction/stopAll")
    public ResponseEntity<List<StationResponse>> stopAllStations() {
        stationService.stopAllStations();
        List<StationResponse> stations = stationService.getAllStations().stream()
            .map(this::stationToResponse).toList();
        return ResponseEntity.ok(stations);
    }

    /**
     * Marks each Station as Active.
     * @return
     */
    @PutMapping("/stationAction/resumeAll")
    public ResponseEntity<List<StationResponse>> resumeAllStations() {
        stationService.resumeAllStations();
        List<StationResponse> stations = stationService.getAllStations().stream()
            .map(this::stationToResponse).toList();
        return ResponseEntity.ok(stations);
    }

    /**
     * Pauses current processes on each Station
     * @return
     */
    @PutMapping("/stationAction/pauseAll")
    public ResponseEntity<List<StationResponse>> pauseAllStations() {
        stationService.pauseAllStations();
        List<StationResponse> stations = stationService.getAllStations().stream()
            .map(this::stationToResponse).toList();
        return ResponseEntity.ok(stations);
    }


    @PutMapping("/stationAction/startIndividual/{id}")
    public ResponseEntity<StationResponse> startIndividualStation(@PathVariable("id") int stationNumber) {
        stationService.startIndividualStation(stationNumber);
        return ResponseEntity.ok(stationToResponse(stationService.getStation(stationNumber)));
    }

    @PutMapping("/stationAction/resumeIndividual/{id}")
    public ResponseEntity<StationResponse> resumeIndividualStation(@PathVariable("id") int stationNumber) {
        stationService.resumeIndividualStation(stationNumber);
        return ResponseEntity.ok(stationToResponse(stationService.getStation(stationNumber)));
    }

    @PutMapping("/stationAction/pauseIndividual/{id}")
    public ResponseEntity<StationResponse> pauseIndividualStation(@PathVariable("id") int stationNumber) {
        stationService.pauseIndividualStation(stationNumber);
        return ResponseEntity.ok(stationToResponse(stationService.getStation(stationNumber)));
    }

    @PutMapping("/stationAction/stopIndividual/{id}")
    public ResponseEntity<StationResponse> stopIndividualStation(@PathVariable("id") int stationNumber) {
        stationService.stopIndividualStation(stationNumber);
        return ResponseEntity.ok(stationToResponse(stationService.getStation(stationNumber)));
    }

    /**----------------------------------------- Product Queue Interactions -----------------------------------------**/

    @GetMapping("/productQueue/viewProductQueue")
    public ResponseEntity<List<ProductResponse>> getProductQueue() {
        List<Product> products = stationService.viewProductQueue();
        List<ProductResponse> productResponses = products.stream()
            .map(this::productToResponse).toList();

        return ResponseEntity.ok(productResponses);
    }

    @PutMapping("/productQueue/addProduct/{product}")
    public ResponseEntity<?> addProductToQueue(@PathVariable("product") String productId) {
        Product productToAdd;
        if (productId.equals(ProductConstants.BREAD_ID)) {
            stationService.addBread();
            productToAdd = ProductConstants.BREAD_PRODUCT;
        } else if (productId.equals(ProductConstants.CROISSANT_ID)) {
            stationService.addCroissant();
            productToAdd = ProductConstants.CROISSANT_PRODUCT;
        } else if (productId.equals(ProductConstants.MUFFIN_ID)) {
            stationService.addMuffin();
            productToAdd = ProductConstants.MUFFIN_PRODUCT;
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body("Invalid Product");
        }
        return ResponseEntity.ok(productToResponse(productToAdd));
    }



    /**----------------------------------------------- Helper Methods -----------------------------------------------**/
    public StationResponse stationToResponse(Station station) {
        StationResponse stationResponse = new StationResponse();
        stationResponse.setId(station.getId());
        stationResponse.setName(station.getName());
        stationResponse.setStatus(station.getStatus());
        stationResponse.setWorkerId(station.getWorkerId());
        stationResponse.setWorkerName(station.getWorkerName());
        stationResponse.setCurrentProduct(stationResponse.productToResponse(station.getCurrentProduct()));
        stationResponse.setRunning(station.getIsRunning().get());

        return stationResponse;
    }

    public ProductResponse productToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setFinished(product.isFinished());
        productResponse.setTimeForJob(product.getTimeForJob());

        return productResponse;
    }
}
