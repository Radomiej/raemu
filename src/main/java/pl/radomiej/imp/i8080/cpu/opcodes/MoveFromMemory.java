package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class MoveFromMemory implements Optcode<I8080CPU> {
    final private String from;
    final private String to;

    public MoveFromMemory(String fromAddress, String to) {
        this.from = fromAddress;
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        int memoryIndex = i8080CPU.getRegistry(from).getValue().toUnsignedInteger();
        PureByte pureByte = i8080CPU.getMemory().getByIndex(memoryIndex);
        i8080CPU.getRegistry(to).setValue(pureByte);
    }
}
