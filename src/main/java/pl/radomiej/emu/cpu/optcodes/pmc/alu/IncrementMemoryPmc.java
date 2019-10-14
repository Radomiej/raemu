package pl.radomiej.emu.cpu.optcodes.pmc.alu;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.PureMemoryBank;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;

public class IncrementMemoryPmc implements Optcode<PmcCPU> {
    final private int a;

    public IncrementMemoryPmc(int a) {
        this.a = a;
    }

    @Override
    public void execute(PmcCPU pureCPU) {
        PureMemoryBank memoryBank = pureCPU.getMemory();
        PureByte rA = memoryBank.getByIndex(a);

        PureByte result = U2UnsignedBitsMathHelper.increment(rA);
        memoryBank.setByIndex(a, result);
    }
}
