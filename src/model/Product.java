/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author ACER
 */
public class Product {
    private String id;
    private String name;
    private String brand_id;
    private String category_id;
    private int model_year;
    private double list_price;

    public Product() {
    }

    public Product(String name, String brand_id, String category_id, int model_year, double list_price) {
        this.name = name;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.model_year = model_year;
        this.list_price = list_price;
    }
    
    

    public Product(String id, String name, String brand_id, String category_id, int model_year, double list_price) {
        this.id = id;
        this.name = name;
        this.brand_id = brand_id;
        this.category_id = category_id;
        this.model_year = model_year;
        this.list_price = list_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(String brand_id) {
        this.brand_id = brand_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", brand_id=" + brand_id + ", category_id=" + category_id + ", model_year=" + model_year + ", list_price=" + list_price + '}';
    }
    
    
    
    
    
}
