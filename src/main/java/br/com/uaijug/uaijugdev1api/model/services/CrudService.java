package br.com.uaijug.uaijugdev1api.model.services;

import br.com.uaijug.uaijugdev1api.exceptions.BadResourceException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceAlreadyExistsException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceNotFoundException;

import java.io.Serializable;
import java.util.List;

public interface CrudService<T, ID extends Serializable> {
    T findById(ID id) throws ResourceNotFoundException;
    List<T> findAll(int pageNumber, int rowPerPage);
    T save(T t) throws BadResourceException, ResourceAlreadyExistsException;
    T update(ID id, T t)
            throws BadResourceException, ResourceNotFoundException;
    void deleteById(ID id) throws ResourceNotFoundException;
    ID count();
}
