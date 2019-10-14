package pl.radomiej.emu.cpu.optcodes.pmc.program;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class IdlePmc implements Optcode<PureCPU> {
    public IdlePmc() {

    }

    @Override
    public void execute(PureCPU pureCPU) {
        //Just IDLE
    }
}
