package pl.radomiej.emu.logic.helpers;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BinaryUtils {

    public int getPowerOfTwo(int exponent){
        return  (int)Math.pow(2, exponent);
    }
}
