package pl.radomiej.emu.asm.pcm.babylon;

import pl.radomiej.emu.cpu.OptcodeFactory;
import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.optcodes.pmc.program.JumpDirectPmc;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;

import java.util.LinkedList;
import java.util.Stack;

public class Intel8080Babylon implements OptcodeFactory<PmcCPU> {

    public PureByte compile(Optcode<PmcCPU> optcode) {

        return null;
    }

    @Override
    public Optcode<PmcCPU> createOptcodeFromMachineCode(PureByte rawInstruction) {
        return null;
    }

    public Optcode<PmcCPU> createOptcodeFromMachineCode(LinkedList<PureByte> pureStackBin) {
        PureByte commandByte = pureStackBin.poll();
        String textCommand = commandByte.toBinaryString();

        if(textCommand.equals("11000011")) return jumpInstruction(pureStackBin);

        throw new UnsupportedOperationException("Given command is not supported: " + textCommand);
    }

    private Optcode<PmcCPU> jumpInstruction(LinkedList<PureByte> pureStackBin) {
        PureByte lb = pureStackBin.poll();
        PureByte hb = pureStackBin.poll();

        //TODO add support for duple cell mode
        return new JumpDirectPmc(0);
    }
}
