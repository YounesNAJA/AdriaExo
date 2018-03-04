package com.younesnaja.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.younesnaja.adria.exo.model.Person;

@Controller
@RequestMapping("/person")
public class PersonController {
	
	@RequestMapping(value = "/list")
    public ModelAndView list() {
		ModelAndView mav = new ModelAndView("personsList", "person", new Person());
        return mav;
    }
 
}
