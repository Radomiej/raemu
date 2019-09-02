package pl.radomiej.emu.logic;

import org.junit.Test;
import pl.radomiej.emu.logic.helpers.BitsPermutationHelper;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureBit;
import pl.radomiej.emu.logic.pure.PureByte;

import static junit.framework.TestCase.assertEquals;

public class BitsPermutationHelperTest {
    @Test
    public void copyBitsArrayTest1() {
        PureBit[] srcArray = new PureBit[]{PureBit.ZERO, PureBit.ONE, PureBit.ONE, PureBit.ZERO};
        PureBit[] testArray = BitsPermutationHelper.copy(srcArray);

        assertEquals(PureBit.ZERO, testArray[0]);
        assertEquals(PureBit.ONE, testArray[1]);
        assertEquals(PureBit.ONE, testArray[2]);
        assertEquals(PureBit.ZERO, testArray[3]);
    }

    @Test
    public void copyBitsArrayTest2() {
        PureBit[] srcArray = new PureBit[]{PureBit.ONE, PureBit.ZERO, PureBit.ONE, PureBit.ZERO};
        PureBit[] testArray = BitsPermutationHelper.copy(srcArray);

        assertEquals(PureBit.ONE, testArray[0]);
        assertEquals(PureBit.ZERO, testArray[1]);
        assertEquals(PureBit.ONE, testArray[2]);
        assertEquals(PureBit.ZERO, testArray[3]);
    }

    @Test
    public void copyBitsArrayTest3() {
        PureBit[] srcArray = new PureBit[]{PureBit.ONE, PureBit.ONE, PureBit.ZERO, PureBit.ZERO};
        PureBit[] testArray = BitsPermutationHelper.copy(srcArray);

        assertEquals(PureBit.ONE, testArray[0]);
        assertEquals(PureBit.ONE, testArray[1]);
        assertEquals(PureBit.ZERO, testArray[2]);
        assertEquals(PureBit.ZERO, testArray[3]);
    }


    @Test
    public void circularLeftShiftSingleTest() {
        PureByte testByte = ToByteParser.parse("00010111");
        PureBit[] srcArray = testByte.getBites();

        PureBit[] testArray = BitsPermutationHelper.circularShiftSingle(srcArray, 3, false);

        assertEquals(PureBit.ONE, testArray[0]);
        assertEquals(PureBit.ZERO, testArray[1]);
        assertEquals(PureBit.ONE, testArray[2]);
        assertEquals(PureBit.ONE, testArray[3]);
        assertEquals(PureBit.ONE, testArray[4]);
        assertEquals(PureBit.ZERO, testArray[5]);
        assertEquals(PureBit.ZERO, testArray[6]);
        assertEquals(PureBit.ZERO, testArray[7]);
    }
}
