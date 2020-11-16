package org.bohdan.web.controllers;

import org.apache.log4j.Logger;
import org.bohdan.web.service.ViewToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("*/tour")
public class TourController {

    private static final Logger logger = Logger.getLogger(TourController.class);

    private ViewToursService viewToursService;

    @Autowired
    public TourController(ViewToursService viewToursService) {
        this.viewToursService = viewToursService;
    }

    @RequestMapping(value = "*/view", method = RequestMethod.GET)
    public ModelAndView viewTours(HttpServletRequest request) {
        return viewToursService.execute(request, "main");
    }

//    @RequestMapping(value = "*/view", method = RequestMethod.GET)
//    @ResponseBody
//    public ModelAndView viewTours(@RequestParam(value = "command", required = false) String command,
//                                  @RequestParam(value = "lang", required = false) String lang
//    ) {
//        logger.debug("command --> " + command);
//        logger.debug("lang --> " + lang);
//        ModelAndView modelAndView = new ModelAndView("main");
//        modelAndView = viewToursService.execute(modelAndView, lang);
//        return modelAndView;
//    }

}
