package org.sample_manager.Util.Exceptions;

import org.sample_manager.GUI.Alert;

public class EmptyStringException extends Throwable {
    public EmptyStringException(String message) {
        Alert.error("Error", message, null);
    }
}
