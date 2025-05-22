package ch.fhnw.lsp.messages.textdocument.didchange;


import java.util.concurrent.*;


public class DebouncedAction {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static long delayMillis = 500;
    private static ScheduledFuture<?> scheduledFuture;

    public DebouncedAction(long delayMillis) {
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

