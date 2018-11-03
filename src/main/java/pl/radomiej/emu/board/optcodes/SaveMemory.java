package pl.radomiej.emu.board.optcodes;

import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.pure.logic.Optcode;
import pl.radomiej.emu.pure.logic.PureByte;

public class SaveMemory implements Optcode<PureCPU> {
    final private int to;

    public SaveMemory(int to) {
        this.to = to;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(0);
        pureCPU.getMemory().getByIndex(to).setBites(value);
    }
}
