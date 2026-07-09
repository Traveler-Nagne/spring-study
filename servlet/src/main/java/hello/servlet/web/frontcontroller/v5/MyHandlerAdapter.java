package hello.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface MyHandlerAdapter {

    boolean support(Object handler);

    HandlerResult handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException,
            IOException;
}
