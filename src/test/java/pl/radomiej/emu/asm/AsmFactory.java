package pl.radomiej.emu.asm;

import pl.radomiej.emu.cpu.ProgramData;

public interface AsmFactory<T> {
    ProgramData<T> createProgramData();
}
