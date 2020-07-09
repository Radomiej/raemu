package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.pure.PureBit;

public interface Flag {
    boolean getBitByHumanName(String name);
    PureBit getPureBitByHumanName(String name);
    boolean getBitByIndex(int index);
    PureBit getPureBitByIndex(int index);
    void setBitByIndex(int index, boolean value);

    boolean isCarry();
    void setCarry(boolean carry);

    boolean isOverflow();
    void setOverflow(boolean overflow);

    boolean isBorrow();
    void setBorrow(boolean borrow);

    boolean isParity();
    void setParity(boolean parity);
}
