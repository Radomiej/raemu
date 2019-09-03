package pl.radomiej.emu.cpu;

import pl.radomiej.emu.logic.pure.PureByte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PureMemoryBank {
    private PureByte[] memory;
    private List<CellChangeListener>[] cellChangeListeners;

    private HashMap<String, int[]> merges = new HashMap<String, int[]>();
    private HashMap<String, Integer> names = new HashMap<String, Integer>();
    private HashMap<Integer, Integer> cpuAddresses = new HashMap<Integer, Integer>();


    public PureMemoryBank(int cells) {
        memory = new PureByte[cells];
        for (int i = 0; i < cells; i++) {
            memory[i] = new PureByte();
        }

        cellChangeListeners = new ArrayList[cells];
    }

    public void addCellChangeListener(int index, CellChangeListener cellChangeListener){
        if(cellChangeListeners[index] == null){
            cellChangeListeners[index] = new ArrayList<>();
        }

        cellChangeListeners[index].add(cellChangeListener);
    }

    public boolean removeCellChangeListener(int index, CellChangeListener cellChangeListener){
        if(cellChangeListeners[index] == null) return false;
        return cellChangeListeners[index].remove(cellChangeListener);
    }

    public void giveName(int index, String name, int cpuAddress) {
        names.put(name, index);
        cpuAddresses.put(cpuAddress, index);
    }

    public void mergeCells(int indexH, int indexL, String name) {
        int[] tmpMerged = new int[2];
        tmpMerged[0] = indexH;
        tmpMerged[1] = indexL;

        merges.put(name, tmpMerged);
    }

    public PureByte getByCpuAddress(int cpuAddress) {
        int index = cpuAddresses.get(cpuAddress);
        return memory[index];
    }

    public PureByte getByIndex(int index) {
        return memory[index];
    }

    public void setByIndex(int index, PureByte byteToSet) {
        PureByte oldValue = memory[index];
        memory[index] = byteToSet;
        notifyListeners(index, oldValue, memory[index]);
    }

    private void notifyListeners(int index, PureByte oldValue, PureByte newValue) {
        if(cellChangeListeners[index] == null) return;
        cellChangeListeners[index].forEach(e -> e.changeCellValue(oldValue, newValue));
    }
}
