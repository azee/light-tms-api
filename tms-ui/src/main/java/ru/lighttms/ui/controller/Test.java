package ru.lighttms.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by IntelliJ IDEA.
 * User: azee
 */

@Controller
@RequestMapping("/test")
public class Test {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView testAction(@RequestParam("name") String name){

		ModelAndView model = new ModelAndView("TestPage");
		model.addObject("msg", name);

		return model;
	}
}
