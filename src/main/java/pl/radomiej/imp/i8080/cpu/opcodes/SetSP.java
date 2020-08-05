package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class SetSP implements Optcode<I8080CPU> {

    final private String from;

    public SetSP(String fromRegistry) {
        this.from = fromRegistry;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte fromByte = i8080CPU.getRegistry(from).getValue();
        i8080CPU.setSP(fromByte.toUnsignedInteger());
    }
}
