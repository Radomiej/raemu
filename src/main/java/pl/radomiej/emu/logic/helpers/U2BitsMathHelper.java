package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.PureByte;
import pl.radomiej.emu.logic.PureFlags;

public class U2BitsMathHelper {
    public static PureByte addBits(PureByte left, PureByte right){
        return addBits(left, right, null);
    }

    public static PureByte addBits(PureByte left, PureByte right, PureFlags flags){
        PureByte result = new PureByte();
        boolean carry = false;

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
    static PureByte subtract(PureByte x, PureByte y)
    {
        PureByte result = new PureByte();
        while(!y.isZero()){
            PureByte borrow = x.negation().and(y);
        }


        return result;
    }
    static int subtract(int x, int y)
    {

        // Iterate till there
        // is no carry
        while (y != 0)
        {
            // borrow contains common
            // set bits of y and unset
            // bits of x
            int borrow = (~x) & y;

            // Subtraction of bits of x
            // and y where at least one
            // of the bits is not set
            x = x ^ y;

            // Borrow is shifted by one
            // so that subtracting it from
            // x gives the required sum
            y = borrow << 1;
        }

        return x;
    }

}
