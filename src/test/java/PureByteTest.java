import org.junit.Test;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.helpers.TextByteParser;

import static junit.framework.TestCase.assertEquals;

public class PureByteTest {

    @Test
    public void byteParserTest() {
        PureByte testByte = TextByteParser.parse("00010111");


        assertEquals(true, testByte.getBit(0).getValue());
        assertEquals(true, testByte.getBit(1).getValue());
        assertEquals(true, testByte.getBit(2).getValue());
        assertEquals(false, testByte.getBit(3).getValue());
        assertEquals(true, testByte.getBit(4).getValue());
        assertEquals(false, testByte.getBit(5).getValue());
        assertEquals(false, testByte.getBit(6).getValue());
        assertEquals(false, testByte.getBit(7).getValue());
    }
    @Test
    public void multiplicationOfZeroIntegersShouldReturnZero() {


        PureByte testByte = TextByteParser.parse("00010111");
        testByte.rotateBitesLeft(3);
        System.out.print(testByte.toBinaryString());
        // assert statements
        assertEquals(false, testByte.getBit(0).getValue());
        assertEquals(false, testByte.getBit(1).getValue());
        assertEquals(false, testByte.getBit(2).getValue());
        assertEquals(true, testByte.getBit(3).getValue());
        assertEquals(true, testByte.getBit(4).getValue());
        assertEquals(true, testByte.getBit(5).getValue());
        assertEquals(false, testByte.getBit(6).getValue());
        assertEquals(true, testByte.getBit(7).getValue());

        //PureByte testByte = TextByteParser.parse("10111000");
    }
}
