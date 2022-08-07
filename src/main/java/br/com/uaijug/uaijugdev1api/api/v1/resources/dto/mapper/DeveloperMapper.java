package br.com.uaijug.uaijugdev1api.api.v1.resources.dto.mapper;

import br.com.uaijug.uaijugdev1api.api.v1.resources.dto.request.DeveloperRequest;
import br.com.uaijug.uaijugdev1api.api.v1.resources.dto.response.DeveloperResponse;
import br.com.uaijug.uaijugdev1api.model.domain.Developer;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {

    @InheritConfiguration
    @Mapping(source = "nomeSobrenome", target = "nomeSobrenome")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phone", target = "phone")
    Developer from(DeveloperRequest request);

    @InheritInverseConfiguration
    @Mapping(target = "id", source = "id")
    DeveloperResponse to(Developer supplier);

    List<DeveloperResponse> map(List<Developer> developers);
}
