package org.sample_manager.Domain;

import java.io.Serializable;

public enum HazardTypes implements Serializable {
    NONE {
        @Override
        public String toString() {
            return "None";
        }
    },
    CHEMICAL {
        @Override
        public String toString() {
            return "Chemical Hazard";
        }
    },
    PHYSICAL {
        @Override
        public String toString() {
            return "Physical Hazard";
        }
    },
    BIOLOGICAL {
        @Override
        public String toString() {
            return "Biological Hazard";
        }
    },
    FLAMMABLE {
        @Override
        public String toString() {
            return "Flammable";
        }
    },
    TOXIC {
        @Override
        public String toString() {
            return "Toxic";
        }
    },
    RADIATION {
        @Override
        public String toString() {
            return "Radiation Hazard";
        }
    },
    GAS {
        @Override
        public String toString() {
            return "Toxic Gases";
        }
    },
    PATHOGENS {
        @Override
        public String toString() {
            return "Pathogens (Bacteria, Viruses, Fungi)";
        }
    };

    @Override
    public abstract String toString();
}

