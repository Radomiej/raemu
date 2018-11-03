import org.junit.Test;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.helpers.U2BitsMathHelper;

import static junit.framework.TestCase.assertEquals;

public class U2PureByteTest {

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
    public void addUnsignedBytes(){
        PureByte left  = ToByteParser.parse("01100011");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2BitsMathHelper.addBits(left, right);
        assertEquals("01100101", result.toBinaryString());

        for(int x = 0; x <= 255; x++){
            for(int y = 0; y <= 255; y++){
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte addResult = U2BitsMathHelper.addBits(byteX, byteY);
                assertEquals(ToByteParser.parse(x + y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void addBytesOverflow(){
        PureByte left  = ToByteParser.parse("11111111");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2BitsMathHelper.addBits(left, right);
        assertEquals("00000001", result.toBinaryString());
    }

    @Test
    public void subtractUnsignedBytes(){
        PureByte left  = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2BitsMathHelper.subtract(left, right);
        assertEquals("00000010", result.toBinaryString());

        for(int x = 0; x <= 255; x++){
            for(int y = 0; y <= 255; y++){
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte subtractResult = U2BitsMathHelper.subtract(byteX, byteY);
//                System.out.println("substract x: " + byteX + " y: " + byteY + " result: " + subtractResult.toSignedInteger());
                assertEquals(ToByteParser.parse(x - y).toUnsignedInteger(), subtractResult.toUnsignedInteger());
            }
        }
        assertEquals(2, U2BitsMathHelper.subtractOrg(5,3));
        assertEquals(-8, U2BitsMathHelper.subtractOrg(-5,3));

    }

    @Test
    public void subtractBytes2(){
        PureByte left  = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2BitsMathHelper.subtract(left, right);
        assertEquals("00000010", result.toBinaryString());

        assertEquals(2, U2BitsMathHelper.subtractOrg(5,3));

    }

    @Test
    public void copy(){
        PureByte left  = ToByteParser.parse("01100011");
        PureByte result = left.copy();
        assertEquals("01100011", result.toBinaryString());

    }

    @Test
    public void shiftLeft(){
        PureByte left  = ToByteParser.parse("01100011");
        PureByte result = left.shiftLeft();
        assertEquals("11000110", result.toBinaryString());

    }
}
