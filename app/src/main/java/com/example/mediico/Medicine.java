package com.example.mediico;

public class Medicine {
    String medId;
    String brandName;
    String genericName;
    String availableIn;
    int stock;
    int price;
    public Medicine()
    {

    }

    public Medicine(String medId, String brandName, String genericName, String availableIn, int stock, int price) {
        this.medId = medId;
        this.brandName = brandName;
        this.genericName = genericName;
        this.availableIn = availableIn;
        this.stock = stock;
        this.price = price;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public void setAvailableIn(String availableIn) {
        this.availableIn = availableIn;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMedId() {
        return medId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getGenericName() {
        return genericName;
    }

    public String getAvailableIn() {
        return availableIn;
    }

    public int getStock() {
        return stock;
    }

    public int getPrice() {
        return price;
    }
}

