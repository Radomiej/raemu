package pl.radomiej.emu.asm.pure.factory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.opcodes.pure.program.Idle;
import pl.radomiej.emu.cpu.opcodes.pure.program.JumpTag;
import pl.radomiej.emu.cpu.opcodes.pure.memory.SaveDirect;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrintHelloWorldAsmExample implements AsmFactory<PureCPU> {
    private int writeMemoryCellIndex = 0;

    @Override
    public ProgramData<PureCPU> createProgramData() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new SaveDirect(writeMemoryCellIndex, 72));
        program.add(new SaveDirect(writeMemoryCellIndex, 101));
        program.add(new SaveDirect(writeMemoryCellIndex, 108));
        program.add(new SaveDirect(writeMemoryCellIndex, 108));
        program.add(new SaveDirect(writeMemoryCellIndex, 111));
        program.add(new SaveDirect(writeMemoryCellIndex, 32));
        program.add(new SaveDirect(writeMemoryCellIndex, 87));
        program.add(new SaveDirect(writeMemoryCellIndex, 111));
        program.add(new SaveDirect(writeMemoryCellIndex, 114));
        program.add(new SaveDirect(writeMemoryCellIndex, 108));
        program.add(new SaveDirect(writeMemoryCellIndex, 100));
        program.add(new SaveDirect(writeMemoryCellIndex, 33));
        program.add(new SaveDirect(writeMemoryCellIndex, 3));

        program.add(new Idle(), "idle");
        program.add(new JumpTag("idle"));
        return program;
    }
}
