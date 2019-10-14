package pl.radomiej.emu.cpu;

import lombok.Getter;
import lombok.NonNull;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PartialOptcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.logic.pure.PureFlags;

public class PmcCPU implements CPU {
    private PureMemoryBank memory;
    private PureFlags flags;
    @Getter
    private int pcRegistry;
    private PartialOptcode<PmcCPU> currentInstructionContext;
    private OptcodeFactory<PmcCPU> optcodeFactory;

    public PmcCPU(int pcRegister, @NonNull OptcodeFactory<PmcCPU> optcodeFactory) {
        memory = new PureMemoryBank(64 * 64 * 64, 64);
        flags = new PureFlags();
        this.pcRegistry = pcRegister;
        this.optcodeFactory = optcodeFactory;
    }

    public PureMemoryBank getMemory() {
        return memory;
    }

    public void setMemory(PureMemoryBank memory) {
        this.memory = memory;
    }

    @Override
    public void tick() {
        PureByte instructionAddress = memory.getByIndex(pcRegistry);
        PureByte instruction = memory.getByIndex(instructionAddress.toUnsignedInteger());

        Optcode<PmcCPU> next = null;
        if (currentInstructionContext != null) {
            next = currentInstructionContext.getNextOptcode();
            if (currentInstructionContext.isEndOfPartialOptcodes()) currentInstructionContext = null;
        } else {
            next = optcodeFactory.createOptcodeFromMachineCode(instruction);
        }

        if (next instanceof PartialOptcode) {
            if (currentInstructionContext != null)
                throw new UnsupportedOperationException("Indented partial optcode is not supported.");
            currentInstructionContext = (PartialOptcode) next;
            next = currentInstructionContext.getNextOptcode();

        }
        next.execute(this);
    }

    @Override
    public PureFlags getFlags() {
        return flags;
    }
}
