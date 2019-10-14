package pl.radomiej.emu.cpu.optcodes.pmc.memory;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;

public class LoadDirectPmc implements Optcode<PureCPU> {
    final private PureByte value;

    public LoadDirectPmc(String textValue) {
        this.value = ToByteParser.parse(textValue);
    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getMemory().setByIndex(0, value);
    }
}
