package pl.radomiej.emu.cpu.optcodes.pure.program;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class JumpDirect implements Optcode<PureCPU> {
    private int jumpIndex;

    public JumpDirect(int jumpIndex){
        this.jumpIndex = jumpIndex;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getProgram().jump(jumpIndex);
    }
}