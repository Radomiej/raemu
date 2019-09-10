package pl.radomiej.emu.peripheral;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.pure.PureByte;

public class ClockInterruptingUnit implements PeripheralDevice{
    private PureCPU cpu;
    private int intervalInCpuCycle = 1000;
    private String jumpInstructionTag;
    private int currentTick = 1;
    private boolean enabled = true;

    public ClockInterruptingUnit(PureCPU cpu, int intervalInCpuCycle, String jumpInstructionTag){
        this.cpu = cpu;
        this.intervalInCpuCycle = intervalInCpuCycle;
        this.jumpInstructionTag = jumpInstructionTag;
    }

    @Override
    public boolean turnOn() {
        enabled = true;
        return true;
    }

    @Override
    public boolean turnOff() {
        enabled = false;
        return true;
    }

    @Override
    public boolean preCpuTick() {
        if(currentTick % intervalInCpuCycle == 0) interruptingJump();
        return true;
    }

    @Override
    public boolean postCpuTick() {
        currentTick++;
        return true;
    }

    private void interruptingJump() {
        if(!cpu.getProgram().isTagExist(jumpInstructionTag)) return;
        cpu.getProgram().goTo(jumpInstructionTag);
    }
}
