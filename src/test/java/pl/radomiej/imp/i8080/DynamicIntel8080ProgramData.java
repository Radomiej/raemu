package pl.radomiej.imp.i8080;

import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.logic.Optcode;
import pl.radomiej.imp.i8080.cpu.I8080CPU;
import pl.radomiej.imp.i8080.cpu.opcodes.*;

import java.util.HashMap;

public class DynamicIntel8080ProgramData extends ProgramData<I8080CPU> {
    private I8080CPU myCPU;
    private HashMap<Integer, Optcode<I8080CPU>> opcodesMap = new HashMap<>();

    public DynamicIntel8080ProgramData(){
        /**
         *   case 0x7F: c->a = c->a; break; // MOV A,A
         *   case 0x78: c->a = c->b; break; // MOV A,B
         *   case 0x79: c->a = c->c; break; // MOV A,C
         *   case 0x7A: c->a = c->d; break; // MOV A,D
         *   case 0x7B: c->a = c->e; break; // MOV A,E
         *   case 0x7C: c->a = c->h; break; // MOV A,H
         *   case 0x7D: c->a = c->l; break; // MOV A,L
         *   case 0x7E: c->a = i8080_rb(c, i8080_get_hl(c)); break; // MOV A,M
         */
        opcodesMap.put(0x7F, new MoveBetweenRegisters("A", "A"));
        opcodesMap.put(0x78, new MoveBetweenRegisters("B", "A"));
        opcodesMap.put(0x79, new MoveBetweenRegisters("C", "A"));
        opcodesMap.put(0x7A, new MoveBetweenRegisters("D", "A"));
        opcodesMap.put(0x7B, new MoveBetweenRegisters("E", "A"));
        opcodesMap.put(0x7C, new MoveBetweenRegisters("H", "A"));
        opcodesMap.put(0x7D, new MoveBetweenRegisters("L", "A"));
        opcodesMap.put(0x7E, new MoveFromMemory("HL", "A"));

        /**
         *   case 0x0A: c->a = i8080_rb(c, i8080_get_bc(c)); break; // LDAX B
         *   case 0x1A: c->a = i8080_rb(c, i8080_get_de(c)); break; // LDAX D
         *   case 0x3A: c->a = i8080_rb(c, i8080_next_word(c)); break; // LDA word
         */

        opcodesMap.put(0x0A, new MoveFromMemory("BC", "A"));
        opcodesMap.put(0x1A, new MoveFromMemory("DE", "A"));
        opcodesMap.put(0x3A, new MoveFromMemoryCpWord("A"));

        /**
         *   case 0x47: c->b = c->a; break; // MOV B,A
         *   case 0x40: c->b = c->b; break; // MOV B,B
         *   case 0x41: c->b = c->c; break; // MOV B,C
         *   case 0x42: c->b = c->d; break; // MOV B,D
         *   case 0x43: c->b = c->e; break; // MOV B,E
         *   case 0x44: c->b = c->h; break; // MOV B,H
         *   case 0x45: c->b = c->l; break; // MOV B,L
         *   case 0x46: c->b = i8080_rb(c, i8080_get_hl(c)); break; // MOV B,M
         */
        opcodesMap.put(0x47, new MoveBetweenRegisters("A", "B"));
        opcodesMap.put(0x40, new MoveBetweenRegisters("B", "B"));
        opcodesMap.put(0x41, new MoveBetweenRegisters("C", "B"));
        opcodesMap.put(0x42, new MoveBetweenRegisters("D", "B"));
        opcodesMap.put(0x43, new MoveBetweenRegisters("E", "B"));
        opcodesMap.put(0x44, new MoveBetweenRegisters("H", "B"));
        opcodesMap.put(0x45, new MoveBetweenRegisters("L", "B"));
        opcodesMap.put(0x46, new MoveFromMemory("HL", "B"));

        /**
         *   case 0x4F: c->c = c->a; break; // MOV C,A
         *   case 0x48: c->c = c->b; break; // MOV C,B
         *   case 0x49: c->c = c->c; break; // MOV C,C
         *   case 0x4A: c->c = c->d; break; // MOV C,D
         *   case 0x4B: c->c = c->e; break; // MOV C,E
         *   case 0x4C: c->c = c->h; break; // MOV C,H
         *   case 0x4D: c->c = c->l; break; // MOV C,L
         *   case 0x4E: c->c = i8080_rb(c, i8080_get_hl(c)); break; // MOV C,M
         */
        opcodesMap.put(0x4F, new MoveBetweenRegisters("A", "C"));
        opcodesMap.put(0x48, new MoveBetweenRegisters("B", "C"));
        opcodesMap.put(0x49, new MoveBetweenRegisters("C", "C"));
        opcodesMap.put(0x4A, new MoveBetweenRegisters("D", "C"));
        opcodesMap.put(0x4B, new MoveBetweenRegisters("E", "C"));
        opcodesMap.put(0x4C, new MoveBetweenRegisters("H", "C"));
        opcodesMap.put(0x4D, new MoveBetweenRegisters("L", "C"));
        opcodesMap.put(0x4E, new MoveFromMemory("HL", "C"));

        /**
         *   case 0x57: c->d = c->a; break; // MOV D,A
         *   case 0x50: c->d = c->b; break; // MOV D,B
         *   case 0x51: c->d = c->c; break; // MOV D,C
         *   case 0x52: c->d = c->d; break; // MOV D,D
         *   case 0x53: c->d = c->e; break; // MOV D,E
         *   case 0x54: c->d = c->h; break; // MOV D,H
         *   case 0x55: c->d = c->l; break; // MOV D,L
         *   case 0x56: c->d = i8080_rb(c, i8080_get_hl(c)); break; // MOV D,M
         */
        opcodesMap.put(0x57, new MoveBetweenRegisters("A", "D"));
        opcodesMap.put(0x50, new MoveBetweenRegisters("B", "D"));
        opcodesMap.put(0x51, new MoveBetweenRegisters("C", "D"));
        opcodesMap.put(0x52, new MoveBetweenRegisters("D", "D"));
        opcodesMap.put(0x53, new MoveBetweenRegisters("E", "D"));
        opcodesMap.put(0x54, new MoveBetweenRegisters("H", "D"));
        opcodesMap.put(0x55, new MoveBetweenRegisters("L", "D"));
        opcodesMap.put(0x56, new MoveFromMemory("HL", "D"));

        /**
         *   case 0x5F: c->e = c->a; break; // MOV E,A
         *   case 0x58: c->e = c->b; break; // MOV E,B
         *   case 0x59: c->e = c->c; break; // MOV E,C
         *   case 0x5A: c->e = c->d; break; // MOV E,D
         *   case 0x5B: c->e = c->e; break; // MOV E,E
         *   case 0x5C: c->e = c->h; break; // MOV E,H
         *   case 0x5D: c->e = c->l; break; // MOV E,L
         *   case 0x5E: c->e = i8080_rb(c, i8080_get_hl(c)); break; // MOV E,M
         */
        opcodesMap.put(0x5F, new MoveBetweenRegisters("A", "E"));
        opcodesMap.put(0x58, new MoveBetweenRegisters("B", "E"));
        opcodesMap.put(0x59, new MoveBetweenRegisters("C", "E"));
        opcodesMap.put(0x5A, new MoveBetweenRegisters("D", "E"));
        opcodesMap.put(0x5B, new MoveBetweenRegisters("E", "E"));
        opcodesMap.put(0x5C, new MoveBetweenRegisters("H", "E"));
        opcodesMap.put(0x5D, new MoveBetweenRegisters("L", "E"));
        opcodesMap.put(0x5E, new MoveFromMemory("HL", "E"));

        /**
         *   case 0x67: c->h = c->a; break; // MOV H,A
         *   case 0x60: c->h = c->b; break; // MOV H,B
         *   case 0x61: c->h = c->c; break; // MOV H,C
         *   case 0x62: c->h = c->d; break; // MOV H,D
         *   case 0x63: c->h = c->e; break; // MOV H,E
         *   case 0x64: c->h = c->h; break; // MOV H,H
         *   case 0x65: c->h = c->l; break; // MOV H,L
         *   case 0x66: c->h = i8080_rb(c, i8080_get_hl(c)); break; // MOV H,M
         */
        opcodesMap.put(0x67, new MoveBetweenRegisters("A", "H"));
        opcodesMap.put(0x60, new MoveBetweenRegisters("B", "H"));
        opcodesMap.put(0x61, new MoveBetweenRegisters("C", "H"));
        opcodesMap.put(0x62, new MoveBetweenRegisters("D", "H"));
        opcodesMap.put(0x63, new MoveBetweenRegisters("E", "H"));
        opcodesMap.put(0x64, new MoveBetweenRegisters("H", "H"));
        opcodesMap.put(0x65, new MoveBetweenRegisters("L", "H"));
        opcodesMap.put(0x66, new MoveFromMemory("HL", "H"));

        /**
         *   case 0x6F: c->l = c->a; break; // MOV L,A
         *   case 0x68: c->l = c->b; break; // MOV L,B
         *   case 0x69: c->l = c->c; break; // MOV L,C
         *   case 0x6A: c->l = c->d; break; // MOV L,D
         *   case 0x6B: c->l = c->e; break; // MOV L,E
         *   case 0x6C: c->l = c->h; break; // MOV L,H
         *   case 0x6D: c->l = c->l; break; // MOV L,L
         *   case 0x6E: c->l = i8080_rb(c, i8080_get_hl(c)); break; // MOV L,M
         */
        opcodesMap.put(0x6F, new MoveBetweenRegisters("A", "L"));
        opcodesMap.put(0x68, new MoveBetweenRegisters("B", "L"));
        opcodesMap.put(0x69, new MoveBetweenRegisters("C", "L"));
        opcodesMap.put(0x6A, new MoveBetweenRegisters("D", "L"));
        opcodesMap.put(0x6B, new MoveBetweenRegisters("E", "L"));
        opcodesMap.put(0x6C, new MoveBetweenRegisters("H", "L"));
        opcodesMap.put(0x6D, new MoveBetweenRegisters("L", "L"));
        opcodesMap.put(0x6E, new MoveFromMemory("HL", "L"));

        /**
         *   case 0x77: i8080_wb(c, i8080_get_hl(c), c->a); break; // MOV M,A
         *   case 0x70: i8080_wb(c, i8080_get_hl(c), c->b); break; // MOV M,B
         *   case 0x71: i8080_wb(c, i8080_get_hl(c), c->c); break; // MOV M,C
         *   case 0x72: i8080_wb(c, i8080_get_hl(c), c->d); break; // MOV M,D
         *   case 0x73: i8080_wb(c, i8080_get_hl(c), c->e); break; // MOV M,E
         *   case 0x74: i8080_wb(c, i8080_get_hl(c), c->h); break; // MOV M,H
         *   case 0x75: i8080_wb(c, i8080_get_hl(c), c->l); break; // MOV M,L
         */

        opcodesMap.put(0x77, new MoveToMemory("HL", "A"));
        opcodesMap.put(0x70, new MoveToMemory("HL", "B"));
        opcodesMap.put(0x71, new MoveToMemory("HL", "C"));
        opcodesMap.put(0x72, new MoveToMemory("HL", "D"));
        opcodesMap.put(0x73, new MoveToMemory("HL", "E"));
        opcodesMap.put(0x74, new MoveToMemory("HL", "H"));
        opcodesMap.put(0x75, new MoveToMemory("HL", "L"));

        /**
         *   case 0x3E: c->a = i8080_next_byte(c); break; // MVI A,byte
         *   case 0x06: c->b = i8080_next_byte(c); break; // MVI B,byte
         *   case 0x0E: c->c = i8080_next_byte(c); break; // MVI C,byte
         *   case 0x16: c->d = i8080_next_byte(c); break; // MVI D,byte
         *   case 0x1E: c->e = i8080_next_byte(c); break; // MVI E,byte
         *   case 0x26: c->h = i8080_next_byte(c); break; // MVI H,byte
         *   case 0x2E: c->l = i8080_next_byte(c); break; // MVI L,byte
         */
        opcodesMap.put(0x3E, new MoveFromMemoryCpByte("A"));
        opcodesMap.put(0x06, new MoveFromMemoryCpByte("B"));
        opcodesMap.put(0x0E, new MoveFromMemoryCpByte("C"));
        opcodesMap.put(0x16, new MoveFromMemoryCpByte("D"));
        opcodesMap.put(0x1E, new MoveFromMemoryCpByte("E"));
        opcodesMap.put(0x26, new MoveFromMemoryCpByte("H"));
        opcodesMap.put(0x2E, new MoveFromMemoryCpByte("L"));
    }

    public void setCpu(I8080CPU myCPU) {
        this.myCPU = myCPU;
    }

    @Override
    public Optcode<I8080CPU> getNext() {
        int memoryPCIndex = myCPU.getPC();
        int opcode = myCPU.getMemory().getByIndex(memoryPCIndex).toUnsignedInteger();
        memoryPCIndex++;
        myCPU.setPC(memoryPCIndex);

        return opcodesMap.get(opcode);
    }
}
