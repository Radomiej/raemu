package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.pure.PureFlags;

public interface CPU {
    void tick();
    Flag getFlags();
}
