package br.com.uaijug.uaijugdev1api.api.v1.resources.dto.request;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Validated
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@Data
public class DeveloperRequest {

    private Long id;

    @NotNull
    @Size(min = 2, max = 255)
    private String nomeSobrenome;

    @Email(message = "Email invalido")
    @Size(max = 120)
    private String email;

    @Pattern(regexp ="^\\+?[0-9. ()-]{7,25}$", message = "Phone number")
    @Size(max = 25)
    private String phone;
}
