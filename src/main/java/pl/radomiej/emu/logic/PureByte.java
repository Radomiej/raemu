package pl.radomiej.emu.logic;

import pl.radomiej.emu.logic.helpers.BitsPermutationHelper;

import java.util.Arrays;
import java.util.Objects;

public class PureByte {

    private PureBit[] bites;
    private boolean defaultValue;

    public PureByte() {
        this(8, false);
    }

    public PureByte(int numberOfBites) {
        this(numberOfBites, false);
    }

    public PureByte(int numberOfBites, boolean defaultValue) {
        this.defaultValue = defaultValue;
        bites = new PureBit[numberOfBites];
        for (int i = 0; i < numberOfBites; i++) {
            bites[i] = new PureBit(defaultValue);
        }
    }


    //Multiple argument operations
    public void shiftRightInternal() {
        for (int i = bites.length - 1; i > 0; i--) {
            bites[i] = bites[i - 1];
        }
        bites[0] = new PureBit(defaultValue);
    }

    public void shiftLeftInternal() {
        for (int i = 0; i < bites.length - 1; i++) {
            bites[i] = bites[i + 1];
        }
        bites[bites.length - 1] = new PureBit(defaultValue);
    }

    public void moveRightArthmetic() {
        for (int i = 0; i < bites.length - 1; i++) {
            bites[i] = bites[i + 1];
        }
        bites[bites.length - 1] = new PureBit(!defaultValue);
    }

    public void rotateBitesLeft(int rotateMidleBitIndex) {
        bites = BitsPermutationHelper.circularShiftSingle(bites, rotateMidleBitIndex, false);
    }

    public void rotateBitesRight(int rotateMidleBitIndex) {
        bites = BitsPermutationHelper.circularShiftSingle(bites, rotateMidleBitIndex, true);
    }

    public void setBit(int index, boolean setValue) {
        bites[index].setValue(setValue);
    }

    public PureBit getBit(int index) {
        return new PureBit(bites[index].getValue());
    }

    public void setBites(PureBit[] setBites) {
        for (int i = 0; i < bites.length; i++) {
            bites[i].setValue(setBites[i].getValue());
        }
    }

    public void setBites(PureByte fromBytes) {
        for (int i = 0; i < bites.length; i++) {
            bites[i].setValue(fromBytes.getBit(i).getValue());
        }
    }

    public PureBit[] getBites() {
        PureBit[] bitesCopy = new PureBit[bites.length];
        for (int i = 0; i < bites.length; i++) {
            bitesCopy[i] = new PureBit(bites[i].getValue());
        }
        //System.arraycopy( bites, 0, bitesCopy, 0, bites.length );
        return bitesCopy;
    }

    public String toBinaryString() {
        String result = "";
        for (int i = 0; i < bites.length; i++) {
            if (bites[i].getValue()) result += "1";
            else result += "0";
        }
        return result;
    }

    public String prettyString() {
        String result = toBinaryString();
        result += " = ";
        result += toUnsignedInteger();
        return result;
    }

    @Override
    public String toString() {
//        return Arrays.toString(bites);
        return prettyString();
    }

    public int getLenght() {
        return bites.length;
    }

    public boolean isZero() {
        for (PureBit bit : bites) {
            if (bit.getValue()) return false;
        }
        return true;
    }

    public PureByte copy() {
        PureByte result = new PureByte();

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte negation() {
        PureByte result = new PureByte();

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = !bites[i].getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte and(PureByte other) {
        PureByte result = new PureByte();

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() & other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte or(PureByte other) {
        PureByte result = new PureByte();

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() | other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte xor(PureByte other) {
        PureByte result = new PureByte();

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() != other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte shiftLeft() {
        PureByte result = copy();
        result.shiftLeftInternal();
        return result;
    }

    public PureByte shiftRight() {
        PureByte result = copy();
        result.shiftRightInternal();
        return result;
    }

    public int toUnsignedInteger() {
        int sum = 0;
        int addValue = 1;
        for (int i = bites.length - 1; i >= 0; i--) {
            if (bites[i].getValue()) sum += addValue;
            addValue *= 2;
        }
        return sum;
    }

    public int toSignedInteger() {
        int sum = 0;
        int addValue = 1;
        for (int i = bites.length - 1; i >= 0; i--) {

            if (i == 0) {
                addValue = -addValue;
            }

            if (bites[i].getValue()) sum += addValue;
            addValue *= 2;
        }
        return sum;
    }

    public boolean lessThan(PureByte other) {
        for (int i = 0; i < bites.length; i++) {
            if (other.getBit(i).getValue() && !bites[i].getValue()) return true;
            else if (!other.getBit(i).getValue() && bites[i].getValue()) return false;
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PureByte pureByte = (PureByte) o;
        return defaultValue == pureByte.defaultValue &&
                Arrays.equals(bites, pureByte.bites);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(defaultValue);
        result = 31 * result + Arrays.hashCode(bites);
        return result;
    }
}
