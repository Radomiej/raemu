package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class MoveByteFromProgramROM implements Optcode<I8080CPU> {
    final private String to;

    public MoveByteFromProgramROM(String to) {
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte byteRight = i8080CPU.getDedicatedPD().getNextRaw();
        i8080CPU.getRegistry(to).setValue(byteRight);
    }
}
