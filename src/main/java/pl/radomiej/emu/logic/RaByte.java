package pl.radomiej.emu.logic;

//TODO Prepare clean interface for Byte object representation
public interface RaByte {
    RaByte shiftRight();

    RaByte shiftLeft();

//    void setBites(RaByte fromBytes);

    String toBinaryString();

    int getLength();

    RaByte copy();
}