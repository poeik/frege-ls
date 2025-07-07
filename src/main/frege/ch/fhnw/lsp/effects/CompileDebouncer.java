package ch.fhnw.lsp.effects;

import java.util.concurrent.*;

public class CompileDebouncer {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static long delayMillis = 500;
    private static ScheduledFuture<?> scheduledFuture;

    public CompileDebouncer(long delayMillis) {
        this.delayMillis = delayMillis;
    }

    public synchronized void trigger(Runnable action) {
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = scheduler.schedule(action, delayMillis, TimeUnit.MILLISECONDS);
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}

