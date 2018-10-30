package pl.radomiej.emu.board.optcodes;

import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureByte;

public class LoadMemory implements Optcode<PureCPU> {
    final private int from;

    public LoadMemory(int from) {
        this.from = from;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(from);
        pureCPU.getMemory().getByIndex(0).setBites(value);
    }
}
