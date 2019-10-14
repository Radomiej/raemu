package pl.radomiej.emu.cpu.optcodes.pure.program;

import lombok.SneakyThrows;
import pl.radomiej.emu.cpu.PureCPU;
import pl.radomiej.emu.debug.DebugContext;
import pl.radomiej.emu.debug.DebugManager;
import pl.radomiej.emu.logic.Optcode;

import java.util.concurrent.CountDownLatch;

/**
 * This implementation of Debug send DebugEvent and block current thread until it was release from another thread.
 */
public class Debug implements Optcode<PureCPU> {
    public Debug() {

    }

    @Override
    @SneakyThrows
    public void execute(PureCPU pureCPU) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        DebugContext debugContext = DebugContext.builder().countDownLatch(countDownLatch).build();
        DebugManager.Instance.sendDebugEvent(debugContext);
        countDownLatch.await();
    }
}