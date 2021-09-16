package com.eventapp.repository;

import com.eventapp.eventapp.models.Event;
import com.eventapp.eventapp.models.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, String> {
    Iterable<Guest> findByEvent(Event event);
}
