package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.SupplierRequest;
import com.supermarket.supermarket.model.Supplier;
import com.supermarket.supermarket.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("")
    public String getAllSupplier(Model model) {
        List<Supplier> suppliers = supplierService.getAll();
        model.addAttribute("supplier", new SupplierRequest());
        model.addAttribute("suppliers", suppliers);
        return "supplier/supplier";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable Long id) {
        supplierService.delete(id);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String editSupplier(@PathVariable Long id, Model model) {
        final Supplier supplier = supplierService.findById(id);
        final SupplierRequest supplierRequest = new SupplierRequest();
        supplierRequest.setName(supplier.getName());
        model.addAttribute("supplier", supplierRequest);
        model.addAttribute("supplierId", id);
        return "supplier/edit";
    }

    @PostMapping("/create")
    public String createSupplier(@Valid @ModelAttribute("supplier") SupplierRequest supplierRequest,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("supplier", supplierRequest);
            return "supplier/new";
        }
        final Supplier supplier = new Supplier();
        supplier.setName(supplierRequest.getName());
        supplierService.create(supplier);
        return "redirect:/suppliers";
    }

    @PostMapping("/update/{id}")
    public String updateSupplier(@PathVariable Long id, @Valid @ModelAttribute("supplier")
    SupplierRequest supplierRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("supplier", supplierRequest);
            model.addAttribute("supplierId", id);
            return "supplier/edit";
        }
        final Supplier supplier = supplierService.findById(id);
        supplier.setName(supplierRequest.getName());
        supplierService.update(supplier);
        return "redirect:/suppliers";
    }
}
