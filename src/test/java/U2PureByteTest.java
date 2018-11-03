import org.junit.Test;
import pl.radomiej.emu.pure.logic.PureByte;
import pl.radomiej.emu.pure.logic.helpers.ToByteParser;
import pl.radomiej.emu.pure.logic.helpers.U2BitsMathHelper;

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
    public void negationTest() {
        PureByte testByte = ToByteParser.parse("00010111");
        PureByte negated = testByte.negation();
        assertEquals("11101000", negated.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            PureByte byteX = ToByteParser.parse(x);
            PureByte addResult = byteX.negation();
            assertEquals(ToByteParser.parse(~x).toUnsignedInteger(), addResult.toUnsignedInteger());
        }
    }

    @Test
    public void andTest() {
        PureByte left = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.and(right);
        assertEquals("00110000", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte addResult = byteX.and(byteY);
                assertEquals(ToByteParser.parse(x & y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void orTest() {
        PureByte left = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.or(right);
        assertEquals("11111100", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte addResult = byteX.or(byteY);
                assertEquals(ToByteParser.parse(x | y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void xorTest() {
        PureByte left = ToByteParser.parse("11110000");
        PureByte right = ToByteParser.parse("00111100");
        PureByte result = left.xor(right);
        assertEquals("11001100", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte addResult = byteX.xor(byteY);
                assertEquals(ToByteParser.parse(x ^ y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void addUnsignedBytesTest() {
        PureByte left = ToByteParser.parse("01100011");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2BitsMathHelper.add(left, right);
        assertEquals("01100101", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte addResult = U2BitsMathHelper.add(byteX, byteY);
                assertEquals(ToByteParser.parse(x + y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void addBytesOverflowTest() {
        PureByte left = ToByteParser.parse("11111111");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2BitsMathHelper.add(left, right);
        assertEquals("00000001", result.toBinaryString());
    }

    @Test
    public void subtractUnsignedBytes() {
        PureByte left = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2BitsMathHelper.subtract(left, right);
        assertEquals("00000010", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte subtractResult = U2BitsMathHelper.subtract(byteX, byteY);
                assertEquals(ToByteParser.parse(x - y).toUnsignedInteger(), subtractResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void subtractVersion2UnsignedBytesTest() {
        PureByte left = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2BitsMathHelper.subtract2(left, right);
        assertEquals("00000010", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte subtractResult = U2BitsMathHelper.subtract2(byteX, byteY);
                assertEquals(ToByteParser.parse(x - y).toUnsignedInteger(), subtractResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void copyTest() {
        PureByte left = ToByteParser.parse("01100011");
        PureByte result = left.copy();
        assertEquals("01100011", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            PureByte byteX = ToByteParser.parse(x);
            PureByte addResult = byteX.copy();
            assertEquals(ToByteParser.parse(x).toUnsignedInteger(), addResult.toUnsignedInteger());
        }

    }

    @Test
    public void shiftLeftTest() {
        PureByte left = ToByteParser.parse("01100011");
        PureByte result = left.shiftLeft();
        assertEquals("11000110", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            PureByte byteX = ToByteParser.parse(x);
            PureByte addResult = byteX.shiftLeft();
            assertEquals(ToByteParser.parse(x << 1).toUnsignedInteger(), addResult.toUnsignedInteger());
        }
    }

    @Test
    public void shiftLeftInternalTest() {
        PureByte left = ToByteParser.parse("01100011");
        PureByte result = left.shiftLeft();
        assertEquals("11000110", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            PureByte byteX = ToByteParser.parse(x);
            byteX.shiftLeftInternal();
            assertEquals(ToByteParser.parse(x << 1).toUnsignedInteger(), byteX.toUnsignedInteger());
        }
    }

    @Test
    public void lessTest() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                assertEquals(x < y, byteX.lessThan(byteY));
            }
        }
    }

    @Test
    public void lessEqualsTest() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                assertEquals(x <= y, byteX.lessOrEqualsThan(byteY));
            }
        }
    }

    @Test
    public void graterOrEqualsTest() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                assertEquals(x >= y, byteX.greaterOrEqualsThan(byteY));
            }
        }
    }

    @Test
    public void graterTest() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                assertEquals(x > y, byteX.greaterThan(byteY));
            }
        }
    }

    @Test
    public void parseTest() {
        PureByte byte0 = ToByteParser.parse(0);
        assertEquals(true, byte0.isZero());

        for(int i = 1; i < 255; i++){

        }
    }
}
