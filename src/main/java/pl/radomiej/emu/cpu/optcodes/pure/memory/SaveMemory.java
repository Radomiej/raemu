package pl.radomiej.emu.cpu.optcodes.pure.memory;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class SaveMemory implements Optcode<PureCPU> {
    final private int to;

    public SaveMemory(int to) {
        this.to = to;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(0);
        pureCPU.getMemory().setByIndex(to, value);
    }
}
