package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.PureByte;

public class ToByteParser {
    public static PureByte parse(String textByte){
        textByte = textByte.trim();
        int length = textByte.length();
        PureByte result = new PureByte(length);
        for(int i = 0; i < length; i++){
            if(textByte.charAt(length - 1 - i) == '1') result.setBit(length - 1 - i, true);
        }
        return result;
    }

    public static PureByte parse32(int bit32){
        return parse(Integer.toBinaryString(bit32));
    }
    public static PureByte parse(int bit8){
        String s2 = String.format("%8s", Integer.toBinaryString(bit8 & 0xFF)).replace(' ', '0');
        return parse(s2);
    }
}
