package pl.radomiej.emu.pure.logic;

public interface Optcode<CPU> {
    void execute(CPU cpu);
}
