package ru.lighttms.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

@Controller
@RequestMapping("/suites")
public class Suites {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(){

		ModelAndView model = new ModelAndView("suites/suites");

		return model;
	}
}
