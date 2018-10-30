package pl.radomiej.emu.board.z80;

import pl.radomiej.emu.integrations.CPU;
import pl.radomiej.emu.integrations.ProgramData;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.PureMemoryBank;

public class Z80 implements CPU {

    private PureMemoryBank registers;
    private PureMemoryBank ram;
    private PureByte flags;
    private ProgramData<Z80> program;

    public Z80(ProgramData<Z80> program) {
        this.program = program;

        registers = new PureMemoryBank(16);
        registers.giveName(0, "A", 0b111);
        registers.giveName(1, "B", 0b000);
        registers.giveName(2, "C", 0b001);
        registers.giveName(3, "D", 0b010);
        registers.giveName(4, "E", 0b011);
        registers.giveName(5, "F", 0b011);
        registers.giveName(6, "H", 0b100);
        registers.giveName(7, "L", 0b101);

        flags = registers.getByIndex(5); //F registers

        registers.mergeCells(0, 5, "AF"); //AF: Not used becouse F store flags
        registers.mergeCells(1, 2, "BC"); //BC
        registers.mergeCells(3, 4, "DE"); //DE
        registers.mergeCells(6, 7, "HL"); //HL
        registers.mergeCells(8, 9, "IX"); //IX
        registers.mergeCells(10, 11, "IY"); //IY
        registers.mergeCells(12, 13, "SP"); //SP
        registers.mergeCells(14, 15, "PC"); //PC

        ram = new PureMemoryBank(65536);
    }

    public PureMemoryBank getRegisters() {
        return registers;
    }

    public PureMemoryBank getMemory() {
        return ram;
    }

    public void tick() {
        Optcode<Z80> next = program.getNext();
        next.execute(this);
    }


}
