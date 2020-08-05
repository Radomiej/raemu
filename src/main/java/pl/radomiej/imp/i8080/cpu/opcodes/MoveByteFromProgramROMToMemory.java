package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class MoveByteFromProgramROMToMemory implements Optcode<I8080CPU> {
    final private String toAddress;

    public MoveByteFromProgramROMToMemory(String toAddress) {
        this.toAddress = toAddress;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();

        int memoryIndex =  i8080CPU.getRegistry(toAddress).getValue().toUnsignedInteger();
        i8080CPU.getMemory().setByIndex(memoryIndex, byteRight);
    }
}
