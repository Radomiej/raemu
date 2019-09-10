package pl.radomiej.emu.cpu.optcodes;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class Return implements Optcode<PureCPU> {
    public Return() {

    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getProgram().returnToUpIndex();
    }
}
