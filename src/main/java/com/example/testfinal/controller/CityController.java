package com.example.testfinal.controller;

import com.example.testfinal.model.City;
import com.example.testfinal.model.Nation;
import com.example.testfinal.service.city.CityService;
import com.example.testfinal.service.nation.NationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class CityController {
    @Autowired
    private CityService service;

    @Autowired
    private NationService nationService;

    @ModelAttribute("nations")
    public List<Nation> findAll(){
        return nationService.findAll();
    }

    @GetMapping("/city")
    public ModelAndView listCity(){
        List<City> list= service.findAll();
        ModelAndView modelAndView= new ModelAndView("city/list");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

    @GetMapping("/create-city")
    public ModelAndView showFormCreate(){
        ModelAndView modelAndView= new ModelAndView("city/create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create-city")
    public ModelAndView saveCity(@Valid @ModelAttribute City city, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView= new ModelAndView("city/create");
            return modelAndView;
        }
        service.save(city);
        ModelAndView modelAndView=new ModelAndView("city/create");
        modelAndView.addObject("message", "Create City Successfully!!!");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @GetMapping("/view-city/{id}")
    public ModelAndView viewCity(@PathVariable Long id){
        Optional<City> city= service.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/view");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
    @GetMapping("/edit-city/{id}")
    public ModelAndView showFormEdit(@PathVariable Long id){
        Optional<City> city= service.findById(id);
        ModelAndView modelAndView= new ModelAndView("city/edit");
        modelAndView.addObject("city", city.get());
        return modelAndView;
    }
    @PostMapping("/edit-city")
    public ModelAndView updateCity( @Valid @ModelAttribute City city, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            ModelAndView modelAndView= new ModelAndView("city/edit");
            return modelAndView;
        }
        if (city== null){
            ModelAndView modelAndView =new ModelAndView("city/edit");
            modelAndView.addObject("message", "Error Update !!!");
            return modelAndView;
        }

        service.save(city);
        List<City> list= service.findAll();
        ModelAndView modelAndView =new ModelAndView("city/list");
        modelAndView.addObject("message", "Update City Successfully!!!");
        modelAndView.addObject("list",list);
        return modelAndView;
    }
    @GetMapping("/delete-city/{id}")
    public ModelAndView deleteCity(@PathVariable Long id ){
        service.delete(id);
        List<City> list= service.findAll();
        ModelAndView modelAndView= new ModelAndView("city/list");
        modelAndView.addObject("message", "Delete City Successfully!!!");
        modelAndView.addObject("list", list);
        return modelAndView;
    }

}
