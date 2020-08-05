package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class MoveWordFromProgramROMAddress implements Optcode<I8080CPU> {
    final private String to;

    public MoveWordFromProgramROMAddress(String to) {
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte byteLeft = i8080CPU.getDedicatedPD().getNextRaw();

        int address = I8080DoubleBytesHelper.merge(byteLeft, byteRight).toUnsignedInteger();
        PureByte valueRight = i8080CPU.getMemory().getByIndex(address++);
        PureByte valueLeft = i8080CPU.getMemory().getByIndex(address);

        PureByte value = I8080DoubleBytesHelper.merge(valueLeft, valueRight);
        i8080CPU.getRegistry(to).setValue(value);
    }
}
