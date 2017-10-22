package io.yuri.yuriserver.server;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class YuriServerListener implements Future {
    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public Future sync() throws InterruptedException {
        return null;
    }

    @Override
    public Future syncUninterruptibly() {
        return null;
    }

    @Override
    public Future await() throws InterruptedException {
        return null;
    }

    @Override
    public Future awaitUninterruptibly() {
        return null;
    }

    @Override
    public boolean await(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean await(long l) throws InterruptedException {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l) {
        return false;
    }

    @Override
    public Object getNow() {
        return null;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public Future removeListeners(GenericFutureListener[] genericFutureListeners) {
        return null;
    }

    @Override
    public Future removeListener(GenericFutureListener genericFutureListener) {
        return null;
    }

    @Override
    public Future addListeners(GenericFutureListener[] genericFutureListeners) {
        return null;
    }

    @Override
    public Future addListener(GenericFutureListener genericFutureListener) {
        return null;
    }
}
