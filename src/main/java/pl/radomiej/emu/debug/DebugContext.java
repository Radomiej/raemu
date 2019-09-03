package pl.radomiej.emu.debug;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.util.concurrent.CountDownLatch;

@Builder
@Value
public class DebugContext {
    private CountDownLatch countDownLatch;
}
