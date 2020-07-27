package pl.radomiej.emu.cpu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.radomiej.emu.logic.Optcode;

import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramData<T extends CPU> {
    private List<Optcode<T>> optcodes = new ArrayList<Optcode<T>>(1000);
    @Getter
    private int current = 0;
    private Map<String, Integer> codeSection = new HashMap<>(1000);
    private LinkedList<Integer> programJumpStack = new LinkedList<>();

    public Optcode<T> getNext() {
        if(optcodes.size() == 0) {
            System.err.print("ERR: No CPU code");
            return null;
        }
        if(current >= optcodes.size()){
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

    public int goTo(String sectionName){
        if(!codeSection.containsKey(sectionName)){
            System.err.println("No section: " + sectionName + " in program data");
        }
        int oldIndex = current;
        programJumpStack.add(oldIndex);
        current = codeSection.get(sectionName);
        return oldIndex;
    }

    /**
     * @return Index of renewed instruction occur.
     */
    public int returnToUpIndex(){
        int oldIndex = current;
        current = programJumpStack.removeLast();
        return oldIndex;
    }

    public void jump(int jumpIndex) {
        current = jumpIndex;
    }

    @Override
    public String toString() {
        return "ProgramData{" +
                "current=" + current +
                '}';
    }

    public boolean isTagExist(String jumpInstructionTag) {
        return codeSection.containsKey(jumpInstructionTag);
    }
}
