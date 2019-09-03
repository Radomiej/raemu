package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.pure.PureByte;
import pl.radomiej.emu.logic.pure.PureFlags;

public class U2BitsMathHelper {
    public static PureByte add(PureByte left, PureByte right) {
        return add(left, right, null);
    }

    public static PureByte add(PureByte left, PureByte right, PureFlags flags) {
        int maxBits = Math.max(left.getLength(), right.getLength());
        PureByte result = new PureByte(maxBits);
        boolean carry = false;

        // AddMemory all bits one by one
        for (int i = left.getLength() - 1; i >= 0; i--) {
            boolean firstBit = left.getBit(i).getValue();
            boolean secondBit = right.getBit(i).getValue();

            // boolean expression for sum of 3 bits
            int sum = 0;
            if (firstBit) sum++;
            if (secondBit) sum++;
            if (carry) sum++;

            boolean sumBool = sum % 2 == 1;

            result.setBit(i, sumBool);

            // boolean expression for 3-bit addition
            carry = (firstBit && secondBit) | (secondBit && carry) | (firstBit && carry);
        }

        // if overflow, then add a leading 1
        if (flags != null) {
            flags.setCarry(carry);
            System.out.println("carry: " + carry);
        }


        return result;
    }

    static public PureByte subtract2(PureByte x, PureByte y) {
        return subtract2(x, y, null);
    }

    static public PureByte subtract2(PureByte x, PureByte y, PureFlags flags) {
        if (flags != null && x.lessThan(y)) flags.setOverflow(true);

        while (!y.isZero()) {
            PureByte borrow = x.negation().and(y);
            x = x.xor(y);
            y = borrow.shiftLeft();
        }

        return x;
    }

    static public PureByte subtract(PureByte x, PureByte y) {
        return subtract(x, y, null);
    }

    static public PureByte subtract(PureByte x, PureByte y, PureFlags flags) {
        if (flags != null && x.lessThan(y)) flags.setOverflow(true);

        PureByte complement = y.negation();
        PureByte increment = new PureByte(complement.getLength());
        increment.setBit(complement.getLength() - 1, true);
        complement = add(complement, increment);
        PureByte result = add(x, complement);

        return result;
    }

    //TODO AddMemory support for flags
    static public PureByte divide(PureByte x, PureByte y, PureFlags flags) {
        if (flags == null) flags = new PureFlags();

        PureByte a = x.copy();
        PureByte b = y.copy();

        //Prepare
        int maxBits = Math.max(a.getLength(), b.getLength());

        PureByte quotient = new PureByte(maxBits);
        PureByte partial = new PureByte(maxBits);
        PureByte dividend = alignLeft(a, maxBits);
        PureByte divisor = alignLeft(b, maxBits);

        for (int leftmostIndex = 0; leftmostIndex < maxBits; leftmostIndex++) {
            boolean dividendBit = dividend.getBit(leftmostIndex).getValue();
            boolean divisorBit = divisor.getBit(leftmostIndex).getValue();

            partial = partial.shiftLeft();
            if (dividendBit) partial = increment(partial);

            boolean bitToSetInQuotient = partial.greaterOrEqualsThan(divisor);
            quotient.setBit(leftmostIndex, bitToSetInQuotient);
            if (bitToSetInQuotient) {
                partial = subtract(partial, divisor);
            }

        }

        return alignRight(quotient,maxBits);
    }

    public static PureByte restOfDivide(PureByte x, PureByte y, PureFlags flags) {
        if (flags == null) flags = new PureFlags();

        PureByte a = x.copy();
        PureByte b = y.copy();

        //Prepare
        int maxBits = Math.max(a.getLength(), b.getLength());

        PureByte quotient = new PureByte(maxBits);
        PureByte partial = new PureByte(maxBits);
        PureByte dividend = alignLeft(a, maxBits);
        PureByte divisor = alignLeft(b, maxBits);

        for (int leftmostIndex = 0; leftmostIndex < maxBits; leftmostIndex++) {
            boolean dividendBit = dividend.getBit(leftmostIndex).getValue();
            boolean divisorBit = divisor.getBit(leftmostIndex).getValue();

            partial = partial.shiftLeft();
            if (dividendBit) partial = increment(partial);

            boolean bitToSetInQuotient = partial.greaterOrEqualsThan(divisor);
            quotient.setBit(leftmostIndex, bitToSetInQuotient);
            if (bitToSetInQuotient) {
                partial = subtract(partial, divisor);
            }

        }

        return alignRight(partial, maxBits);
    }

    public static PureByte alignRight(PureByte byteToAlign, int bitsSize) {
        PureByte result = new PureByte(bitsSize);
        int leftmostBitOffset = 0;
        for (int resultIndex = result.getLength() - 1; resultIndex >= 0 && leftmostBitOffset < byteToAlign.getLength(); resultIndex--) {
            boolean bitToAlign = byteToAlign.getBit(byteToAlign.getLength() - 1 - leftmostBitOffset++).getValue();
            result.setBit(resultIndex, bitToAlign);
        }

        return result;
    }

    public static PureByte alignLeft(PureByte byteToAlign, int bitsSize) {
        PureByte result = new PureByte(bitsSize);
        int leftmostBitOffset = 0;
        for (int resultIndex = 0; resultIndex < bitsSize && leftmostBitOffset < byteToAlign.getLength(); resultIndex++) {
            boolean bitToAlign = byteToAlign.getBit(leftmostBitOffset++).getValue();
            result.setBit(resultIndex, bitToAlign);
        }

        return result;
    }

    static public PureByte multiply(PureByte left, PureByte right, PureFlags flags) {
        int maxBits = Math.max(left.getLength(), right.getLength());
        int computeLength = maxBits * 2;

        if (flags == null) flags = new PureFlags();
        PureByte one = new PureByte(left.getLength());
        one = increment(one);


        PureByte a = new PureByte(computeLength, left);
        PureByte b = right.copy();
        PureByte score = new PureByte(computeLength);
        while (!b.isZero()) {
            if (!b.and(one).isZero()) {
                score = add(score, a, flags);
            }


            a = a.rotateBitesLeft(1);
            b = b.shiftRight();
        }

        PureByte result = alignRight(score, maxBits);
        if(score.greaterThan(alignRight(result, computeLength))) flags.setCarry(true);
        return result;
    }

    private static PureByte decrement(PureByte value) {
        PureByte one = new PureByte(value.getLength());
        one.setBit(value.getLength() - 1, true);
        return subtract(value, one);
    }

    public static PureByte increment(PureByte value) {
        PureByte one = new PureByte(value.getLength());
        one.setBit(value.getLength() - 1, true);
        return add(value, one);
    }

    public static void bitwiseMultiply(int n1, int n2) {
        /* This value will hold n1 * 2^i for varying values of i.  It will
         * start off holding n1 * 2^0 = n1, and after each iteration will
         * be updated to hold the next term in the sequence.
         */
        int a = n1;

        /* This value will be used to read the individual bits out of n2.
         * We'll use the shifting trick to read the bits and will maintain
         * the invariant that after i iterations, b is equal to n2 >> i.
         */
        int b = n2;

        /* This value will hold the sum of the terms so far. */
        int result = 0;

        /* Continuously loop over more and more bits of n2 until we've
         * consumed the last of them.  Since after i iterations of the
         * loop b = n2 >> i, this only reaches zero once we've used up
         * all the bits of the original value of n2.
         */
        while (b != 0) {
            /* Using the bitwise AND trick, determine whether the ith
             * bit of b is a zero or one.  If it's a zero, then the
             * current term in our sum is zero and we don't do anything.
             * Otherwise, then we should add n1 * 2^i.
             */
            if ((b & 1) != 0) {
                /* Recall that a = n1 * 2^i at this point, so we're adding
                 * in the next term in the sum.
                 */
                result = result + a;
            }

            /* To maintain that a = n1 * 2^i after i iterations, scale it
             * by a factor of two by left shifting one position.
             */
            a <<= 1;

            /* To maintain that b = n2 >> i after i iterations, shift it
             * one spot over.
             */
            b >>>= 1;
        }

        System.out.println(result);
    }


}
