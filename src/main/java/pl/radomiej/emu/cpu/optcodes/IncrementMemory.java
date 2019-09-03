package pl.radomiej.emu.cpu.optcodes;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2BitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.cpu.PureMemoryBank;

public class IncrementMemory implements Optcode<PureCPU> {
    final private int a;

    public IncrementMemory(int a) {
        this.a = a;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        PureMemoryBank memoryBank = pureCPU.getMemory();
        PureByte rA = memoryBank.getByIndex(a);

        PureByte result = U2BitsMathHelper.increment(rA);
        memoryBank.setByIndex(a, result);
    }
}
