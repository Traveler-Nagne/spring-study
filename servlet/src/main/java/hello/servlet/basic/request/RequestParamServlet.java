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

    /*
        1. Get 방식으로 호출해도 아래의 방식을 사용해도 되고
        2. Post 방식이나 content-type 이 application/x-www-form-urlencoded 이면
           메세지 바디에 쿼리 파라미터 형식으로 데이터를 전달하기 때문에 똑같이 아래의 방식으로 사용해도 된다. (username=park&age=34)
     */

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
