package pl.radomiej.emu.board.optcodes;

import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.helpers.ToByteParser;

public class Add implements Optcode<PureCPU> {
    final private int a;
    final private int b;
    final private int c;

    public Add(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureByte rA = pureCPU.getMemory().getByIndex(a);
        PureByte rB = pureCPU.getMemory().getByIndex(b);
        PureByte rC = pureCPU.getMemory().getByIndex(c);


    }
}
