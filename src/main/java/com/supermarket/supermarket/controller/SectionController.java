package com.supermarket.supermarket.controller;

import com.supermarket.supermarket.dto.SectionRequest;
import com.supermarket.supermarket.model.Section;
import com.supermarket.supermarket.service.SectionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("sections")
public class SectionController {
    private final SectionService sectionService;

    public SectionController(SectionService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("")
    public String getAllSection(Model model) {
        List<Section> sections = sectionService.getAll();
        model.addAttribute("section", new SectionRequest());
        model.addAttribute("sections", sections);
        return "section/section";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSection(@PathVariable Long id) {
        sectionService.delete(id);
        return "redirect:/sections";
    }

    @GetMapping("/edit/{id}")
    public String editSection(@PathVariable Long id, Model model) {
        final Section section = sectionService.findById(id);
        final SectionRequest sectionRequest = new SectionRequest();
        sectionRequest.setName(section.getName());
        model.addAttribute("section", sectionRequest);
        model.addAttribute("sectionId", id);
        return "section/edit";
    }

    @PostMapping("/create")
    public String createSection(@Valid @ModelAttribute("section") SectionRequest sectionRequest,
                                     BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("section", sectionRequest);
            return "section/new";
        }
        final Section section = new Section();
        section.setName(sectionRequest.getName());
        sectionService.create(section);
        return "redirect:/sections";
    }

    @PostMapping("/update/{id}")
    public String updateSection(@PathVariable Long id, @Valid @ModelAttribute("section")
    SectionRequest sectionRequest, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("section", sectionRequest);
            model.addAttribute("sectionId", id);
            return "section/edit";
        }
        final Section section = sectionService.findById(id);
        section.setName(sectionRequest.getName());
        sectionService.update(section);
        return "redirect:/sections";
    }
}
