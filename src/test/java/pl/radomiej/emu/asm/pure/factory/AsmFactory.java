package pl.radomiej.emu.asm.pure.factory;

import pl.radomiej.emu.cpu.CPU;
import pl.radomiej.emu.cpu.ProgramData;

public interface AsmFactory<T extends CPU> {
    ProgramData<T> createProgramData();
}
