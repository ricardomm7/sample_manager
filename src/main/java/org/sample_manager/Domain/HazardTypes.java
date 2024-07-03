package org.sample_manager.Domain;

public enum HazardTypes {
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
    ERGONOMIC {
        @Override
        public String toString() {
            return "Ergonomic Hazard";
        }
    },
    PSYCHOSOCIAL {
        @Override
        public String toString() {
            return "Psychosocial Hazard";
        }
    },
    RADIATION {
        @Override
        public String toString() {
            return "Radiation Hazard";
        }
    };

    @Override
    public abstract String toString();
}

