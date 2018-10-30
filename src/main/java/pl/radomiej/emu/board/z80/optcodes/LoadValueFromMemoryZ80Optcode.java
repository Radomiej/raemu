package pl.radomiej.emu.board.z80.optcodes;

import pl.radomiej.emu.board.z80.Z80;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.PureMemoryBank;
import pl.radomiej.emu.logic.helpers.TextByteParser;

public class LoadValueFromMemoryZ80Optcode implements Optcode<Z80> {
    final private int to;
    final private int from;

    public LoadValueFromMemoryZ80Optcode(int to, int from) {
        this.to = to;
        this.from = from;
    }

    public void execute(Z80 z80) {
        PureMemoryBank registers = z80.getRegisters();
        PureMemoryBank ram = z80.getMemory();
        registers.getByCpuAddress(to).setBites(ram.getByIndex(from));

    }
}
