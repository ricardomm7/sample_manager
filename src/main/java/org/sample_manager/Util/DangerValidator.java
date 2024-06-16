package org.sample_manager.Util;

import org.sample_manager.Util.Exceptions.ZeroHazardException;

public class DangerValidator {
    public static void validateNotEmpty(Boolean value, String fieldName) throws ZeroHazardException {
        if (value == null) {
            throw new ZeroHazardException(fieldName + " cannot be empty.");
        }
    }
}
