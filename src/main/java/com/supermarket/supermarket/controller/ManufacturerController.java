package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.ManufacturerRequest;
import com.supermarket.supermarket.model.Manufacturer;
import com.supermarket.supermarket.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("manufacturers")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping("")
    public String getAllManufacturer(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.getAll();
        model.addAttribute("manufacturer", new ManufacturerRequest());
        model.addAttribute("manufacturers", manufacturers);
        return "manufacturer/manufacturer";
    }

    // no need
//    @GetMapping("/{id}")
//    public String findManufacturerById(@PathVariable Long id, Model model) {
//        model.addAttribute("manufacturer", manufacturerService.findById(id));
//        return "manufacturer/show";
//    }
    // no need
//    @GetMapping("/new")
//    public String newManufacturer(Model model) {
//        model.addAttribute("manufacturer", new ManufacturerRequest());
//        return "manufacturer/new";
//    }

    @DeleteMapping("/delete/{id}")
    public String deleteManufacturer(@PathVariable Long id) {
        manufacturerService.delete(id);
        return "redirect:/manufacturers"; // Обновленный адрес перенаправления
    }

    @GetMapping("/edit/{id}")
    public String editManufacturer(@PathVariable Long id, Model model) {
        final Manufacturer manufacturer = manufacturerService.findById(id);
        final ManufacturerRequest manufacturerRequest = new ManufacturerRequest();
        manufacturerRequest.setName(manufacturer.getName());
        model.addAttribute("manufacturer", manufacturerRequest);
        model.addAttribute("manufacturerId", id);
        return "manufacturer/edit";
    }

    @PostMapping("/create")
    public String createManufacturer(@Valid @ModelAttribute("manufacturer") ManufacturerRequest manufacturerRequest,
                                     BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("manufacturer", manufacturerRequest);
            return "manufacturer/new";
        }
        final Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerRequest.getName());
        manufacturerService.create(manufacturer);
        return "redirect:/manufacturers";
    }

    @PostMapping("/update/{id}")
    public String updateManufacturer(@PathVariable Long id, @Valid @ModelAttribute("manufacturer")
    ManufacturerRequest manufacturerRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("manufacturer", manufacturerRequest);
            model.addAttribute("manufacturerId", id);
            return "manufacturer/edit";
        }
        final Manufacturer manufacturer = manufacturerService.findById(id);
        manufacturer.setName(manufacturerRequest.getName());
        manufacturerService.update(manufacturer);
        return "redirect:/manufacturers";
    }
}
