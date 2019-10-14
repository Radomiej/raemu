package pl.radomiej.emu.cpu.optcodes.pmc.program;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class IdlePmc implements Optcode<PmcCPU> {
    public IdlePmc() {

    }

    @Override
    public void execute(PmcCPU pureCPU) {
        //Just IDLE
    }
}
