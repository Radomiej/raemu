package pl.radomiej.emu.peripheral;

import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.logic.pure.PureByte;

public class ConsoleMemoryCellLoggerDevice extends PeripheralDeviceDefault{
    private PureCPU cpu;
    private int observeMemoryCellIndex;

    public ConsoleMemoryCellLoggerDevice(PureCPU cpu, int observeMemoryCellIndex){
        this.cpu = cpu;
        this.observeMemoryCellIndex = observeMemoryCellIndex;
    }

    @Override
    public boolean turnOn() {
        cpu.getMemory().addCellChangeListener(observeMemoryCellIndex, (oldValue, newValue) -> printChangeLog(oldValue, newValue));
        return true;
    }

    private void printChangeLog(PureByte oldValue, PureByte newValue) {
        String message = "Change Value in cell: " + observeMemoryCellIndex + " from: " + oldValue + " to: " + newValue;
        System.out.println(message);
    }
}
