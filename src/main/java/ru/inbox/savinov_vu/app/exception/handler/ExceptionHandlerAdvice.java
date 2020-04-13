package ru.inbox.savinov_vu.app.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.inbox.savinov_vu.app.dto.StatusResponse;
import ru.inbox.savinov_vu.app.exception.hierarchy.FlowException;

import java.io.PrintWriter;
import java.io.StringWriter;

import static ru.inbox.savinov_vu.app.constants.StringConstant.FAILURE;



@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity handleDefault(Exception e) {
        LOG.warn(getStackTrace(e));
        return new ResponseEntity(StatusResponse.of(FAILURE), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(FlowException.class)
    public ResponseEntity handleDefault(FlowException e) {
        LOG.warn(getStackTrace(e));
        return new ResponseEntity(StatusResponse.of(FAILURE), HttpStatus.BAD_REQUEST);
    }

    private static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
