package pl.radomiej.imp.i8080.cpu;

import pl.radomiej.emu.cpu.Flag;
import pl.radomiej.emu.logic.pure.PureBit;
import pl.radomiej.emu.logic.pure.PureByte;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class I8080Flags implements Flag {
    private PureByte bits = new PureByte(8);
    private Map<String, Integer> bitsHumanMapping = new HashMap<>();

    public I8080Flags() {
        bits.setBit(1, true);

        bitsHumanMapping.put("Sign Flag", 7);
        bitsHumanMapping.put("S", 7);

        bitsHumanMapping.put("Zero Flag", 6);
        bitsHumanMapping.put("Z", 6);

        bitsHumanMapping.put("Auxiliary Carry Flag", 4);
        bitsHumanMapping.put("A", 4);
        bitsHumanMapping.put("H", 4);
        bitsHumanMapping.put("AC", 4);

        bitsHumanMapping.put("Parity Flag", 2);
        bitsHumanMapping.put("P", 2);

        bitsHumanMapping.put("Carry Flag", 0);
        bitsHumanMapping.put("C", 0);
    }


    @Override
    public boolean getBitByHumanName(String name) {
        if (!bitsHumanMapping.containsKey(name))
            throw new NoSuchElementException("Given name does not exist in this FlagRegistry: " + name);
        return bits.getBit(bitsHumanMapping.get(name)).getValue();
    }

    @Override
    public PureBit getPureBitByHumanName(String name) {
        if (!bitsHumanMapping.containsKey(name))
            throw new NoSuchElementException("Given name does not exist in this FlagRegistry: " + name);
        return bits.getBit(bitsHumanMapping.get(name));
    }

    @Override
    public boolean getBitByIndex(int index) {
        return bits.getBit(index).getValue();
    }

    @Override
    public void setBitByIndex(int index, boolean value) {
        bits = bits.setBit(index, value);
    }

    @Override
    public PureBit getPureBitByIndex(int index) {
        return bits.getBit(index);
    }

    @Override
    public boolean isCarry() {
        return getBitByIndex(0);
    }

    @Override
    public void setCarry(boolean carry) {
        setBitByIndex(0, carry);
    }

    @Override
    public boolean isOverflow() {
        return false;
    }

    @Override
    public void setOverflow(boolean overflow) {
//        setBitByIndex(0, carry);
    }

    @Override
    public boolean isBorrow() {
        return getBitByIndex(4);
    }

    @Override
    public void setBorrow(boolean borrow) {
        setBitByIndex(4, borrow);
    }

    @Override
    public boolean isParity() {
        return getBitByIndex(2);
    }

    @Override
    public void setParity(boolean parity) {
        setBitByIndex(2, parity);
    }
}
