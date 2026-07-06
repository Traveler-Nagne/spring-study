package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/*
        HTTP 응답 데이터
        1. 단순 텍스트 응답
        2. HTML 응답
        3. HTTP API Message Body 응답 - JSON
 */

@WebServlet(name = "responseBodyServlet", urlPatterns = "/response-body")
public class ResponseBodyServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

        // responseBodyHtml(response);
        responseBodyJson(response);

    }


    private void responseBodyHtml(HttpServletResponse response) throws IOException {

        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.println(" <div>안녕?</div>");
        writer.println("</body>");
        writer.println("</html>");

    }


    private void responseBodyJson(HttpServletResponse response) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        HelloData helloData = new HelloData();
        helloData.setUsername("parkJongHyuk");
        helloData.setAge(34);

        String json = objectMapper.writeValueAsString(helloData);
        response.getWriter()
                .write(json);

    }


}
