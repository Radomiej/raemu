package pl.radomiej.emu.cpu.opcodes.pmc.program;

import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.PureMemoryBank;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

public class JumpDirectPmc implements Optcode<PmcCPU> {
    private int jumpCellIndex;

    public JumpDirectPmc(int jumpCellIndex){
        this.jumpCellIndex = jumpCellIndex;
    }

    @Override
    public void execute(PmcCPU pmcCPU) {
        PureMemoryBank memory = pmcCPU.getMemory();
        PureByte instructionAddress = memory.getByIndex(jumpCellIndex);
        pmcCPU.getMemory().setByIndex(pmcCPU.getPcRegistry(), instructionAddress);
    }
}