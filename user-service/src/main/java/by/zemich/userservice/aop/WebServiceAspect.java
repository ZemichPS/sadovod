package by.zemich.userservice.aop;

import by.zemich.userservice.core.dto.ErrorResponseDto;
import by.zemich.userservice.core.dto.User;
import by.zemich.userservice.core.dto.UserCreateDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebServiceAspect {

    @Around("WebUserServicePointCuts.allMethod()")
    public Object webResponseAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        ServerRequest request = (ServerRequest) joinPoint.getArgs()[0];

        switch (methodSignature.getName()) {
            case "save" -> log.info("получен запрос на сохранение нового пользователя. Request: %s".formatted(request));
            case "update" -> log.info("получен запрос на обновление пользователя. Request: %s".formatted(request));
        }
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable exception) {
            ErrorResponseDto errorResponse = new ErrorResponseDto();
            errorResponse.setMessage(exception.getMessage());

            result = ServerResponse.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
        return result;
    }
}
