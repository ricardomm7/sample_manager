package org.sample_manager;

import org.sample_manager.Controller.SampleController;
import org.sample_manager.Domain.HazardTypes;
import org.sample_manager.Domain.Repositories;
import org.sample_manager.GUI.Alert;
import org.sample_manager.Util.Exceptions.EmptyStringException;
import org.sample_manager.Util.Exceptions.SymbolsStringException;

import java.io.*;
import java.time.LocalDate;

public class Bootstrap implements Runnable {
    public void serializeAll() {
        try {
            FileOutputStream fileOut = new FileOutputStream("s4ft66ys.smfile");
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
            FileInputStream fileIn = new FileInputStream("s4ft66ys.smfile");
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
        } catch (EmptyStringException | SymbolsStringException e) {
            throw new RuntimeException(e);
        }
    }

    private void addSampleSamples() throws EmptyStringException, SymbolsStringException {
        SampleController c1 = new SampleController();
        c1.create("Alcohol", HazardTypes.BIOLOGICAL, LocalDate.of(2024, 06, 13), LocalDate.of(2029, 05, 20), "Ana Magalhaes Pimenta");
        c1.create("Acetone", HazardTypes.CHEMICAL, LocalDate.of(2023, 02, 15), LocalDate.of(2026, 01, 25), "Carlos Santos Oliveira");
        c1.create("Phenol", HazardTypes.CHEMICAL, LocalDate.of(2021, 9, 9), LocalDate.of(2024, 8, 14), "022222222");
        c1.create("Toluene", HazardTypes.CHEMICAL, LocalDate.of(2023, 04, 22), LocalDate.of(2026, 03, 29), "Lucas Gomes");
        c1.create("Xylene", HazardTypes.CHEMICAL, LocalDate.of(2022, 06, 18), LocalDate.of(2025, 05, 25), "Fernanda Ribeiro");
        c1.create("Sulfuric Acid", HazardTypes.CHEMICAL, LocalDate.of(2024, 01, 05), LocalDate.of(2027, 12, 10), "Tiago Martins");

    }
}
