package pl.radomiej.emu.pure.logic;

public class PureFlags {
    private boolean carry;
    private boolean overflow;

    public boolean isCarry() {
        return carry;
    }

    public void setCarry(boolean carry) {
        this.carry = carry;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public void setOverflow(boolean overflow) {
        this.overflow = overflow;
    }
}
