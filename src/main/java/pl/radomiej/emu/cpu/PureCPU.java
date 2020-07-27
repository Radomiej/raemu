package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureFlags;

public class PureCPU implements CPU {
    private PureMemoryBank memory;
    private ProgramData program;
    private Flag flags;

    public PureCPU(ProgramData program) {
        this(program, 64, new PureFlags());
    }

    public PureCPU(ProgramData program, int architecture, Flag pureFlags) {
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

    public ProgramData<CPU> getProgram() {
        return program;
    }

    public void setProgram(ProgramData<CPU> program) {
        this.program = program;
    }

    @Override
    public void tick() {
        Optcode<CPU> next = program.getNext();
        next.execute(this);
    }

    @Override
    public Flag getFlags() {
        return flags;
    }
}
