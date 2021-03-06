package pl.radomiej.emu.cpu.optcodes;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2BitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.logic.pure.PureMemoryBank;

public class MultiplyMemory implements Optcode<PureCPU> {
    final private int a;
    final private int b;
    final private int c;

    public MultiplyMemory(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureMemoryBank memoryBank = pureCPU.getMemory();
        PureByte rA = memoryBank.getByIndex(a);
        PureByte rB = memoryBank.getByIndex(b);

        PureByte result = U2BitsMathHelper.multiply(rA, rB, pureCPU.getFlags());
        memoryBank.setByIndex(c, result);
    }
}
