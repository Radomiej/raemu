package pl.radomiej.emu.board;

import pl.radomiej.emu.integrations.CPU;
import pl.radomiej.emu.integrations.ProgramData;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureFlags;
import pl.radomiej.emu.logic.PureMemoryBank;

public class PureCPU implements CPU {
    private PureMemoryBank memory;
    private ProgramData<PureCPU> program;
    private PureFlags flags;

    public PureCPU(ProgramData<PureCPU> program) {
        this.program = program;
        memory = new PureMemoryBank(64);
        flags = new PureFlags();
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
    public PureFlags getFlags() {
        return flags;
    }
}
