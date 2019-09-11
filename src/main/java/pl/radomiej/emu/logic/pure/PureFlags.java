package pl.radomiej.emu.logic.pure;

import lombok.Data;

@Data
public class PureFlags {
    private boolean carry;
    private boolean overflow;
    private boolean borrow;
}
