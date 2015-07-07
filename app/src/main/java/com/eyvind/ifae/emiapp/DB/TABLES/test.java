package com.eyvind.ifae.emiapp.DB.TABLES;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Emilio-Emilio on 7/6/2015.
 */
@Table(name = "test")
public class test extends Model {
    // If name is omitted, then the field name is used.
    @Column(name = "Name")
    public String name;

    @Column(name = "Category")
    public String category;

    public test() {
        super();
    }
}