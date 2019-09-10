package pl.radomiej.emu.peripheral;

public interface PeripheralDevice {
    boolean turnOn();
    boolean turnOff();
    boolean preCpuTick();
    boolean postCpuTick();
}
