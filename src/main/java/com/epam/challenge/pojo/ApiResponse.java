package com.epam.challenge.pojo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiResponse<T> {

    private final String status;
    private final String message;
    private final T data;

}