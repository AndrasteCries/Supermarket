package com.supermarket.supermarket.service.impl;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Warehouse;
import com.supermarket.supermarket.repository.WarehouseRepository;
import com.supermarket.supermarket.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseProductRepository;

    @Autowired
    public WarehouseServiceImpl(WarehouseRepository warehouseProductRepository) {
        this.warehouseProductRepository = warehouseProductRepository;
    }

    @Override
    public List<Warehouse> getAllProducts() {
        return warehouseProductRepository.findAll();
    }

    @Override
    public Warehouse getProductById(Long id) {
        return warehouseProductRepository.findById(id).orElse(null);
    }

    @Override
    public void addProductToWarehouse(Product product, LocalDate arriveDate, BigDecimal count) {
        Warehouse existingProduct = warehouseProductRepository.findByProductIdAndExpiryDate(
                product.getId(), arriveDate.plusDays(product.getExpirationDate().longValue()));

        if (existingProduct != null) {
            existingProduct.setCount(existingProduct.getCount().add(count));
            warehouseProductRepository.save(existingProduct);
        } else {
            Warehouse warehouse = new Warehouse();
            warehouse.setProduct(product);
            warehouse.setExpiryDate(arriveDate.plusDays(product.getExpirationDate().longValue()));
            warehouse.setCount(count);
            warehouseProductRepository.save(warehouse);
        }
    }

    // todo no need
//    @Override
//    public void updateProduct(Product product) {
//        warehouseProductRepository.save(product);
//    }

    @Override
    public void deleteProduct(Long id) {
        warehouseProductRepository.deleteById(id);
    }


}
