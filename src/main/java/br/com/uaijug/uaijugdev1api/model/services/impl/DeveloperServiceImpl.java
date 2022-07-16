package br.com.uaijug.uaijugdev1api.model.services.impl;

import br.com.uaijug.uaijugdev1api.exceptions.BadResourceException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceAlreadyExistsException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceNotFoundException;
import br.com.uaijug.uaijugdev1api.model.domain.Developer;
import br.com.uaijug.uaijugdev1api.model.repositories.DeveloperRepository;
import br.com.uaijug.uaijugdev1api.model.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Override
    public Developer findById(Long id) throws ResourceNotFoundException {
        Developer developer = developerRepository.findById(id).orElse(null);
        if (developer == null) {
            throw new ResourceNotFoundException("Nao achei esse Id");
        }

        return developer;
    }

    @Override
    public List<Developer> findAll(int pageNumber, int rowPerPage) {
        List<Developer> developers = new ArrayList<>();
        Pageable sortedByIdAsc = PageRequest.of(pageNumber-1, rowPerPage, Sort.by("id").ascending());
        developerRepository.findAll(sortedByIdAsc).forEach(developers::add);
        return developers;
    }

    @Override
    public Developer save(Developer developer) throws BadResourceException, ResourceAlreadyExistsException {
      //  if (!StringUtils.isEmpty(developer.getNomeSobrenome()) && !existsById(developer.getId())){
        //    throw new ResourceAlreadyExistsException("O desenvolvedor ja existe");
        //}

        return developerRepository.save(developer);
    }

    @Override
    public Developer update(Long id, Developer developer) throws BadResourceException, ResourceNotFoundException {
        developer.setId(id);
        return developerRepository.save(developer);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find developer with id: " + id);
        }

        developerRepository.deleteById(id);
    }

    private boolean existsById(Long id) {
        return developerRepository.existsById(id);
    }

    @Override
    public Long count() {
        return developerRepository.count();
    }

    @Override
    public Developer findByNome(String nome) throws ResourceNotFoundException {
        return null;
    }
}
