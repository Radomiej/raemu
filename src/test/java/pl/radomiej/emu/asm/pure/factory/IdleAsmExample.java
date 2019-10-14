package pl.radomiej.emu.asm.pure.factory;

import lombok.Builder;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.pure.program.Idle;
import pl.radomiej.emu.cpu.optcodes.pure.program.JumpTag;

@Builder
public class IdleAsmExample implements AsmFactory<PureCPU> {
    @Override
    public ProgramData<PureCPU> createProgramData() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new Idle(), "start");
        program.add(new JumpTag("start"));
        return program;
    }
}