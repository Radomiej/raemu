package pl.radomiej.emu.logic;

public interface Optcode<CPU> {
    void execute(CPU cpu);
}
