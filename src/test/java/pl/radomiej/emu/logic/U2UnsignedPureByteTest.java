package pl.radomiej.emu.logic;

import org.junit.jupiter.api.Test;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.logic.pure.PureFlags;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class U2UnsignedPureByteTest {

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
        PureByte resultByte = testByte.rotateBitesLeft(3);
        System.out.print(testByte.toBinaryString());
        // assert statements
        assertEquals(false, resultByte.getBit(7).getValue());
        assertEquals(false, resultByte.getBit(6).getValue());
        assertEquals(false, resultByte.getBit(5).getValue());
        assertEquals(true, resultByte.getBit(4).getValue());
        assertEquals(true, resultByte.getBit(3).getValue());
        assertEquals(true, resultByte.getBit(2).getValue());
        assertEquals(false, resultByte.getBit(1).getValue());
        assertEquals(true, resultByte.getBit(0).getValue());

        //PureByte testByte = ToByteParser.parse("10111000");
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
        PureByte result = U2UnsignedBitsMathHelper.add(left, right);
        assertEquals("01100101", result.toBinaryString());

        PureFlags flag = new PureFlags();
        left = ToByteParser.parse("11111110");
        right = ToByteParser.parse("00000010");
        result = U2UnsignedBitsMathHelper.add(left, right, flag);
        assertEquals("00000000", result.toBinaryString());
        assertEquals(true, flag.isCarry());


    }

    @Test
    public void addUnsignedBytesTest2() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureFlags flags = new PureFlags();

                PureByte addResult = U2UnsignedBitsMathHelper.add(byteX, byteY, flags);

                boolean carryFlagShouldBe = x + y > 255 ? true : false;

                assertEquals(carryFlagShouldBe, flags.isCarry());
                assertEquals(ToByteParser.parse(x + y).toUnsignedInteger(), addResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void addBytesOverflowTest() {
        PureByte left = ToByteParser.parse("11111111");
        PureByte right = ToByteParser.parse("00000010");
        PureByte result = U2UnsignedBitsMathHelper.add(left, right);
        assertEquals("00000001", result.toBinaryString());
    }

    @Test
    public void subtractUnsignedBytes() {
        PureByte left = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2UnsignedBitsMathHelper.subtract(left, right);
        assertEquals("00000010", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte subtractResult = U2UnsignedBitsMathHelper.subtract(byteX, byteY);
                assertEquals(ToByteParser.parse(x - y).toUnsignedInteger(), subtractResult.toUnsignedInteger());
            }
        }
    }

    @Test
    public void subtractVersion2UnsignedBytesTest() {
        PureByte left = ToByteParser.parse("00000101");
        PureByte right = ToByteParser.parse("00000011");
        PureByte result = U2UnsignedBitsMathHelper.subtract2(left, right);
        assertEquals("00000010", result.toBinaryString());

        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte subtractResult = U2UnsignedBitsMathHelper.subtract2(byteX, byteY);
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

        for (int i = 1; i < 255; i++) {
            PureByte parse = ToByteParser.parse(i);
            assertEquals(i, parse.toUnsignedInteger());
        }
    }

    @Test
    public void multiply_For8Bit_Test() {
        for (int x = 0; x <= 255; x++) {
            for (int y = 0; y <= 255; y++) {
                PureFlags flag = new PureFlags();
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);


                if(x == 2 && y == 128){
                    System.out.println("DeBUG");
                }
                PureByte result = U2UnsignedBitsMathHelper.multiply(byteX, byteY, flag);
                System.out.println(x + " * " + y + " = " + result.toString());

                int testResult = x * y;
                boolean flagShouldBe = x * y > 255;
                assertEquals(flagShouldBe, flag.isCarry());
                assertEquals((byte) testResult, (byte) result.toUnsignedInteger());
            }
        }
    }

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {

    }

    @Test
    public void divideTest() {
        for (int x = 1; x <= 255; x++) {
            for (int y = 1; y <= 255; y++) {
                PureFlags flag = new PureFlags();
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte result = U2UnsignedBitsMathHelper.divide(byteX, byteY, flag);
                System.out.println(x + " / " + y + " = " + result.toString());

                int testResult = x / y;
                assertEquals((byte) testResult, (byte) result.toUnsignedInteger());
            }
        }
    }

    @Test
    public void restOfDivideTest() {
        for (int x = 1; x <= 255; x++) {
            for (int y = 1; y <= 255; y++) {
                PureFlags flag = new PureFlags();
                PureByte byteX = ToByteParser.parse(x);
                PureByte byteY = ToByteParser.parse(y);
                PureByte result = U2UnsignedBitsMathHelper.restOfDivide(byteX, byteY, flag);
                System.out.println(x + " % " + y + " = " + result.toString());

                int testResult = x % y;
                assertEquals((byte) testResult, (byte) result.toUnsignedInteger());
            }
        }
    }

    @Test
    public void alignToLeftTest1() {
        String fillZeros = new String(new char[24]).replace('\0', '0');
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignLeft(testByte, 32);
        assertEquals("01100011" + fillZeros, result.toBinaryString());
    }

    @Test
    public void alignToLeft_WithCutTheOverflowBits_Test() {
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignLeft(testByte, 4);
        assertEquals("0110", result.toBinaryString());
    }

    @Test
    public void alignToLeft_WithTheSameBitsLength_Test() {
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignLeft(testByte, 8);
        assertEquals("01100011", result.toBinaryString());
    }

    @Test
    public void alignToRightTest1() {
        String fillZeros = new String(new char[24]).replace('\0', '0');
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignRight(testByte, 32);
        assertEquals(fillZeros + "01100011", result.toBinaryString());
    }

    @Test
    public void alignToRight_WithCutTheOverflowBits_Test() {
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignRight(testByte, 4);
        assertEquals("0011", result.toBinaryString());
    }

    @Test
    public void alignToRight_WithTheSameBitsLength_Test() {
        PureByte testByte = ToByteParser.parse("01100011");
        PureByte result = U2UnsignedBitsMathHelper.alignRight(testByte, 8);
        assertEquals("01100011", result.toBinaryString());
    }
}
