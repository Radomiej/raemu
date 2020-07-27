package pl.radomiej.imp.i8080.cpu.opcodes;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.I8080DoubleBytesHelper;

public class MoveFromMemoryCpWord implements Optcode<I8080CPU> {
    final private String to;

    public MoveFromMemoryCpWord(String to) {
        this.to = to;
    }

    @Override
    public void execute(I8080CPU i8080CPU) {
        int memoryPCIndex = i8080CPU.getPC();
        PureByte byteRight = i8080CPU.getMemory().getByIndex(memoryPCIndex++);
        PureByte byteLeft = i8080CPU.getMemory().getByIndex(memoryPCIndex++);
        i8080CPU.setPC(memoryPCIndex);

        int memoryIndex = I8080DoubleBytesHelper.merge(byteLeft, byteRight).toUnsignedInteger();
        PureByte pureByte = i8080CPU.getMemory().getByIndex(memoryIndex);
        i8080CPU.getRegistry(to).setValue(pureByte);
    }
}
