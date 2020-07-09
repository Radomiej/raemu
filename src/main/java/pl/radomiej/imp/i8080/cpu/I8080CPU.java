package pl.radomiej.imp.i8080.cpu;

import pl.radomiej.emu.cpu.ProgramData;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.cpu.PureRegistryBank;

import java.util.HashMap;
import java.util.Map;

public class I8080CPU extends PureCPU {
    private Map<String, PureRegistryBank> registryBankMap = new HashMap<>();

    public I8080CPU(ProgramData<PureCPU> program) {
        super(program, 8, new I8080Flags());

        I8080RegistryBank A = new I8080RegistryBank();
        I8080RegistryBank F = new I8080RegistryBank();
        I8080DoubleRegistryBank PSW = new I8080DoubleRegistryBank(A, F);

        I8080RegistryBank B = new I8080RegistryBank();
        I8080RegistryBank C = new I8080RegistryBank();
        I8080DoubleRegistryBank BC = new I8080DoubleRegistryBank(B, C);

        I8080RegistryBank D = new I8080RegistryBank();
        I8080RegistryBank E = new I8080RegistryBank();
        I8080DoubleRegistryBank DE = new I8080DoubleRegistryBank(D, E);

        I8080RegistryBank H = new I8080RegistryBank();
        I8080RegistryBank L = new I8080RegistryBank();
        I8080DoubleRegistryBank HL = new I8080DoubleRegistryBank(H, L);

        registryBankMap.put("A", A);
        registryBankMap.put("F", F);
        registryBankMap.put("PSW", PSW);

        registryBankMap.put("B", B);
        registryBankMap.put("C", C);
        registryBankMap.put("BC", BC);

        registryBankMap.put("D", D);
        registryBankMap.put("E", E);
        registryBankMap.put("DE", DE);

        registryBankMap.put("H", H);
        registryBankMap.put("L", L);
        registryBankMap.put("HL", HL);
    }

    public PureRegistryBank getRegistry(String name){
        return registryBankMap.get(name);
    }
}
