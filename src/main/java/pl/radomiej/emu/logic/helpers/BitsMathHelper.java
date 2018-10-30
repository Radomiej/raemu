package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.PureFlags;

public class BitsMathHelper {
    public static PureByte addBits(PureByte left, PureByte right){
        return addBits(left, right, null);
    }

    public static PureByte addBits(PureByte left, PureByte right, PureFlags flags){
        PureByte result = new PureByte();
        boolean carry = false;
        int carryOut = 0;

        // Add all bits one by one
        for (int i = left.getLenght() -1 ; i >= 0 ; i--)
        {
            boolean firstBit = left.getBit(i).getValue();
            boolean secondBit = right.getBit(i).getValue();

            // boolean expression for sum of 3 bits
            int sum = 0;
            if(firstBit) sum++;
            if(secondBit) sum++;
            if(carry) sum++;

            boolean sumBool = sum % 2 == 1;

            result.setBit(i, sumBool);

            // boolean expression for 3-bit addition
            carry = (firstBit && secondBit) | (secondBit && carry) | (firstBit && carry);
        }

        // if overflow, then add a leading 1
        if (flags != null)
            flags.setCarry(carry);


        return result;
    }
}
