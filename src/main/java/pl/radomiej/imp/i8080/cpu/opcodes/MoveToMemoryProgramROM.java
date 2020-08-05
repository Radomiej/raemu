package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class MoveToMemoryProgramROM implements Optcode<I8080CPU> {
    final private String from;

    public MoveToMemoryProgramROM(String fromRegistry) {
        this.from = fromRegistry;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte byteLeft = i8080CPU.getDedicatedPD().getNextRaw();

        PureByte mergeByte = I8080DoubleBytesHelper.merge(byteLeft, byteRight);
        int to = I8080DoubleBytesHelper.merge(byteLeft, byteRight).toUnsignedInteger();
        i8080CPU.getMemory().setByIndex(to, mergeByte);
    }
}
