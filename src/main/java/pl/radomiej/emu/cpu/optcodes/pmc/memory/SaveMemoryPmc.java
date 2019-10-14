package pl.radomiej.emu.cpu.optcodes.pmc.memory;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class SaveMemoryPmc implements Optcode<PmcCPU> {
    final private int to;

    public SaveMemoryPmc(int to) {
        this.to = to;
    }

    @Override
    public void execute(PmcCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(0);
        pureCPU.getMemory().setByIndex(to, value);
    }
}
