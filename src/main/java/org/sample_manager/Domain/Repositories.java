package org.sample_manager.Domain;

import java.io.Serializable;

public class Repositories implements Serializable {
    private static Repositories instance;
    private final SampleRepository sampleRepository;

    public Repositories() {
        sampleRepository = new SampleRepository();
    }

    public SampleRepository getSampleRepository() {
        return sampleRepository;
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public static void setInstance(Repositories newInstance) {
        instance = newInstance;
    }
}
