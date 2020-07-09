package pl.radomiej.emu.cpu.opcodes.pure.program;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class Idle implements Optcode<PureCPU> {
    public Idle() {

    }

    @Override
    public void execute(PureCPU pureCPU) {
        //Just IDLE
    }
}
