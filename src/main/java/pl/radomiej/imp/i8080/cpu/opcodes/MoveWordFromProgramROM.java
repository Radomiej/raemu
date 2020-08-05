package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class MoveWordFromProgramROM implements Optcode<I8080CPU> {
    final private String to;

    public MoveWordFromProgramROM(String to) {
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();
        PureByte byteLeft = i8080CPU.getDedicatedPD().getNextRaw();

        PureByte mergeByte = I8080DoubleBytesHelper.merge(byteLeft, byteRight);
        i8080CPU.getRegistry(to).setValue(mergeByte);
    }
}
