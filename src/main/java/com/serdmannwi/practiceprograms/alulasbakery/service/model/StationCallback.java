package com.serdmannwi.practiceprograms.alulasbakery.service.model;

public interface StationCallback {
    void onProductFinished(int stationId, Product product);
    boolean isNextStationAvailable(int stationid);
}
