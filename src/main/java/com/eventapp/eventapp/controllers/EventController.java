package com.eventapp.eventapp.controllers;

import com.eventapp.eventapp.models.Event;
import com.eventapp.eventapp.models.Guest;
import com.eventapp.repository.EventRepository;
import com.eventapp.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    public String form(@Valid Event event, BindingResult result, RedirectAttributes attributes){
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique os campos!");
            return "redirect:/registerEvent";
        }

        er.save(event);
        attributes.addFlashAttribute("message", "Verifique os campos!");
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

        Iterable<Guest> guests = gr.findByEvent(event);
        mv.addObject("guests", guests);

        return mv;
    }

    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public String detailsEventPost(@PathVariable("code") long code, @Valid Guest guest, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verifique os campos!");
            return "redirect:/{code}";
        }
        Event event = er.findByCode(code);
        guest.setEvent(event);
        gr.save(guest);
        attributes.addFlashAttribute("message", "Convidado adicionado com sucesso!");
        return "redirect:/{code}";
    }
}
