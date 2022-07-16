package br.com.uaijug.uaijugdev1api.api.v1.resources;

import br.com.uaijug.uaijugdev1api.exceptions.BadResourceException;
import br.com.uaijug.uaijugdev1api.exceptions.ResourceAlreadyExistsException;
import br.com.uaijug.uaijugdev1api.model.domain.Developer;
import br.com.uaijug.uaijugdev1api.model.services.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/developers")
public class DeveloperResources {

    @Autowired
    private DeveloperService developerService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Developer>> list() {
        List<Developer> developers = developerService.findAll(1, 10);

        if (developers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developers);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Developer> findById(@PathVariable("id") Long id) {
        Developer developer = developerService.findById(id);

        if (developer == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        developerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Developer> save(@Valid @RequestBody Developer developer) throws BadResourceException, ResourceAlreadyExistsException {
        Developer developerSaved = developerService.save(developer);

        if (developer == null) {
            return ResponseEntity.noContent().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(developerSaved.getId()).toUri();
        return ResponseEntity.created(uri).body(developerSaved);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Developer> update(@PathVariable("id") Long id, @Valid @RequestBody Developer developer) throws BadResourceException, ResourceAlreadyExistsException {
        Developer developerSaved = developerService.update(id, developer);

        if (developer == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developer);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<Developer> by(@RequestParam String nome) {
        Developer developer = developerService.findByNome(nome);

        if (developer == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(developer);
    }

}
