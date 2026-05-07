package com.example.springboot_proj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {

    private boolean success;
    private String  message;
    private T       data;

    public static <T> ApiResponseDTO<T> ok(T data) {
        return ApiResponseDTO.<T>builder().success(true).data(data).build();
    }

    public static <T> ApiResponseDTO<T> ok(String message, T data) {
        return ApiResponseDTO.<T>builder().success(true).message(message).data(data).build();
    }

    public static <T> ApiResponseDTO<T> error(String message) {
        return ApiResponseDTO.<T>builder().success(false).message(message).build();
    }
}

