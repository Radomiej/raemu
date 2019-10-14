package pl.radomiej.emu.logic;

public interface PartialOptcode<T> {
    boolean isEndOfPartialOptcodes();
    Optcode<T> getNextOptcode();
}
