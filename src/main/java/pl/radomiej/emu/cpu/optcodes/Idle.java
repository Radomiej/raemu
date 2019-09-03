package pl.radomiej.emu.cpu.optcodes;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class Idle implements Optcode<PureCPU> {
    public Idle() {

    }

    @Override
    public void execute(PureCPU pureCPU) {
        //Just IDLE
    }
}
