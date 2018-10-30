package pl.radomiej.emu.board.z80.optcodes;

import pl.radomiej.emu.board.z80.Z80;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.PureMemoryBank;
import pl.radomiej.emu.logic.helpers.TextByteParser;

public class LoadValueZ80Optcode implements Optcode<Z80> {
    final private int to;
    final private PureByte value;

    public LoadValueZ80Optcode(int to, String textValue) {
        this.to = to;
        this.value = TextByteParser.parse(textValue);
    }

    public void execute(Z80 z80) {
        PureMemoryBank registers = z80.getRegisters();
        registers.getByCpuAddress(to).setBites(value);

    }
}
