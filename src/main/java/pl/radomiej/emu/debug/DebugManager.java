package pl.radomiej.emu.debug;

import lombok.Getter;
import lombok.Setter;

public enum DebugManager {
    Instance;

    @Getter
    @Setter
    private DebugEventListener debugEventListener;

    public void sendDebugEvent(DebugContext debugContext) {
        if (debugEventListener == null) { //Auto resume
            debugContext.getCountDownLatch().countDown();
            return;
        }

        debugEventListener.debugEvent(debugContext);
    }
}
