package br.com.uaijug.uaijugdev1api.model.repositories;

import br.com.uaijug.uaijugdev1api.model.domain.Developer;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends PagingAndSortingRepository<Developer, Long>, JpaSpecificationExecutor<Developer> {
}
