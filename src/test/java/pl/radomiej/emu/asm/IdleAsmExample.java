package pl.radomiej.emu.asm;

import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.LoadDirect;
import pl.radomiej.emu.cpu.optcodes.LoadMemory;

public class IdleAsmExample implements AsmFactory<PureCPU> {
    @Override
    public ProgramData<PureCPU> createProgramData() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new LoadDirect( "01010101"));
        program.add(new LoadMemory(10));
        return program;
    }
}
