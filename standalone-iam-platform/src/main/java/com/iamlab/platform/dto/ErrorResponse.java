package com.iamlab.platform.dto;

public record ErrorResponse(
        int status,
        String error,
        String message
) {
}