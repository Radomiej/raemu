package pl.radomiej.emu.integrations;

import pl.radomiej.emu.logic.Optcode;

import java.util.ArrayList;
import java.util.List;

public class ProgramData<T> {
    private List<Optcode<T>> optcodes = new ArrayList<Optcode<T>>(1000);
    private int current = 0;

    public Optcode<T> getNext() {
        if(optcodes.size() == 0) {
            System.err.print("ERR: No CPU code");
            return null;
        }

        return optcodes.get(current++);
    }

    public void add(Optcode<T> z80Optcode) {
        optcodes.add(z80Optcode);
    }
}
