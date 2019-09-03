package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.Optcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgramData<T> {
    private List<Optcode<T>> optcodes = new ArrayList<Optcode<T>>(1000);
    private int current = 0;
    private Map<String, Integer> codeSection = new HashMap<>(1000);

    public Optcode<T> getNext() {
        if(optcodes.size() == 0) {
            System.err.print("ERR: No CPU code");
            return null;
        }
        if(optcodes.size() >= current){
            System.err.print("ERR: Program end overflow");
            return null;
        }

        return optcodes.get(current++);
    }

    public void add(Optcode<T> optcode){
        add(optcode, null);
    }

    public void add(Optcode<T> optcode, String sectionName) {
        if(sectionName != null && !sectionName.isEmpty()){
            codeSection.put(sectionName, optcodes.size());
        }
        optcodes.add(optcode);
    }

    public void goTo(String sectionName){
        if(!codeSection.containsKey(sectionName)){
            System.err.println("No section: " + sectionName + " in program data");
        }
        current = codeSection.get(sectionName);
    }

    public void jump(int jumpIndex) {
        current = jumpIndex;
    }
}
