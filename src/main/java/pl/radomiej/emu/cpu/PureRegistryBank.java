package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.pure.PureByte;

public interface PureRegistryBank {
    PureByte getValue();
    void setValue(PureByte pureByte);
    int getArchitecture();
}
