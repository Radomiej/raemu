package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

public class SwitchBetweenRegisters implements Optcode<I8080CPU> {
    final private String from;
    final private String to;

    public SwitchBetweenRegisters(String from, String to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        PureByte fromByteA = i8080CPU.getRegistry(from).getValue();
        PureByte fromByteB = i8080CPU.getRegistry(to).getValue();
        i8080CPU.getRegistry(from).setValue(fromByteB);
        i8080CPU.getRegistry(to).setValue(fromByteA);
    }
}
