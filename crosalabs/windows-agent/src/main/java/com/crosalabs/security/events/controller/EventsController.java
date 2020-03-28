package com.crosalabs.security.events.controller;

import com.crosalabs.security.events.services.EventsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventsController {

    private final EventsService eventService;

    public EventsController(EventsService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/justOne", method = RequestMethod.POST)
    public ModelAndView postOne(){
        eventService.create();
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/createMany", method = RequestMethod.POST)
    public ModelAndView postMany(int number){
        eventService.create(number);
        return new ModelAndView("success");
    }
}
