package pl.radomiej.emu.pure.logic;

import java.util.HashMap;

public class PureMemoryBank {
    private PureByte[] memory;
    private HashMap<String, int[]> mergeses = new HashMap<String, int[]>();
    private HashMap<String, Integer> names = new HashMap<String, Integer>();
    private HashMap<Integer, Integer> cpuAddresses = new HashMap<Integer, Integer>();

    public PureMemoryBank(int cells) {
        memory = new PureByte[cells];
        for (int i = 0; i < cells; i++) {
            memory[i] = new PureByte();
        }
    }

    public void giveName(int index, String name, int cpuAddress) {
        names.put(name, index);
        cpuAddresses.put(cpuAddress, index);
    }

    public void mergeCells(int indexH, int indexL, String name) {
        int[] tmpMerged = new int[2];
        tmpMerged[0] = indexH;
        tmpMerged[1] = indexL;

        mergeses.put(name, tmpMerged);
    }

    public PureByte getByCpuAddress(int cpuAddress) {
        int index = cpuAddresses.get(cpuAddress);
        return memory[index];
    }

    public PureByte getByIndex(int index) {
        return memory[index];
    }
}
