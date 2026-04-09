package com.ticket.electronico.shared.util;

import java.util.Optional;
import java.util.function.Function;

public class Result<T, E> {
    private final T value;
    private final E error;
    private final boolean isSuccess;

    private Result(T value, E error, boolean isSuccess) {
        this.value = value;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public static <T, E> Result<T, E> success(T value) {
        return new Result<>(value, null, true);
    }

    public static <T, E> Result<T, E> failure(E error) {
        return new Result<>(null, error, false);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    public Optional<T> getValue() {
        return isSuccess ? Optional.of(value) : Optional.empty();
    }

    public Optional<E> getError() {
        return isSuccess ? Optional.empty() : Optional.of(error);
    }

    public <U> Result<U, E> map(Function<T, U> mapper) {
        if (isSuccess) {
            return Result.success(mapper.apply(value));
        }
        return Result.failure(error);
    }

    public <F> Result<T, F> mapError(Function<E, F> mapper) {
        if (isFailure()) {
            return Result.failure(mapper.apply(error));
        }
        return Result.success(value);
    }
}