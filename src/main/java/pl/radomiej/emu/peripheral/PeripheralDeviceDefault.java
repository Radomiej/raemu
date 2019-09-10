package pl.radomiej.emu.peripheral;

public abstract class PeripheralDeviceDefault implements PeripheralDevice{
    @Override
    public boolean turnOn() {
        return true;
    }

    @Override
    public boolean turnOff() {
        return true;
    }

    @Override
    public boolean preCpuTick() {
        return true;
    }

    @Override
    public boolean postCpuTick() {
        return true;
    }
}
