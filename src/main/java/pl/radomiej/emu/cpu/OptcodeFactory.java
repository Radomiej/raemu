package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public interface OptcodeFactory<T> {
    Optcode<T> createOptcodeFromMachineCode(PureByte rawInstruction);
}
