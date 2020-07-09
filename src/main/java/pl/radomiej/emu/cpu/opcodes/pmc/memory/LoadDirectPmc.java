package pl.radomiej.emu.cpu.opcodes.pmc.memory;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;

public class LoadDirectPmc implements Optcode<PmcCPU> {
    final private PureByte value;

    public LoadDirectPmc(String textValue) {
        this.value = ToByteParser.parse(textValue);
    }

    @Override
    public void execute(PmcCPU pureCPU) {
        pureCPU.getMemory().setByIndex(0, value);
    }
}
