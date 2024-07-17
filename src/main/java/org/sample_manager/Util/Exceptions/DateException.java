package org.sample_manager.Util.Exceptions;

import org.sample_manager.GUI.Alert;

public class DateException extends Throwable {
    public DateException(String message) {
        Alert.error("Error", message, null);
    }
}
