package br.com.statemachine.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import br.com.statemachine.domain.ErrorType;
import lombok.Getter;

@Getter
public abstract class AbstractErrorException extends RuntimeException {

    private static final long serialVersionUID = -2660360885131681620L;

    private final Map<String, String> details;

    public AbstractErrorException(String msg) {
        super(msg);
        details = new ConcurrentHashMap<>();
    }

    public AbstractErrorException(String msg, Throwable th) {
        super(msg, th);
        details = new ConcurrentHashMap<>();
    }

    public abstract ErrorType getErrorType();

}
