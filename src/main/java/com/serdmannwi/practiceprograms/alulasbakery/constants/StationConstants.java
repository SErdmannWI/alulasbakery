package com.serdmannwi.practiceprograms.alulasbakery.constants;

import com.serdmannwi.practiceprograms.alulasbakery.service.model.Station;

public class StationConstants {
    /**-------------------------------------------- Station Status Codes --------------------------------------------**/
    public static final int STATION_STATUS_WAITING = 0;             //Will display blank with white background
    public static final int STATION_STATUS_WORKING = 1;             //Will display job info with green background
    public static final int STATION_STATUS_WARNING = 2;             //Will display job info with yellow background
    public static final int STATION_STATUS_OVER_TIME = 3;           //Will display job info with red background
    public static final int STATION_STATUS_NEEDS_ATTENTION = 4;     //Will display job info with pulsing yellow to orange backgorund

    /**------------------------------------------------ Station Names -----------------------------------------------**/
    public static final String STATION_ONE_NAME = "Station 1";
    public static final String STATION_TWO_NAME = "Station 2";
    public static final String STATION_THREE_NAME = "Station 3";
    public static final String STATION_FOUR_NAME = "Station 4";
    public static final String STATION_FIVE_NAME = "Station 5";
    public static final String STATION_SIX_NAME = "Station 6";
    public static final String STATION_SEVEN_NAME = "Station 7";
    public static final String STATION_EIGHT_NAME = "Station 8";
    public static final String STATION_NINE_NAME = "Station 9";
    public static final String STATION_TEN_NAME = "Station 10";

    /**------------------------------------------------- Station IDs ------------------------------------------------**/
    public static final String STATION_ONE_ID = "ST01";
    public static final String STATION_TWO_ID = "ST02";
    public static final String STATION_THREE_ID = "ST03";
    public static final String STATION_FOUR_ID = "ST04";
    public static final String STATION_FIVE_ID = "ST05";
    public static final String STATION_SIX_ID = "ST06";
    public static final String STATION_SEVEN_ID = "ST07";
    public static final String STATION_EIGHT_ID = "ST08";
    public static final String STATION_NINE_ID = "ST09";
    public static final String STATION_TEN_ID = "ST10";

    /**----------------------------------------------- Station Numbers ----------------------------------------------**/
    public static final int STATION_ONE_NUMBER = 1;
    public static final int STATION_TWO_NUMBER = 2;
    public static final int STATION_THREE_NUMBER = 3;
    public static final int STATION_FOUR_NUMBER = 4;
    public static final int STATION_FIVE_NUMBER = 5;
    public static final int STATION_SIX_NUMBER = 6;
    public static final int STATION_SEVEN_NUMBER = 7;
    public static final int STATION_EIGHT_NUMBER = 8;
    public static final int STATION_NINE_NUMBER = 9;
    public static final int STATION_TEN_NUMBER = 10;

    /**--------------------------------------------------- Stations --------------------------------------------------**/
    public static final Station STATION_ONE = new Station(STATION_ONE_ID, STATION_ONE_NAME, STATION_ONE_NUMBER);
    public static final Station STATION_TWO = new Station(STATION_TWO_ID, STATION_TWO_NAME, STATION_TWO_NUMBER);
    public static final Station STATION_THREE = new Station(STATION_THREE_ID, STATION_THREE_NAME, STATION_THREE_NUMBER);
    public static final Station STATION_FOUR = new Station(STATION_FOUR_ID, STATION_FOUR_NAME, STATION_FOUR_NUMBER);
    public static final Station STATION_FIVE = new Station(STATION_FIVE_ID, STATION_FIVE_NAME, STATION_FIVE_NUMBER);
    public static final Station STATION_SIX = new Station(STATION_SIX_ID, STATION_SIX_NAME, STATION_SIX_NUMBER);
    public static final Station STATION_SEVEN = new Station(STATION_SEVEN_ID, STATION_SEVEN_NAME, STATION_SEVEN_NUMBER);
    public static final Station STATION_EIGHT = new Station(STATION_EIGHT_ID, STATION_EIGHT_NAME, STATION_EIGHT_NUMBER);
    public static final Station STATION_NINE = new Station(STATION_NINE_ID, STATION_NINE_NAME, STATION_NINE_NUMBER);
    public static final Station STATION_TEN = new Station(STATION_TEN_ID, STATION_TEN_NAME, STATION_TEN_NUMBER);
}
