package org.sample_manager.Util.Exceptions;

import org.sample_manager.GUI.Alert;

public class ZeroHazardException extends Throwable {
    public ZeroHazardException(String message) {
        Alert.error("Error", message, null);
    }
}
