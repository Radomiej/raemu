package pl.radomiej.emu.logic.pure;

import java.util.Objects;

public enum PureBit {
    ZERO(false), ONE(true);

    private final boolean value;

    PureBit(boolean initValue) {
        value = initValue;
    }

    public static PureBit getValueFor(boolean value) {
        return value ? ONE : ZERO;
    }

    public boolean getValue() {
        return value;
    }

    //One argument operation
    public PureBit negateNOR() {
        return PureBit.getValueFor(!value);
    }

    //Two argument operations
    public boolean and(boolean otherBit) {
        if (value && otherBit) return true;
        return false;
    }

    public boolean or(boolean otherBit) {
        if (!value && !otherBit) return false;
        return true;
    }

    public boolean xor(boolean otherBit) {
        if (value != otherBit) return true;
        return false;
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }
}
