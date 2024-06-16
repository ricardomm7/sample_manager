package org.sample_manager;

import org.sample_manager.Controller.SampleController;
import org.sample_manager.Domain.Date;
import org.sample_manager.Domain.Repositories;

import java.io.*;

public class Bootstrap implements Runnable {
    public void serializeAll() {
        try {
            FileOutputStream fileOut = new FileOutputStream("serial/s4ft66ys.smfile");
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            outStream.writeObject(Repositories.getInstance());
            outStream.close();
            fileOut.close();
        } catch (IOException i) {
            System.out.println("Error saving all the data " + i.getMessage());
            i.printStackTrace();
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
            System.out.println("Error loading all the data " + e.getMessage());
        }
    }

    @Override
    public void run() {
        addSampleSamples();
    }

    private void addSampleSamples() {
        SampleController c1 = new SampleController();
        c1.create("Alcohol", false, new Date(2024, 06, 13), new Date(2029, 05, 20));
    }
}
