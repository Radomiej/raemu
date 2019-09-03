package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.pure.PureByte;

public interface CellChangeListener {
    void changeCellValue(PureByte oldValue, PureByte newValue);
}
