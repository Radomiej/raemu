import org.junit.Test;
import pl.radomiej.emu.board.z80.Z80;
import pl.radomiej.emu.board.z80.optcodes.LoadDataBetweenRegistersZ80Optcode;
import pl.radomiej.emu.board.z80.optcodes.LoadValueZ80Optcode;
import pl.radomiej.emu.integrations.ProgramData;

public class Z80BoardTest {

    @Test
    public void optcodeTest() {
        ProgramData<Z80> program = new ProgramData<Z80>();
        program.add(new LoadValueZ80Optcode(0b001, "01010101"));
        program.add(new LoadDataBetweenRegistersZ80Optcode(0b000, 0b001));
        Z80 myCPU = new Z80(program);
        myCPU.tick();
        myCPU.tick();

        System.out.println("B " + myCPU.getRegisters().getByCpuAddress(0b000));
    }
}
