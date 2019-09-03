package pl.radomiej.emu;

import org.junit.Test;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.LoadDirect;
import pl.radomiej.emu.cpu.optcodes.LoadMemory;
import pl.radomiej.emu.cpu.ProgramData;

public class PureAssemblyTest {

    @Test
    public void optcodeTest() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new LoadDirect( "01010101"));
        program.add(new LoadMemory(10));
        PureCPU myCPU = new PureCPU(program);
        myCPU.tick();
        myCPU.tick();

//        System.out.println("B " + myCPU.getRegisters().getByCpuAddress(0b000));
    }
}
