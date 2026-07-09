package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerV4Servlet", urlPatterns = "/front-controller/v4/*")
public class FrontControllerV4Servlet extends HttpServlet {

    private final Map<String, ControllerV4> controllers = new HashMap<>();


    public FrontControllerV4Servlet() {
        controllers.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllers.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllers.put("/front-controller/v4/members", new MemberListControllerV4());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        String uri = request.getRequestURI();
        ControllerV4 controller = controllers.get(uri);

        if (Objects.isNull(controller)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, Object> model = new HashMap<>();

        Map<String, String> params = createParams(request);

        String viewName = controller.process(params, model);

        MyView myView = viewResolve(viewName);
        myView.render(model, request, response);
    }


    private Map<String, String> createParams(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> params.put(paramName,
                        request.getParameter(paramName)));

        return params;
    }


    private MyView viewResolve(String viewName) {

        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        String viewPath = prefix + viewName + suffix;

        return new MyView(viewPath);
    }
}
