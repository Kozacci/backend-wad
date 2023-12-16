package pl.uwm.wateradventure.exceptions;

import jakarta.validation.ConstraintViolation;

public record ErrorDTO(String fieldName, String error) {

    static String extractFieldName(ConstraintViolation<?> violation) {
        var entireMessage = violation.getMessage();
        var dotIndex = entireMessage.indexOf(".");
        var colonIndex = entireMessage.indexOf(":");
        return entireMessage.substring(dotIndex + 1, colonIndex);
    }

}
