package pl.radomiej.emu.peripheral;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.pure.PureByte;

public class SPIAsciiOutputDevice implements PeripheralDevice{
    private PureCPU cpu;
    private int observeMemoryCellIndex;

    private StringBuilder asciiBuffor = new StringBuilder();

    public SPIAsciiOutputDevice(PureCPU cpu, int observeMemoryCellIndex){
        this.cpu = cpu;
        this.observeMemoryCellIndex = observeMemoryCellIndex;
    }

    @Override
    public boolean turnOn() {
        cpu.getMemory().addCellChangeListener(observeMemoryCellIndex, (oldValue, newValue) -> processAscii(oldValue, newValue));
        return true;
    }

    private void processAscii(PureByte oldValue, PureByte newValue) {
        int value = newValue.toUnsignedInteger();
        if(value == 3){
            System.out.println(asciiBuffor.toString());
            asciiBuffor = new StringBuilder();
            return;
        }
        char asciiCharacter = (char)value;
        asciiBuffor.append(asciiCharacter);
    }
}
