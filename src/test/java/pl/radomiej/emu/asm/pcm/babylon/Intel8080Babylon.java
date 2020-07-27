package pl.radomiej.emu.asm.pcm.babylon;

import pl.radomiej.emu.cpu.OptcodeFactory;
import pl.radomiej.emu.cpu.PmcCPU;
import pl.radomiej.emu.cpu.opcodes.pmc.program.JumpDirectPmc;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.imp.i8080.cpu.I8080CPU;

import java.util.LinkedList;

public class Intel8080Babylon implements OptcodeFactory<I8080CPU> {

    public PureByte compile(Optcode<PmcCPU> optcode) {

        return null;
    }

    @Override
    public Optcode<I8080CPU> createOptcodeFromMachineCode(PureByte rawInstruction) {
        return null;
    }

    public Optcode<PmcCPU> createOptcodeFromMachineCode(LinkedList<PureByte> pureStackBin) {
        PureByte commandByte = pureStackBin.poll();
        String textCommand = commandByte.toBinaryString();
        int opcode = commandByte.toUnsignedInteger();


        if(textCommand.equals("11000011")) return jumpInstruction(pureStackBin);

        throw new UnsupportedOperationException("Given command is not supported: " + textCommand);
    }

    private Optcode<PmcCPU> jumpInstruction(LinkedList<PureByte> pureStackBin) {
        PureByte lb = pureStackBin.poll();
        PureByte hb = pureStackBin.poll();

        //TODO add support for double cell mode
        return new JumpDirectPmc(0);
    }
}
