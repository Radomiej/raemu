package pl.radomiej.emu.integrations;

import pl.radomiej.emu.pure.logic.PureFlags;

public interface CPU {
    void tick();
    PureFlags getFlags();
}
