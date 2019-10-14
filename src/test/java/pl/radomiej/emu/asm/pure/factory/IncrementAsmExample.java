package pl.radomiej.emu.asm.pure.factory;

import lombok.Builder;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.pure.alu.IncrementMemory;
import pl.radomiej.emu.cpu.optcodes.pure.program.JumpTag;
import pl.radomiej.emu.cpu.optcodes.pure.memory.SaveDirect;

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
