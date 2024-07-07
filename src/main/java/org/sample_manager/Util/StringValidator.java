package org.sample_manager.Util;

import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;

import java.util.regex.Pattern;

public class StringValidator {
    public static void validateNotEmpty(String value, String fieldName) throws EmptyStringException {
        if (value == null || value.trim().isEmpty()) {
            throw new EmptyStringException(fieldName + " cannot be empty.");
        }
    }

    public static void validateSymbols(String value, String fieldName) throws SymbolsStringException {
        if (Pattern.compile("[^\\p{L}\\p{N} ]").matcher(value).find()) {
            throw new SymbolsStringException(fieldName + " contains invalid symbols.");
        }
    }
}
