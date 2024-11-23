package com.financialhouseproject.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequestDto (@NotEmpty(message = "Email cannot be empty.")
                              String email,
                              @NotEmpty(message = "Password can not be empty.")
                              String password) {}
