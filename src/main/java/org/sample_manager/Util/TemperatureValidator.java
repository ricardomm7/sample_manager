package org.sample_manager.Util;

import org.sample_manager.Util.Exceptions.TemperatureException;

public class TemperatureValidator {
    public static void validateRange(Double value, String fieldName) throws TemperatureException {
        if (value > 100 || value < -50) {
            throw new TemperatureException(fieldName + " cannot be empty.");
        }
    }
}
