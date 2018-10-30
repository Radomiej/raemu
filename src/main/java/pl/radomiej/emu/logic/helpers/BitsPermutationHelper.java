package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.PureBit;

public class BitsPermutationHelper {
    public static PureBit[] circularShiftSingle(PureBit[] array, int shift, boolean right) {
        PureBit[] array2 = new PureBit[shift];
        if(right) {
            for (int i = 0;i < shift;i++)
                array2[i] = array[array.length - shift + i];
            System.arraycopy(array, 0, array, shift, array.length - shift);
            for (int i = 0;i < shift;i++) {
                array[i] = array2[i];
            }
        }
        else {
            for (int i = 0;i < shift;i++)
                array2[i] = array[i];
            System.arraycopy(array, shift, array, 0, array.length - shift);
            for (int i = array.length - shift; i < array.length; i++) {
                array[i] = array2[shift + i - array.length];
            }
        }
        return array;
    }
}
