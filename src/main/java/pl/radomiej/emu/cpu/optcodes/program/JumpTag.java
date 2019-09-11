package pl.radomiej.emu.cpu.optcodes.program;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.Optcode;

public class JumpTag implements Optcode<PureCPU> {
    private String jumpTag;

    public JumpTag(String jumpTag) {
        this.jumpTag = jumpTag;
    }

    @Override
    public void execute(PureCPU pureCPU) {
        pureCPU.getProgram().goTo(jumpTag);
    }
}