package pl.radomiej.emu.tests;

import com.google.common.io.Resources;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Intel8080BoardTest {

    @Test
    public void bootloaderTest() throws IOException {
        URL binPath = Resources.getResource("Intel8080/monitor.bin");
        byte[] rawBin = Resources.toByteArray(binPath);
        List<PureByte> pureBin = new ArrayList<>(rawBin.length);
        Intel8080Babylon babylon = new Intel8080Babylon();
        for(int i = 0; i < rawBin.length; i++){
            char unsignedByte = (char) rawBin[i];
            PureByte pureByte = ToByteParser.parse(unsignedByte);
            pureBin.add(pureByte);
        }

        LinkedList<PureByte> pureStackBin = new LinkedList(pureBin);

        Optcode<PmcCPU> optcode = babylon.createOptcodeFromMachineCode(pureStackBin);
        System.out.println(optcode);

        PureCPU myCPU = new PureCPU(IncrementAsmExample.builder().build().createProgramData());

        long startTime = System.nanoTime();
        for(int i = 0; i < 1000000; i++) {
            myCPU.tick();
        }
        long stopTime = System.nanoTime();
        long executionTime = stopTime - startTime;
        System.out.println("Execution time: " + executionTime / 1000000 + " milis");
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