package com.eventapp.repository;

import com.eventapp.eventapp.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event, String> {

    Event findByCode(long code);
}
