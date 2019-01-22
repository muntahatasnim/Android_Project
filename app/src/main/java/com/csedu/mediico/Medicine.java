package com.csedu.mediico;


public class Medicine {
    String medId;
    String brandName;
    String genericName;
    String availableIn;
    int stock;

    public Medicine() {

    }

    public Medicine(String medId, String brandName, String genericName, String availableIn, int stock) {
        this.medId = medId;
        this.brandName = brandName;
        this.genericName = genericName;
        this.availableIn = availableIn;
        this.stock = stock;
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
}
