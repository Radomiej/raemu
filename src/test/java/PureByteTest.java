import org.junit.Test;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.helpers.BitsMathHelper;
import pl.radomiej.emu.logic.helpers.TextByteParser;

import static junit.framework.TestCase.assertEquals;

public class PureByteTest {

    @Test
    public void byteParserTest() {
        PureByte testByte = TextByteParser.parse("00010111");


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


        PureByte testByte = TextByteParser.parse("00010111");
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

        //PureByte testByte = TextByteParser.parse("10111000");
    }

    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {

    }

    @Test
    public void addBytes(){
        PureByte left  = TextByteParser.parse("01100011");
        PureByte right = TextByteParser.parse("00000010");
        PureByte result = BitsMathHelper.addBits(left, right);
        assertEquals("01100101", result.toBinaryString());

    }
}
