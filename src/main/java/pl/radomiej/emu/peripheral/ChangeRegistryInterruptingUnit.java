package pl.radomiej.emu.peripheral;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.pure.PureByte;

public class ChangeRegistryInterruptingUnit extends PeripheralDeviceDefault{
    private PureCPU cpu;
    private int observeMemoryCellIndex;
    private String jumpInstructionTag;

    public ChangeRegistryInterruptingUnit(PureCPU cpu, int observeMemoryCellIndex, String jumpInstructionTag){
        this.cpu = cpu;
        this.observeMemoryCellIndex = observeMemoryCellIndex;
        this.jumpInstructionTag = jumpInstructionTag;
    }

    @Override
    public boolean turnOn() {
        cpu.getMemory().addCellChangeListener(observeMemoryCellIndex, (oldValue, newValue) -> interruptingJump(oldValue, newValue));
        return true;
    }

    private void interruptingJump(PureByte oldValue, PureByte newValue) {
        if(newValue.isZero()) return;
        cpu.getProgram().goTo(jumpInstructionTag);
    }
}
