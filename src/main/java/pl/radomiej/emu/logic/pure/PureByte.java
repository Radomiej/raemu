package pl.radomiej.emu.logic.pure;

import pl.radomiej.emu.logic.RaByte;
import pl.radomiej.emu.logic.helpers.BitsPermutationHelper;

import java.util.Arrays;
import java.util.Objects;

public class PureByte implements RaByte {

    /**
     * Little-endian
     * [N][N-1][1][0]
     * [0] - Index 0 - Biggest
     * [N] - Index Last - Lowest
     */
    private final PureBit[] bites;
    private final boolean defaultValue;

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
            bites[i] = PureBit.getValueFor(defaultValue);
        }
    }

    public PureByte(int numberOfBites, final PureByte byteToCopy) {
        this(numberOfBites, byteToCopy.defaultValue);

        int bitesToCopy = Math.min(numberOfBites, byteToCopy.getLength());
        int copyOffset = 1;

        for (int i = numberOfBites - 1; i >= 0 && bitesToCopy > 0; i--, bitesToCopy--, copyOffset++) {
            bites[i] = PureBit.getValueFor(byteToCopy.getBit(byteToCopy.getLength() - copyOffset).getValue());
        }
    }

    public PureByte(PureBit[] bitesToSet) {
        this(bitesToSet.length);
        int numberOfBites = bitesToSet.length;

        for (int i = 0; i < numberOfBites; i++) {
            bites[i] = PureBit.getValueFor(bitesToSet[i].getValue());
        }
    }

    public static PureByte adjust(PureByte toAdjust, PureByte reference) {
        if (toAdjust.bites.length == reference.bites.length) return toAdjust;
        int referenceLength = reference.bites.length;
        int adjustLength = toAdjust.bites.length;

        int delta = referenceLength - adjustLength;
        boolean expand = referenceLength > adjustLength;

        PureByte temp = new PureByte(reference.bites);
        for (int i = 0; i < referenceLength; i++) {
            if (expand) {
                int referenceIndex = referenceLength - delta + i;
                if (referenceIndex < 0 || referenceIndex >= referenceLength) continue;
                temp.bites[referenceIndex] = toAdjust.bites[i];
            } else {
                int adjustIndex = -delta + i;
                if (adjustIndex < 0 || adjustIndex >= adjustLength) continue;
                temp.bites[i] = toAdjust.bites[adjustIndex];
            }
        }
        return temp;
    }

    //Multiple argument operations
    public void shiftRightInternal(int iteration) {
        for (int i = 0; i < iteration; i++) {
            shiftRightInternal();
        }
    }

    public void shiftRightInternal() {
        for (int i = bites.length - 1; i > 0; i--) {
            bites[i] = bites[i - 1];
        }
        bites[0] = PureBit.getValueFor(defaultValue);
    }

    public void shiftLeftInternal(int iteration) {
        for (int i = 0; i < iteration; i++) {
            shiftLeftInternal();
        }
    }

    public void shiftLeftInternal() {
        for (int i = 0; i < bites.length - 1; i++) {
            bites[i] = bites[i + 1];
        }
        bites[bites.length - 1] = PureBit.getValueFor(defaultValue);
    }

    public void moveRightArthmetic() {
        for (int i = 0; i < bites.length - 1; i++) {
            bites[i] = bites[i + 1];
        }
        bites[bites.length - 1] = PureBit.getValueFor(defaultValue);
    }

    public PureByte rotateBitesLeft(int rotateMiddleBitIndex) {
        PureBit[] resultBites = BitsPermutationHelper.circularShiftSingle(bites, rotateMiddleBitIndex, false);
        return new PureByte(resultBites);
    }

    public PureByte rotateBitesRight(int rotateMiddleBitIndex) {
        PureBit[] resultBites = BitsPermutationHelper.circularShiftSingle(bites, rotateMiddleBitIndex, true);
        return new PureByte(resultBites);
    }

    public PureByte setBit(int index, boolean setValue) {
        PureBit[] resultBites = BitsPermutationHelper.copy(bites);
        bites[index] = PureBit.getValueFor(setValue);
        return new PureByte(resultBites);
    }

    public PureBit getBit(int index) {
        return bites[index];
    }

    public PureBit[] getBites() {
        return BitsPermutationHelper.copy(bites);
    }

    @Override
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

    @Override
    public int getLength() {
        return bites.length;
    }

    public boolean isZero() {
        for (PureBit bit : bites) {
            if (bit.getValue()) return false;
        }
        return true;
    }

    @Override
    public PureByte copy() {
        PureByte result = new PureByte(bites.length);

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte negation() {
        PureByte result = new PureByte(bites.length);

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = !bites[i].getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte and(PureByte other) {
        other = adjust(other, this);
        PureByte result = new PureByte(bites.length);

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() & other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte or(PureByte other) {
        other = adjust(other, this);
        PureByte result = new PureByte(bites.length);

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() | other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    public PureByte xor(PureByte other) {
        other = adjust(other, this);
        PureByte result = new PureByte(bites.length);

        for (int i = 0; i < bites.length; i++) {
            boolean copyBitValue = bites[i].getValue() != other.getBit(i).getValue();
            result.setBit(i, copyBitValue);
        }
        return result;
    }

    @Override
    public PureByte shiftLeft() {
        PureByte result = copy();
        result.shiftLeftInternal();
        return result;
    }


    @Override
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

    public boolean greaterThan(PureByte other) {
        return !lessComplex(other, true);
    }

    public boolean greaterOrEqualsThan(PureByte other) {
        return !lessComplex(other, false);
    }

    public boolean lessThan(PureByte other) {
        return lessComplex(other, false);
    }

    public boolean lessOrEqualsThan(PureByte other) {
        return lessComplex(other, true);
    }

    public boolean lessComplex(PureByte other, boolean checkEqual) {
        if (other.getLength() != getLength())
            throw new UnsupportedOperationException("Bytes to compare must have the same amount of bits!");
        for (int i = 0; i < bites.length; i++) {
            if (other.getBit(i).getValue() && !bites[i].getValue()) return true;
            else if (!other.getBit(i).getValue() && bites[i].getValue()) return false;
        }

        return checkEqual;
    }

    public boolean getLeftmostBit() {
        return bites[bites.length - 1].getValue();
    }

    public boolean getRightmostBit() {
        return bites[0].getValue();
    }
}
