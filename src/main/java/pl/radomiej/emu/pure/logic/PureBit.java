package pl.radomiej.emu.pure.logic;

import java.util.Objects;

public class PureBit {
    private boolean value;

    public PureBit() {
        this(false);
    }

    public PureBit(boolean defaultValues) {
        value = defaultValues;
    }

    public void setValue(boolean newValue){
        value = newValue;
    }

    public boolean getValue(){
        return value;
    }

    //One argument operation
    public void negateNOR(){
        value = !value;
    }

    //Two argument operations
    public boolean and(boolean otherBit){
        if(value && otherBit) return true;
        return false;
    }

    public boolean or(boolean otherBit){
        if(!value && !otherBit) return false;
        return true;
    }

    public boolean xor(boolean otherBit){
        if(value != otherBit) return true;
        return false;
    }

    @Override
    public String toString() {
        return value ? "1" : "0";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PureBit pureBit = (PureBit) o;
        return value == pureBit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
