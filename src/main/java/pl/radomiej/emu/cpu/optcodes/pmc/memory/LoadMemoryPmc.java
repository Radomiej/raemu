package pl.radomiej.emu.cpu.optcodes.pmc.memory;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class LoadMemoryPmc implements Optcode<PureCPU> {
    final private int from;

    public LoadMemoryPmc(int from) {
        this.from = from;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(from);
        pureCPU.getMemory().setByIndex(0, value);
    }
}
