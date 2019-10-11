package com.sprintell.assetmanagement.services;

import com.sprintell.assetmanagement.models.Asset;
import com.sprintell.assetmanagement.models.Category;
import com.sprintell.assetmanagement.repositories.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    private LocalDate timeNw = LocalDate.now();

    CategoryService categoryService;
    LocationService locationService;
    BrandService brandService;
    DepartmentService departmentService;
    ManufacturerService manufacturerService;
    VendorService vendorService;
    StatusService statusService;
    ModelService modelService;

    @Autowired
    private AssetRepository assetRepository;

    // Add Assets Data
    public Asset addAsset(Asset asset, List<Long> catIds, Long locationId, Long brandId,
                          Long departmentId, Long manufacturerId, Long vendorId, Long statusId,
                          Long modelId) {

        List<Category> assetCategories = new ArrayList<>();
        for (Long catId: catIds){
            assetCategories.add(categoryService.getCategory(catId).get());
        }

        asset.setCategories(assetCategories);
        asset.setLocation(locationService.getLocation(locationId).get());
        asset.setBrand(brandService.getBrand(brandId).get());
        asset.setDepartment(departmentService.getDepartment(departmentId).get());
        asset.setManufacturer(manufacturerService.getManufacturer(manufacturerId).get());
        asset.setVendor(vendorService.getVendor(vendorId).get());
        asset.setStatus(statusService.getStatus(statusId).get());
        asset.setModel(modelService.getModel(modelId).get());


        asset.setRegTime(timeNw);


        return assetRepository.saveAndFlush(asset);
    }

    public List<Asset> addAssets(List<Asset> assets) {

        for (Asset Asset : assets) {
            Asset.setRegTime(timeNw);

        }

        return assetRepository.saveAll(assets);
    }

    // Get Assets Data

    public Optional<Asset> getAsset(Long id) {
        return assetRepository.findById(id);
    }

    /* public List<Asset> getSomeAssets(List<Long> ids) {
        return AssetRepository.findByIdIn(ids);
    }*/

    public List<Asset> getAssets() {

        List<Asset> assets = assetRepository.findAll();

        return assets;
    }


    // Search / Delete / Check Existence of Assets Data

    public List<Asset> search(String searchParam) {

        List<Asset> assets = assetRepository.findBySearchParameter(searchParam);

        return assets;
    }


    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }

    public void deleteAllAssets(){
        assetRepository.deleteAll();
    }


    public boolean checkExistence(Long id) {

        return assetRepository.existsById(id);
    }

    public long countRecord() {
        long count = assetRepository.count();
        return count;
    }

    public Boolean updateAsset(Asset incomingData, Long id, List<Long> catIds, Long locationId, Long brandId,
                               Long departmentId, Long manufacturerId, Long vendorId, Long statusId,
                               Long modelId){
        Optional<Asset> asset = getAsset(id);

        if (asset.isPresent()){
            Asset data = asset.get();
            data.setAssetDescription(incomingData.getAssetDescription());
            data.setAssetNumber(incomingData.getAssetNumber());
            data.setBarCode(incomingData.getBarCode());
            data.setSituation(incomingData.getSituation());
            data.setPicture(incomingData.getPicture());
            data.setSerialNumber(incomingData.getSerialNumber());
            data.setAssetStatus(incomingData.getAssetStatus());
            data.setAssetCondition(incomingData.getAssetCondition());
            data.setNotes(incomingData.getNotes());
            data.setAccountCode(incomingData.getAccountCode());
            data.setPONumber(incomingData.getPONumber());
            data.setPurchasePrice(incomingData.getPurchasePrice());
            data.setCurrentValue(incomingData.getCurrentValue());
            data.setScrapValue(incomingData.getScrapValue());
            data.setPurchasedDate(incomingData.getPurchasedDate());
            data.setInService(incomingData.getInService());
            data.setWarrantyExpires(incomingData.getWarrantyExpires());

            addAsset(data, catIds, locationId, manufacturerId, brandId,
                    modelId, departmentId, vendorId, statusId);

            return true;
        }
        return false;
    }




}
