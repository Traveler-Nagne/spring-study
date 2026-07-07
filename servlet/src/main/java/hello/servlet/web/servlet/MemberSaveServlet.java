package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = Member.create(username, age);

        Member savedMember = memberRepository.save(member);

        response.setContentType(MediaType.TEXT_HTML_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter w = response.getWriter();
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id=" + savedMember.getId() + "</li>\n" +
                " <li>username=" + savedMember.getUsername() + "</li>\n" +
                " <li>age=" + savedMember.getAge() + "</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");
    }
}
