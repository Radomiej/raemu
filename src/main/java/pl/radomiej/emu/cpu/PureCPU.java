package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureFlags;

public class PureCPU implements CPU {
    private PureMemoryBank memory;
    private ProgramData<PureCPU> program;
    private Flag flags;

    public PureCPU(ProgramData<PureCPU> program) {
        this(program, 64, new PureFlags());
    }

    public PureCPU(ProgramData<PureCPU> program, int architecture, Flag pureFlags) {
        this.program = program;
        memory = new PureMemoryBank(architecture * architecture, architecture);
        flags = pureFlags;
    }

    public PureMemoryBank getMemory() {
        return memory;
    }

    public void setMemory(PureMemoryBank memory) {
        this.memory = memory;
    }

    public ProgramData<PureCPU> getProgram() {
        return program;
    }

    public void setProgram(ProgramData<PureCPU> program) {
        this.program = program;
    }

    @Override
    public void tick() {
        Optcode<PureCPU> next = program.getNext();
        next.execute(this);
    }

    @Override
    public Flag getFlags() {
        return flags;
    }
}
