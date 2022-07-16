package br.com.uaijug.uaijugdev1api.model.services.impl;

import br.com.uaijug.uaijugdev1api.exceptions.BadResourceException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceAlreadyExistsException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceNotFoundException;
import br.com.uaijug.uaijugdev1api.model.domain.Event;
import br.com.uaijug.uaijugdev1api.model.services.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Override
    public Event findById(Long aLong) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public List<Event> findAll(int pageNumber, int rowPerPage) {
        return null;
    }

    @Override
    public Event save(Event event) throws BadResourceException, ResourceAlreadyExistsException {
        return null;
    }

    @Override
    public Event update(Long aLong, Event event) throws BadResourceException, ResourceNotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) throws ResourceNotFoundException {

    }

    @Override
    public Long count() {
        return null;
    }
}
