package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class MoveToMemory implements Optcode<I8080CPU> {
    final private String toAddress;
    final private String from;

    public MoveToMemory(String toAddress, String from) {
        this.toAddress = toAddress;
        this.from = from;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        int memoryIndex = i8080CPU.getRegistry(toAddress).getValue().toUnsignedInteger();
        PureByte pureByte = i8080CPU.getRegistry(from).getValue();
        i8080CPU.getMemory().setByIndex(memoryIndex, pureByte);
    }
}
