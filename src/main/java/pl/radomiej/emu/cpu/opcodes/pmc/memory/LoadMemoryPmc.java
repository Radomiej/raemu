package pl.radomiej.emu.cpu.opcodes.pmc.memory;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class LoadMemoryPmc implements Optcode<PmcCPU> {
    final private int from;

    public LoadMemoryPmc(int from) {
        this.from = from;
    }

    @Override
    public void execute(PmcCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(from);
        pureCPU.getMemory().setByIndex(0, value);
    }
}
