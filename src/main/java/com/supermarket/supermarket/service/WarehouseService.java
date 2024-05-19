package com.supermarket.supermarket.service;

import com.supermarket.supermarket.model.Product;
import com.supermarket.supermarket.model.Warehouse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public interface WarehouseService {

    List<Warehouse> getAllProducts();

    Warehouse getProductById(Long id);

    Long getCountProductById(Long id);

    void addProductToWarehouse(Product product, LocalDate arriveDate, BigDecimal count);

    void purchaseByProductId(Long id, long count);

    List<Warehouse> getAllByProductId(Long id);

    //todo no need
//    void updateProduct(Product product);

    void deleteProduct(Long id);
}
