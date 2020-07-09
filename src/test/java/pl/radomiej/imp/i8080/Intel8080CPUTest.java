package pl.radomiej.imp.i8080;

import com.google.common.io.Resources;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.radomiej.emu.asm.pcm.babylon.Intel8080Babylon;
import pl.radomiej.emu.asm.pure.factory.IncrementAsmExample;
import pl.radomiej.emu.asm.pure.factory.PrintHelloWorldAsmExample;
import pl.radomiej.emu.board.RaBoard;
import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Intel8080CPUTest {

    @Test
    public void registryTest() throws IOException {

        I8080CPU myCPU = new I8080CPU(IncrementAsmExample.builder().build().createProgramData());
        Assertions.assertNotNull(myCPU.getRegistry("A"));
        Assertions.assertNotNull(myCPU.getRegistry("F"));
        Assertions.assertNotNull(myCPU.getRegistry("PSW"));

        Assertions.assertNotNull(myCPU.getRegistry("B"));
        Assertions.assertNotNull(myCPU.getRegistry("C"));
        Assertions.assertNotNull(myCPU.getRegistry("BC"));

        Assertions.assertNotNull(myCPU.getRegistry("D"));
        Assertions.assertNotNull(myCPU.getRegistry("E"));
        Assertions.assertNotNull(myCPU.getRegistry("DE"));

        Assertions.assertNotNull(myCPU.getRegistry("H"));
        Assertions.assertNotNull(myCPU.getRegistry("L"));
        Assertions.assertNotNull(myCPU.getRegistry("HL"));

        myCPU.getRegistry("B").setValue(ToByteParser.parse("11111110"));
        myCPU.getRegistry("C").setValue(ToByteParser.parse("00000001"));
        Assertions.assertEquals(ToByteParser.parse("1111111000000001"), myCPU.getRegistry("BC").getValue());

        myCPU.getRegistry("DE").setValue(ToByteParser.parse("1111111000000001"));
        Assertions.assertEquals(ToByteParser.parse("11111110"), myCPU.getRegistry("D").getValue());
        Assertions.assertEquals(ToByteParser.parse("00000001"), myCPU.getRegistry("E").getValue());
    }

    @Test
    public void simpleRunTest() {
        RaBoard raBoard = new RaBoard();
        raBoard.uploadProgram(PrintHelloWorldAsmExample.builder().writeMemoryCellIndex(32).build().createProgramData());
        raBoard.turnOn();

        long startTime = System.nanoTime();
        for(int i = 0; i < 1000000; i++) {
            raBoard.tick();
        }
        long stopTime = System.nanoTime();
        long executionTime = stopTime - startTime;
        System.out.println("Execution time: " + executionTime / 1000000 + " milis");
    }


}
