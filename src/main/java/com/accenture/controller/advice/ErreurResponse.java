package com.accenture.controller.advice;

import java.time.LocalDateTime;

public record ErreurResponse(LocalDateTime temporalite, String type, String message) {
}
