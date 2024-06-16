package org.sample_manager.Util;

import org.sample_manager.Util.Exceptions.EmptyStringException;

public class StringValidator {
    public static void validateNotEmpty(String value, String fieldName) throws EmptyStringException {
        if (value == null || value.trim().isEmpty()) {
            throw new EmptyStringException(fieldName + " cannot be empty.");
        }
    }
}
