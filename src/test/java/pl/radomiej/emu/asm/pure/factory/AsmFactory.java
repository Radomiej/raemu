package pl.radomiej.emu.asm.pure.factory;

import pl.radomiej.emu.cpu.ProgramData;

public interface AsmFactory<T> {
    ProgramData<T> createProgramData();
}
