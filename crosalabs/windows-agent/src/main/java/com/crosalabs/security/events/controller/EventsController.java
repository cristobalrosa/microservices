package com.crosalabs.security.events.controller;

import com.crosalabs.security.events.services.EventsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventsController {
    private final EventsService eventsService;

    // inject via application.properties
    @Value("${welcome.message}")
    private String message;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @RequestMapping(value = "/justOne", method = RequestMethod.POST)
    public ModelAndView post() {
        eventsService.create();
        return new ModelAndView("success");
    }

    @RequestMapping(value = "/createMany", method = RequestMethod.POST)
    public ModelAndView post(int number) {
        eventsService.create(number);
        return new ModelAndView("success");
    }
}
