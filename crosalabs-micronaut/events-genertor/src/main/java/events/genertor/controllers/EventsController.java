package events.genertor.controllers;

import events.genertor.services.EventsService;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Consumes;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.views.ModelAndView;
import io.micronaut.views.View;

import javax.inject.Inject;

@Controller
public class EventsController {

    @Inject
    private final EventsService eventsService;

    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @Get("/")
    public ModelAndView index() {
        return new ModelAndView("home",null);
    }

    @Post(value = "/justOne")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public ModelAndView createOne(){
        eventsService.generate();
        return new ModelAndView("success",null);
    }
    @Post(value = "/createMany")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public ModelAndView createMany(int number){

        eventsService.generate(number);
        return new ModelAndView("success",null);
    }
}
