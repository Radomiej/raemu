import org.junit.Test;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.helpers.U2BitsMathHelper;

import static junit.framework.TestCase.assertEquals;

public class PureByteTest {

    @Test
    public void byteParserTest() {
        PureByte testByte = ToByteParser.parse("00010111");


        assertEquals(true, testByte.getBit(7).getValue());
        assertEquals(true, testByte.getBit(6).getValue());
        assertEquals(true, testByte.getBit(5).getValue());
        assertEquals(false, testByte.getBit(4).getValue());
        assertEquals(true, testByte.getBit(3).getValue());
        assertEquals(false, testByte.getBit(2).getValue());
        assertEquals(false, testByte.getBit(1).getValue());
        assertEquals(false, testByte.getBit(0).getValue());
    }

    @Test
    public void rotateShiftLeft() {


        PureByte testByte = ToByteParser.parse("00010111");
        testByte.rotateBitesLeft(3);
        System.out.print(testByte.toBinaryString());
        // assert statements
        assertEquals(false, testByte.getBit(7).getValue());
        assertEquals(false, testByte.getBit(6).getValue());
        assertEquals(false, testByte.getBit(5).getValue());
        assertEquals(true, testByte.getBit(4).getValue());
        assertEquals(true, testByte.getBit(3).getValue());
        assertEquals(true, testByte.getBit(2).getValue());
        assertEquals(false, testByte.getBit(1).getValue());
        assertEquals(true, testByte.getBit(0).getValue());

        //PureByte testByte = ToByteParser.parse("10111000");
    }

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {

    }

    @Test
    public void negation() {
        PureByte testByte = ToByteParser.parse("00010111");
        PureByte negated = testByte.negation();
        assertEquals("11101000", negated.toBinaryString());
    }

    @Test
    public void and() {
        PureByte left  = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.and(right);
        assertEquals("00110000", result.toBinaryString());
    }

    @Test
    public void or() {
        PureByte left  = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.or(right);
        assertEquals("11111100", result.toBinaryString());
    }

    @Test
    public void xor() {
        PureByte left  = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.xor(right);
        assertEquals("11001100", result.toBinaryString());
    }

    @Test
    public void addBytes(){
        PureByte left  = ToByteParser.parse("01100011");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2BitsMathHelper.addBits(left, right);
        assertEquals("01100101", result.toBinaryString());

    }
}
