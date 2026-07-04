package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();


    /**
     * http://localhost:8080/request-body-json
     * <p>
     * JSON 형식 전송
     * content-type: application/json
     * message body: {"username": "hello", "age": 20}
     *
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        String responseBody = "messageBody = " + messageBody + "\n" +
                "helloData.name = " + helloData.getUsername() + "\n" +
                "helloData.age = " + helloData.getAge();

        response.getWriter()
                .write(responseBody);
    }
}
