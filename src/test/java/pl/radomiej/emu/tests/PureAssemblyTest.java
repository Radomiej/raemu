package pl.radomiej.emu.tests;

import org.junit.jupiter.api.Test;
import pl.radomiej.emu.asm.factory.IdleAsmExample;
import pl.radomiej.emu.asm.factory.IncrementAsmExample;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PureAssemblyTest {

    @Test
    public void Idle_And_JumpTag_Test() {
        PureCPU myCPU = new PureCPU(IdleAsmExample.builder().build().createProgramData());
        for(int i = 0; i < 100; i++) {
            assertEquals(i % 2,  myCPU.getProgram().getCurrent());
            myCPU.tick();
            assertEquals((i + 1) % 2,  myCPU.getProgram().getCurrent());
        }
    }

    @Test
    public void MemoryCellChangeValueListener_Test() {
        PureCPU myCPU = new PureCPU(IncrementAsmExample.builder().build().createProgramData());

        final List<PureByte> listeningValues = new ArrayList<>(100);
        myCPU.getMemory().addCellChangeListener(0, (oldValue, newValue) -> {listeningValues.add(newValue);});

        myCPU.tick();
        assertEquals(1,  listeningValues.size());
        assertEquals(ToByteParser.parse(0),  listeningValues.get(0));
        for(int i = 2; i < 100; i++) {
            myCPU.tick();
            if(i % 2 == 0) { //Perform Check
                int half = i / 2;
                assertEquals(half + 1, listeningValues.size());
                assertEquals(ToByteParser.parse(half), listeningValues.get(half));
            }
        }

        assertEquals(50,  listeningValues.size());
    }
}
