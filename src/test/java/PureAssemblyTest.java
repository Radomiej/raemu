import org.junit.Test;
import pl.radomiej.emu.board.PureCPU;
import pl.radomiej.emu.board.optcodes.LoadDirect;
import pl.radomiej.emu.board.optcodes.LoadMemory;
import pl.radomiej.emu.integrations.ProgramData;

public class PureAssemblyTest {

    @Test
    public void optcodeTest() {
        ProgramData<PureCPU> program = new ProgramData<PureCPU>();
        program.add(new LoadDirect( "01010101"));
        program.add(new LoadMemory(10));
        PureCPU myCPU = new PureCPU(program);
        myCPU.tick();
        myCPU.tick();

//        System.out.println("B " + myCPU.getRegisters().getByCpuAddress(0b000));
    }
}
