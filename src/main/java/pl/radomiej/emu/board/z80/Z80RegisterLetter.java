package pl.radomiej.emu.board.z80;

public enum Z80RegisterLetter {
    A(0b111),
    B(0b000),
    C(0b001),
    D(0b010),
    E(0b011),
    H(0b100),
    L(0b101);

    private int myAddres;
    Z80RegisterLetter(int addressValue){
        myAddres = addressValue;
    }

    public int getMyAddres(){
        return myAddres;
    }
}
