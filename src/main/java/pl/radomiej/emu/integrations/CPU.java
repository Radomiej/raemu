package pl.radomiej.emu.integrations;

import pl.radomiej.emu.logic.pure.PureFlags;

public interface CPU {
    void tick();
    PureFlags getFlags();
}
