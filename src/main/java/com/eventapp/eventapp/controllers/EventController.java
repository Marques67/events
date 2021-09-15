package com.eventapp.eventapp.controllers;

import com.eventapp.eventapp.models.Event;
import com.eventapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {

    @Autowired
    private EventRepository er;

    @RequestMapping(value = "/registerEvent", method = RequestMethod.GET)
    public String form(){
        return "event/formEvent";
    }

    @RequestMapping(value = "/registerEvent", method = RequestMethod.POST)
    public String form(Event event){

        er.save(event);

        return "redirect:/registerEvent";
    }

    @RequestMapping("/events")
    public ModelAndView listEvents() {
        ModelAndView mv = new ModelAndView("index.html");
        Iterable<Event> events = er.findAll();
        mv.addObject("events", events);
        return mv;
    }

    @RequestMapping("/{code}")
    public ModelAndView detailsEvent(@PathVariable("code") long code) {
        Event event = er.findByCode(code);
        ModelAndView mv = new ModelAndView("event/detailsEvent.html");
        mv.addObject("event", event);
        return mv;
    }
}
