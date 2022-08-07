package br.com.uaijug.uaijugdev1api.api.v1.resources;

import br.com.uaijug.uaijugdev1api.api.v1.resources.dto.mapper.DeveloperMapper;
import br.com.uaijug.uaijugdev1api.api.v1.resources.dto.request.DeveloperRequest;
import br.com.uaijug.uaijugdev1api.api.v1.resources.dto.response.DeveloperResponse;
import br.com.uaijug.uaijugdev1api.exceptions.BadResourceException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceAlreadyExistsException;
import br.com.uaijug.uaijugdev1api.model.domain.Developer;
import br.com.uaijug.uaijugdev1api.model.services.DeveloperService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/developers")
public class DeveloperResources {

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private DeveloperMapper developerMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Developer>> list() {
        List<Developer> developers = developerService.findAll(1, 10);
        List<DeveloperResponse> developersResponse = developerMapper.map(developers);

        developersResponse.forEach(director -> {
            director.add(linkTo(methodOn(DeveloperResources.class).findById(director.getId())).withSelfRel());
        });

     //   Link allDirectorsLink = linkTo(methodOn(DeveloperResources.class).list()).withSelfRel();
        //developers.add(linkTo(methodOn(DeveloperResources.class).list()).withSelfRel());

        if (developers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developers);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DeveloperResponse> findById(@PathVariable("id") Long id) {
        Developer developerFound = developerService.findById(id);
        DeveloperResponse developerResponse = developerMapper.to(developerFound);

        if (developerResponse == null) {
            return ResponseEntity.noContent().build();
        }

        developerResponse.add(linkTo(methodOn(DeveloperResources.class).findById(developerResponse.getId())).withSelfRel());
        return ResponseEntity.ok(developerResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        developerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<DeveloperResponse> save(@Valid @RequestBody DeveloperRequest developerRequest) throws BadResourceException, ResourceAlreadyExistsException {
        Developer developer = developerMapper.from(developerRequest);
        Developer developerSaved = developerService.save(developer);
        DeveloperResponse developerResponse = developerMapper.to(developerSaved);

        if (developerResponse == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(developerResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(developerResponse);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<DeveloperResponse> update(@PathVariable("id") Long id, @Valid @RequestBody DeveloperRequest developerRequest) throws BadResourceException, ResourceAlreadyExistsException {
        Developer developer = developerMapper.from(developerRequest);
        Developer developerSaved = developerService.update(id, developer);
        DeveloperResponse developerResponse = developerMapper.to(developerSaved);

        if (developerResponse == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developerResponse);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<DeveloperResponse> by(@RequestParam String nome) {
        Developer developerFound = developerService.findByNome(nome);
        DeveloperResponse developerResponse = developerMapper.to(developerFound);

        if (developerResponse == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developerResponse);
    }

}
