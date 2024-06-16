package org.sample_manager;

import org.sample_manager.Controller.SampleController;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.GUI.Alert;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.ZeroHazardException;

import java.io.*;
import java.time.LocalDate;

public class Bootstrap implements Runnable {
    public void serializeAll() {
        try {
            FileOutputStream fileOut = new FileOutputStream("serial/s4ft66ys.smfile");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(Repositories.getInstance());
            outStream.close();
            fileOut.close();
        } catch (IOException i) {
            Alert.error("Error", i.getMessage(), "Error saving all the data");
        }
    }

    /**
     * Deserializes all data from a file.
     */
    public void deserializeAll() {
        try {
            FileInputStream fileIn = new FileInputStream("serial/s4ft66ys.smfile");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Repositories instance = (Repositories) in.readObject();
            Repositories.setInstance(instance);
            in.close();
            fileIn.close();
        } catch (FileNotFoundException e) {
            Repositories.setInstance(new Repositories());
        } catch (IOException | ClassNotFoundException e) {
            Alert.error("Error", e.getMessage(), "Error loading all the data");
        }
    }

    @Override
    public void run() {
        try {
            addSampleSamples();
        } catch (EmptyStringException | ZeroHazardException e) {
            throw new RuntimeException(e);
        }
    }

    private void addSampleSamples() throws EmptyStringException, ZeroHazardException {
        SampleController c1 = new SampleController();
        c1.create("Alcohol", false, LocalDate.of(2024, 06, 13), LocalDate.of(2029, 05, 20));
    }
}
