package com.debajyoti.spacexinfo.utils;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

@Singleton
public class AppExecutor {
    private final Executor diskIO;
    private final Executor mainThread;

    public AppExecutor(Executor diskIO, Executor mainThread) {
        this.diskIO = diskIO;
        this.mainThread = mainThread;
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor mainThread() {
        return mainThread;
    }
}
