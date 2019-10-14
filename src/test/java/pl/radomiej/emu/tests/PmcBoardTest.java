package pl.radomiej.emu.tests;

import org.junit.jupiter.api.Test;
import pl.radomiej.emu.asm.factory.IncrementAsmExample;
import pl.radomiej.emu.asm.factory.PrintHelloWorldAsmExample;
import pl.radomiej.emu.board.RaBoard;
import pl.radomiej.emu.cpu.PureCPU;

public class PmcBoardTest {

    @Test
    public void bootloaderTest() {
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
