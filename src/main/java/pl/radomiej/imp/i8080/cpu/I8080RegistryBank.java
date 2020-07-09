package pl.radomiej.imp.i8080.cpu;

import pl.radomiej.emu.cpu.PureRegistryBank;
import pl.radomiej.emu.logic.pure.PureByte;

public class I8080RegistryBank implements PureRegistryBank {
    private PureByte value;

    @Override
    public PureByte getValue() {
        return value;
    }

    @Override
    public void setValue(PureByte pureByte) {
        value = pureByte;
    }

    @Override
    public int getArchitecture() {
        return 8;
    }
}
