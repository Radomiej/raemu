package pl.radomiej.emu.asm.factory;

import lombok.Builder;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.Idle;
import pl.radomiej.emu.cpu.optcodes.IncrementMemory;
import pl.radomiej.emu.cpu.optcodes.JumpTag;
import pl.radomiej.emu.cpu.optcodes.SaveDirect;

@Builder
public class IncrementAsmExample implements AsmFactory<PureCPU> {
    @Override
    public ProgramData<PureCPU> createProgramData() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new SaveDirect(0, "00000000"));
        program.add(new IncrementMemory(0), "increment");
        program.add(new JumpTag("increment"));
        return program;
    }
}
