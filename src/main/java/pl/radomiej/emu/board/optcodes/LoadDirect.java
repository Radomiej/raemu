package pl.radomiej.emu.board.optcodes;

import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.logic.helpers.ToByteParser;

public class LoadDirect implements Optcode<PureCPU> {
    final private PureByte value;

    public LoadDirect(String textValue) {
        this.value = ToByteParser.parse(textValue);
    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getMemory().getByIndex(0).setBites(value);
    }
}
