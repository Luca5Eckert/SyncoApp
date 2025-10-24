package com.api.blog.infrastructure.api;

import java.time.Instant;

public record CustomApiResponse<T>(Instant timestamp, int status, String error, String message, String path, T data) {

    public static CustomApiResponse<Void> error(int status, String error, String message, String path){
        return new CustomApiResponse<Void>(Instant.now(), status, error, message, path, null);
    }

    public static <T> CustomApiResponse<T> success(int status, String message, T data){
        return new CustomApiResponse<>(Instant.now(), status, null, message, null, data);
    }

    public static CustomApiResponse<Void> success(int status, String message){
        return new CustomApiResponse<Void>(Instant.now(), status, null, message, null, null);
    }

}
