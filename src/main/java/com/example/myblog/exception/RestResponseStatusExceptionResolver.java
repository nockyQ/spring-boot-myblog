package com.example.myblog.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class RestResponseStatusExceptionResolver extends AbstractHandlerExceptionResolver {

    @Override
    protected ModelAndView doResolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        try {
            if (ex instanceof IllegalArgumentException) {
                return handleIllegalArgument((IllegalArgumentException) ex, response, request);
            }
            //异常处理逻辑
        } catch (Exception handlerException) {
            log.warn("Handling of [" + ex.getClass().getName() + "]resulted in Exception", handlerException);
        }
        return null;
    }

    private ModelAndView
    handleIllegalArgument(IllegalArgumentException ex, HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT);
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        //处理响应内容
        return new ModelAndView();
    }
}

