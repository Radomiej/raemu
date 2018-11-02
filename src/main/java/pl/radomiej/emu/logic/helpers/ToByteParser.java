package pl.radomiej.emu.logic.helpers;

import pl.radomiej.emu.logic.PureByte;

public class ToByteParser {
    public static PureByte parse(String textByte){
        textByte = textByte.trim();
        int lenght = textByte.length();
        PureByte result = new PureByte(lenght);
        for(int i = 0; i < lenght; i++){
            if(textByte.charAt(lenght - 1 - i) == '1') result.setBit(lenght - 1 - i, true);
        }
        return result;
    }

    public static PureByte parse(int intByte){
        return parse(Integer.toBinaryString(intByte));
    }
}
