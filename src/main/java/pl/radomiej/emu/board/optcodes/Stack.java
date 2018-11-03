package pl.radomiej.emu.board.optcodes;

import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.pure.logic.Optcode;
import pl.radomiej.emu.pure.logic.PureByte;

public class Stack implements Optcode<PureCPU> {
    public Stack() {

    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(0);
        pureCPU.getMemory().getByIndex(1).setBites(value);
    }
}
