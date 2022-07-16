package br.com.uaijug.uaijugdev1api.model.services;

import br.com.uaijug.uaijugdev1api.exceptions.ResourceNotFoundException;
import br.com.uaijug.uaijugdev1api.model.domain.Developer;

public interface DeveloperService extends CrudService<Developer, Long> {

    Developer findByNome(String nome) throws ResourceNotFoundException;

}
