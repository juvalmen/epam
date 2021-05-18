package com.epam.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import lombok.Builder;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Data
@Builder
@JsonRootName(value = "quote")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeTO {

    private Long id;

    @Valid
    @NotNull(message = "no nulo")
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

}
