package pl.radomiej.emu.cpu.opcodes.pmc.memory;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class StackPmc implements Optcode<PmcCPU> {
    public StackPmc() {

    }

    @Override
    public void execute(PmcCPU pureCPU) {
        PureByte value = pureCPU.getMemory().getByIndex(0);
        pureCPU.getMemory().setByIndex(1, value);
    }
}
