package pl.radomiej.emu.board;

import pl.radomiej.emu.asm.factory.IdleAsmExample;
import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.peripheral.ClockInterruptingUnit;
import pl.radomiej.emu.peripheral.PeripheralDevice;
import pl.radomiej.emu.peripheral.SPIAsciiOutputDevice;

/**
 * 64-bit board.
 */
public class RaBoard{
    private PureCPU myCPU;
    private PeripheralDevice clock;
    private PeripheralDevice asciLogger;
    private PeripheralDevice keyboard;
    private PeripheralDevice monitor;

    public RaBoard(){
        uploadProgram(IdleAsmExample.builder().build().createProgramData());
    }

    public void uploadProgram(ProgramData<PureCPU> programDataToSave){
        myCPU = new PureCPU(programDataToSave);
        clock = new ClockInterruptingUnit(myCPU, 1000, "int_clock");
        asciLogger = new SPIAsciiOutputDevice(myCPU, 32);
    }

    public void turnOn(){
        clock.turnOn();
        asciLogger.turnOn();
    }

    public void tick(){
        clock.preCpuTick();
        myCPU.tick();
        clock.postCpuTick();
    }
}
