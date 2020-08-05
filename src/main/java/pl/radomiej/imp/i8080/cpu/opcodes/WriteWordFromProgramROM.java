package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class WriteWordFromProgramROM implements Optcode<I8080CPU> {
    final private String toAddress;

    public WriteWordFromProgramROM(String toMemoryAddress) {
        this.toAddress = toMemoryAddress;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte byteLeft = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte value = I8080DoubleBytesHelper.merge(byteLeft, byteRight);

        int memoryIndex = i8080CPU.getRegistry(toAddress).getValue().toUnsignedInteger();
        PureByte rightValue = I8080DoubleBytesHelper.getRight(value);
        i8080CPU.getMemory().setByIndex(memoryIndex++, rightValue);
        PureByte leftValue = I8080DoubleBytesHelper.getLeft(value);
        i8080CPU.getMemory().setByIndex(memoryIndex, leftValue);
    }
}
