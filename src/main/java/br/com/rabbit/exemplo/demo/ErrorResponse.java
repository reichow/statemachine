package br.com.rabbit.exemplo.demo;

import static org.apache.commons.lang3.BooleanUtils.negate;
import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@AllArgsConstructor
public final class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -8659539917093650919L;

    private final ErrorType errorType;
    private final String message;
    private final Map<String, String> details;

    public static ErrorResponse build(Exception ex) {
        if (ex instanceof AbstractErrorException) {
            return build((AbstractErrorException) ex);
        }

        return buildDefault();
    }

    public static ErrorResponse build(AbstractErrorException ex) {
        return ErrorResponse.builder()
            .errorType(ex.getErrorType())
            .message(ex.getMessage())
            .details(negate(ex.getDetails().isEmpty())
                ? ex.getDetails()
                : null)
            .build();
    }

    public static ErrorResponse buildDefault() {
        return ErrorResponse.builder()
            .errorType(ErrorType.INTERNAL_ERROR)
            .message("Falha inesperada")
            .build();
    }

}
