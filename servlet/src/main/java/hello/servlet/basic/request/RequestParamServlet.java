package hello.servlet.basic.request;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[전체 파라미터 조회]\n");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName ->
                        stringBuilder.append(paramName).append(" = ").append(request.getParameter(paramName)).append("\n"));
        stringBuilder.append("------------------------------------------------ \n");

        stringBuilder.append("\n[이름이 같은 복수 파라미터 조회]\n");
        String[] usernames = request.getParameterValues("username");
        Arrays.stream(usernames)
                .forEach(name -> stringBuilder.append("username = ").append(name).append("\n"));

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(stringBuilder.toString());
    }
}
