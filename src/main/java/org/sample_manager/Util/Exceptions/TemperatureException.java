package org.sample_manager.Util.Exceptions;

import org.sample_manager.GUI.Alert;

public class TemperatureException extends Throwable {
    public TemperatureException(String message) {
        Alert.error("Error", message, null);
    }
}
