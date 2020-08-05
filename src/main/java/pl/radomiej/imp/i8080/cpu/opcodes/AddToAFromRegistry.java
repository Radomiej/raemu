package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class AddToAFromRegistry implements Optcode<I8080CPU> {
    final private String from;
    final private String to;

    public AddToAFromRegistry(String from) {
        this.from = from;
        this.to = "A";
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte fromByte = i8080CPU.getRegistry(from).getValue();
        PureByte acc = i8080CPU.getRegistry(to).getValue();
        PureByte result = U2UnsignedBitsMathHelper.add(fromByte, acc);
        i8080CPU.getRegistry(to).setValue(result);
    }
}
