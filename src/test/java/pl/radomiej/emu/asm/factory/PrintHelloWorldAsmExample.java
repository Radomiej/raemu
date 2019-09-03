package pl.radomiej.emu.asm.factory;

import lombok.Builder;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.optcodes.Idle;
import pl.radomiej.emu.cpu.optcodes.IncrementMemory;
import pl.radomiej.emu.cpu.optcodes.JumpTag;
import pl.radomiej.emu.cpu.optcodes.SaveDirect;

@Builder
public class PrintHelloWorldAsmExample implements AsmFactory<PureCPU> {
    @Override
    public ProgramData<PureCPU> createProgramData() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new SaveDirect(0, 72));
        program.add(new SaveDirect(0, 101));
        program.add(new SaveDirect(0, 108));
        program.add(new SaveDirect(0, 108));
        program.add(new SaveDirect(0, 111));
        program.add(new SaveDirect(0, 32));
        program.add(new SaveDirect(0, 87));
        program.add(new SaveDirect(0, 111));
        program.add(new SaveDirect(0, 114));
        program.add(new SaveDirect(0, 108));
        program.add(new SaveDirect(0, 100));
        program.add(new SaveDirect(0, 33));
        program.add(new SaveDirect(0, 3));

        program.add(new Idle(), "idle");
        program.add(new JumpTag("idle"));
        return program;
    }
}
