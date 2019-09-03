package pl.radomiej.emu.tests;

import org.junit.jupiter.api.Test;
import pl.radomiej.emu.asm.factory.IncrementAsmExample;
import pl.radomiej.emu.cpu.PureCPU;

public class PerformanceTest {

    @Test
    public void simplePerformanceTest() {
        PureCPU myCPU = new PureCPU(IncrementAsmExample.builder().build().createProgramData());

        long startTime = System.nanoTime();
        for(int i = 0; i < 1000000; i++) {
            myCPU.tick();
        }
        long stopTime = System.nanoTime();
        long executionTime = stopTime - startTime;
        System.out.println("Execution time: " + executionTime / 1000000 + " milis");
    }


}
