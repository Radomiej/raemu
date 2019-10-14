package pl.radomiej.emu.cpu.optcodes.pmc.memory;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;

public class SaveDirectPmc implements Optcode<PureCPU> {
    private final PureByte value;
    private final int to;

    public SaveDirectPmc(int to, String textValue) {
        this.to = to;
        this.value = ToByteParser.parse(textValue);
    }

    public SaveDirectPmc(int to, int value) {
        this.to = to;
        this.value = ToByteParser.parse(value);
    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getMemory().setByIndex(to, value);
    }
}
