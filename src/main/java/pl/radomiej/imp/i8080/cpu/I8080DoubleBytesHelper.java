package pl.radomiej.imp.i8080.cpu;

import lombok.experimental.UtilityClass;
import pl.radomiej.emu.logic.pure.PureByte;

import java.util.Arrays;

@UtilityClass
public class I8080DoubleBytesHelper {
    public PureByte merge(PureByte left, PureByte right) {
        PureByte pureByte = new PureByte(16);
        pureByte = pureByte.or(left);
        pureByte.shiftLeftInternal(8);
        pureByte = pureByte.or(right);
        return pureByte;
    }

    public PureByte[] setValue(PureByte pureByte) {
        PureByte temp = new PureByte(pureByte.getBites());
        PureByte rightValue = new PureByte(Arrays.copyOfRange(temp.getBites(), 8, 16));
        PureByte leftValue = new PureByte(Arrays.copyOfRange(temp.getBites(), 0, 8));

        return new PureByte[]{leftValue, rightValue};
    }
}
