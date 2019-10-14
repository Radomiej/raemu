package pl.radomiej.emu.cpu.optcodes.pmc.alu;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.PureMemoryBank;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;

public class DivideUnsignedMemoryPmc implements Optcode<PureCPU> {
    final private int a;
    final private int b;
    final private int c;

    public DivideUnsignedMemoryPmc(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureMemoryBank memoryBank = pureCPU.getMemory();
        PureByte rA = memoryBank.getByIndex(a);
        PureByte rB = memoryBank.getByIndex(b);

        PureByte result = U2UnsignedBitsMathHelper.divide(rA, rB, pureCPU.getFlags());
        memoryBank.setByIndex(c, result);
    }
}
