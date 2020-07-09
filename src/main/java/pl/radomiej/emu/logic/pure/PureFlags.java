package pl.radomiej.emu.logic.pure;

import lombok.Data;
import pl.radomiej.emu.cpu.Flag;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Data
public class PureFlags implements Flag {
    private boolean carry;
    private boolean overflow;
    private boolean borrow;
    private boolean parity;

    @Override
    public boolean getBitByHumanName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public PureBit getPureBitByHumanName(String name) {
        throw new NotImplementedException();
    }

    @Override
    public boolean getBitByIndex(int index) {
        if (index == 0) return carry;
        else if (index == 1) return overflow;
        else if (index == 2) return borrow;
        throw new NotImplementedException();
    }

    @Override
    public PureBit getPureBitByIndex(int index) {
        throw new NotImplementedException();
    }

    @Override
    public void setBitByIndex(int index, boolean value) {
        if (index == 0) carry = value;
        else if (index == 1) overflow = value;
        else if (index == 2) borrow = value;
        throw new NotImplementedException();
    }

    @Override
    public boolean isCarry() {
        return carry;
    }

    @Override
    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    @Override
    public boolean isOverflow() {
        return overflow;
    }

    @Override
    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }

    @Override
    public boolean isBorrow() {
        return borrow;
    }

    @Override
    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    @Override
    public boolean isParity() {
        return parity;
    }

    @Override
    public void setParity(boolean parity) {
        this.parity = parity;
    }
}
