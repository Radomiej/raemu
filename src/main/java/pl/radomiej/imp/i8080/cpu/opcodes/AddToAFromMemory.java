package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.U2UnsignedBitsMathHelper;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class AddToAFromMemory implements Optcode<I8080CPU> {
    final private String fromAddress;
    final private String to;
    final private boolean useFlag;

    public AddToAFromMemory(String fromAddress, boolean useFlag) {
        this.fromAddress = fromAddress;
        this.useFlag = useFlag;
        this.to = "A";
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        int memoryIndex = i8080CPU.getRegistry(fromAddress).getValue().toUnsignedInteger();
        PureByte value = i8080CPU.getMemory().getByIndex(memoryIndex);
        PureByte acc = i8080CPU.getRegistry(to).getValue();
        PureByte result = U2UnsignedBitsMathHelper.add(value, acc);
        i8080CPU.getRegistry(to).setValue(result);
    }
}
