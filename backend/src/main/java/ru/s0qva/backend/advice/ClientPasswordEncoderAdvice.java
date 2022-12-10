package ru.s0qva.backend.advice;

import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;
import ru.s0qva.backend.dto.ClientDto;

import java.lang.reflect.Type;

@ControllerAdvice
@RequiredArgsConstructor
@SuppressWarnings("NullableProblems")
public class ClientPasswordEncoderAdvice extends RequestBodyAdviceAdapter {
    private final PasswordEncoder passwordEncoder;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

        var client = (ClientDto) body;

        encodePassword(client);
        return client;
    }

    private void encodePassword(ClientDto client) {
        var encodedPassword = passwordEncoder.encode(client.getPassword());

        client.setPassword(encodedPassword);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {

        return ((Type) ClientDto.class).equals(targetType);
    }
}

