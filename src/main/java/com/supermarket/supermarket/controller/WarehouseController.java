package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.service.WarehouseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping("")
    public String getWarehouse(Model model){
        model.addAttribute("products", warehouseService.getAllProducts());
        return "warehouse/warehouse";
    }
}
