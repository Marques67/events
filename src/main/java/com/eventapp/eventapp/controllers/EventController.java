package com.eventapp.eventapp.controllers;

import com.eventapp.eventapp.models.Event;
import com.eventapp.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
