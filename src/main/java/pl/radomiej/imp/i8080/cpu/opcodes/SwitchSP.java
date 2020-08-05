package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.helpers.ToByteParser;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class SwitchSP implements Optcode<I8080CPU> {

    final private String from;

    public SwitchSP(String fromRegistry) {
        this.from = fromRegistry;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte value1 = i8080CPU.getRegistry(from).getValue();
        PureByte value2 = ToByteParser.parse16(i8080CPU.getSP());
        i8080CPU.setSP(value1.toUnsignedInteger());
        i8080CPU.getRegistry(from).setValue(value2);
    }
}
