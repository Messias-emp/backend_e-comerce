package com.ecommerce.common.exception;

import com.ecommerce.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntime(RuntimeException ex) {

        ApiResponse<Void> response =
            new ApiResponse<>(false, ex.getMessage(), null);

        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(response);
    }
}
