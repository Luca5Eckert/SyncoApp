package com.api.blog.infrastructure.api;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

public record ApiResponse<T>(Instant timestamp, int status, String error, String message, String path, T data) {

    public static ApiResponse<Void> error(int status, String error, String message, String path){
        return new ApiResponse<Void>(Instant.now(), status, error, message, path, null);
    }

    public static <T> ApiResponse<T> success(int status, String message, String path, T data){
        return new ApiResponse<>(Instant.now(), status, null, message, path, data);
    }

    public static ApiResponse<Void> success(int status, String message, String path){
        return new ApiResponse<Void>(Instant.now(), status, null, message, path, null);
    }

}
