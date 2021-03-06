package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.pure.PureBit;

public class BitsPermutationHelper {
    public static PureBit[] circularShiftSingle(PureBit[] array, int shift, boolean right) {
        array = copy(array); //Defensive copy

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

    public static PureBit[] copy(PureBit[] bites) {
        PureBit[] copyBites = new PureBit[bites.length];
        for(int i = 0; i < bites.length; i++){
            copyBites[i] = bites[i];
        }
        return copyBites;
    }
}
