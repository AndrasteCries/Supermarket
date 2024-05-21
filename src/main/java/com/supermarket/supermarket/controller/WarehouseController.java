package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.model.Warehouse;
import com.supermarket.supermarket.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public String getWarehouse(Model model){
        model.addAttribute("products", warehouseService.findAllWithNonExpiredAndPositiveCount());
        return "warehouse/warehouse";
    }
    @GetMapping("/util")
    public String getNeedToRemove(Model model){
        List<Warehouse> allProducts = warehouseService.findAllWithMustExpired();

        model.addAttribute("products", allProducts);
        return "warehouse/util";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable Long id) {
        warehouseService.deleteProduct(id);
        return "redirect:/warehouse/util";
    }
}
