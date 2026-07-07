package hello.servlet.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

// 딱봐도 html를 생성하는 부분이 정말 복잡하고 힘들다
@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class MemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter w = response.getWriter();
        w.write("""
                <!DOCTYPE html>
                <html>
                <head>
                 <meta charset="UTF-8">
                 <title>Title</title>
                </head>
                <body>
                <form action="/servlet/members/save" method="post">
                 username: <input type="text" name="username" />
                 age: <input type="text" name="age" />
                 <button type="submit">전송</button>
                </form>
                </body>
                </html>
                """);
    }

}
