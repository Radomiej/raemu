package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class AddToAFromROM implements Optcode<I8080CPU> {
    final private String to;

    public AddToAFromROM() {
        this.to = "A";
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte value = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte acc = i8080CPU.getRegistry(to).getValue();
        PureByte result = U2UnsignedBitsMathHelper.add(value, acc);
        i8080CPU.getRegistry(to).setValue(result);
    }
}
