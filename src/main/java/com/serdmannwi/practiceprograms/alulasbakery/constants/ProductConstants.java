package com.serdmannwi.practiceprograms.alulasbakery.constants;

import com.serdmannwi.practiceprograms.alulasbakery.service.model.Product;

public class ProductConstants {
    /**------------------------------------------------- Product IDs ------------------------------------------------**/
    public static final String BREAD_ID = "BREAD";
    public static final String MUFFIN_ID = "MUFFIN";
    public static final String CROISSANT_ID= "CROISSANT";

    /**----------------------------------------------- Product Names ------------------------------------------------**/
    public static final String BREAD_NAME = "BREAD";
    public static final String MUFFIN_NAME = "MUFFIN";
    public static final String CROISSANT_NAME = "CROISSANT";

    /**----------------------------------------------- Product Times ------------------------------------------------**/
    public static final int BREAD_WORK_TIME = 10;
    public static final int MUFFIN_WORK_TIME = 15;
    public static final int CROISSANT_WORK_TIME = 20;

    /**------------------------------------------------- Products ---------------------------------------------------**/
    public static final Product BREAD_PRODUCT = new Product(BREAD_NAME, BREAD_ID, BREAD_WORK_TIME);
    public static final Product MUFFIN_PRODUCT = new Product(MUFFIN_NAME, MUFFIN_ID, MUFFIN_WORK_TIME);
    public static final Product CROISSANT_PRODUCT = new Product(CROISSANT_NAME, CROISSANT_ID, CROISSANT_WORK_TIME);
}
