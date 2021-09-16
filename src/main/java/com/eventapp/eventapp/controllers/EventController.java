package com.eventapp.eventapp.controllers;

import com.eventapp.eventapp.models.Event;
import com.eventapp.eventapp.models.Guest;
import com.eventapp.repository.EventRepository;
import com.eventapp.repository.GuestRepository;
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

    @Autowired
    private GuestRepository gr;

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

    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView detailsEvent(@PathVariable("code") long code) {
        Event event = er.findByCode(code);
        ModelAndView mv = new ModelAndView("event/detailsEvent.html");
        mv.addObject("event", event);
        return mv;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public String detailsEventPost(@PathVariable("code") long code, Guest guest) {
        Event event = er.findByCode(code);
        guest.setEvent(event);
        gr.save(guest);
        return "redirect:/{code}";
    }
}
