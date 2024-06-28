package org.sample_manager.Util.Exceptions;

import org.sample_manager.GUI.Alert;

public class SymbolsStringException extends Throwable {
    public SymbolsStringException(String message) {
        Alert.error("Error", message, null);
    }
}
