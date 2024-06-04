package dev.camilo.general.utils;

public class Result<T>{
    private final boolean isSuccess;
    private final T value;
    private final String errorMessage;

    private Result(boolean isSuccess, T value, String errorMessage) {
        this.isSuccess = isSuccess;
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> failure(String errorMessage) {
        return new Result<>(false, null, errorMessage);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public T getValue() {
        return value;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
