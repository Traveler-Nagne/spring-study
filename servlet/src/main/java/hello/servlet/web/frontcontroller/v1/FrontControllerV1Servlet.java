package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerV1Servlet", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1Servlet extends HttpServlet {

    private final Map<String, ControllerV1> controllers = new HashMap<>();


    public FrontControllerV1Servlet() {
        controllers.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllers.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllers.put("/front-controller/v1/members", new MemberListControllerV1());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ControllerV1 controller = controllers.get(request.getRequestURI());

        if (Objects.isNull(controller)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
