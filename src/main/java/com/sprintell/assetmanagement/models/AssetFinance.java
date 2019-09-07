package com.sprintell.assetmanagement.models;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class AssetFinance {

    private String accountCode;
    private int PONumber;
    private double purchasePrice;
    private double currentValue;
    private double scrapValue;

    private LocalDate purchasedDate;
    private LocalDate inService;
    private LocalDate warrantyExpires;

    public AssetFinance() {
    }

    public AssetFinance(String accountCode, int PONumber,
                        double purchasePrice,
                        double currentValue, double scrapValue,
                        LocalDate purchasedDate,
                        LocalDate inService,
                        LocalDate warrantyExpires) {
        this.accountCode = accountCode;
        this.PONumber = PONumber;
        this.purchasePrice = purchasePrice;
        this.currentValue = currentValue;
        this.scrapValue = scrapValue;
        this.purchasedDate = purchasedDate;
        this.inService = inService;
        this.warrantyExpires = warrantyExpires;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public int getPONumber() {
        return PONumber;
    }

    public void setPONumber(int PONumber) {
        this.PONumber = PONumber;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(double currentValue) {
        this.currentValue = currentValue;
    }

    public double getScrapValue() {
        return scrapValue;
    }

    public void setScrapValue(double scrapValue) {
        this.scrapValue = scrapValue;
    }

    public LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(LocalDate purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public LocalDate getInService() {
        return inService;
    }

    public void setInService(LocalDate inService) {
        this.inService = inService;
    }

    public LocalDate getWarrantyExpires() {
        return warrantyExpires;
    }

    public void setWarrantyExpires(LocalDate warrantyExpires) {
        this.warrantyExpires = warrantyExpires;
    }
}
