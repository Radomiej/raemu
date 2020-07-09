package pl.radomiej.imp.i8080.cpu;

import pl.radomiej.emu.cpu.PureRegistryBank;
import pl.radomiej.emu.logic.pure.PureByte;

import java.util.Arrays;

public class I8080DoubleRegistryBank implements PureRegistryBank {
    private I8080RegistryBank left;
    private I8080RegistryBank right;

    public I8080DoubleRegistryBank(I8080RegistryBank left, I8080RegistryBank right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public PureByte getValue() {
        PureByte pureByte = new PureByte(16);
        pureByte = pureByte.or(left.getValue());
        pureByte.shiftLeftInternal(8);
        pureByte = pureByte.or(right.getValue());
        return pureByte;
    }

    @Override
    public void setValue(PureByte pureByte) {
        PureByte temp = new PureByte(pureByte.getBites());
        PureByte rightValue = new PureByte(Arrays.copyOfRange(temp.getBites(), 8, 16));
        PureByte leftValue = new PureByte(Arrays.copyOfRange(temp.getBites(), 0, 8));

        left.setValue(leftValue);
        right.setValue(rightValue);
    }

    @Override
    public int getArchitecture() {
        return 16;
    }
}
