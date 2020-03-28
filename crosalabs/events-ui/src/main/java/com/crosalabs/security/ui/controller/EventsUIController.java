package com.crosalabs.security.ui.controller;

import com.crosalabs.security.ui.model.Event;
import com.crosalabs.security.ui.services.EventSearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller(value = "")
public class EventsUIController {

    private final EventSearchService eventSearchService;

    public EventsUIController(EventSearchService eventSearchService) {
        this.eventSearchService = eventSearchService;
    }

    @GetMapping(value = "")
    public ModelAndView getPaginatedEvents(Model model, @PageableDefault(size = 20) Pageable pageable) {

        Page<Event> eventsPage = eventSearchService.fetchEvents(pageable);

        model.addAttribute("eventsPage", eventsPage);

        int totalPages = eventsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return new ModelAndView("eventList.html");
    }

    @GetMapping(value = "/withId")
    public ModelAndView getById(Model model,
                                @PageableDefault(size = 20) Pageable pageable,
                                @RequestParam(value = "eventId", required = false) UUID eventId) {

        Optional<Event> eventOptional = eventSearchService.fetchEvent(eventId);
        List<Event> events = new ArrayList<>();
        eventOptional.ifPresent(events::add);

        Page<Event> eventsPage = new PageImpl<>(events, PageRequest.of(0, events.size()), events.size());
        model.addAttribute("eventsPage", eventsPage);

        int totalPages = eventsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return new ModelAndView("eventList.html");
    }


}