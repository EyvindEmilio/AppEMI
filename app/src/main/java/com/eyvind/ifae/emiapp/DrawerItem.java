package com.eyvind.ifae.emiapp;

/**
 * Created by Emilio-Emilio on 5/8/2015.
 */
public class DrawerItem {

    String ItemName;
    int imgResID;
    public DrawerItem(String ItemName, int imgResID) {
        super();
        this.ItemName = ItemName;
        this.imgResID = imgResID;
    }

    public String getItemName() {
        return ItemName;
    }
    public void setItemName(String itemName) {
        ItemName = itemName;
    }
    public int getImgResID() {
        return imgResID;
    }
    public void setImgResID(int imgResID) {
        this.imgResID = imgResID;
    }
}