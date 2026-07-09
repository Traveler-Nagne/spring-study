package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.annotation.Nonnull;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebServlet(name = "frontControllerV3Servlet", urlPatterns = "/front-controller/v3/*")
public class FrontControllerV3Servlet extends HttpServlet {

    private final Map<String, ControllerV3> controllers = new HashMap<>();


    public FrontControllerV3Servlet() {
        controllers.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllers.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllers.put("/front-controller/v3/members", new MemberListControllerV3());
    }


    @Nonnull
    private static Map<String, String> createParams(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> params.put(paramName,
                        request.getParameter(paramName)));

        return params;
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ControllerV3 controller = controllers.get(request.getRequestURI());

        if (Objects.isNull(controller)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> params = createParams(request);
        ModelView modelView = controller.process(params);

        MyView myView = viewResolve(modelView);
        myView.render(modelView.getModel(), request, response);
    }


    private MyView viewResolve(ModelView modelView) {

        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        String viewPath = prefix + modelView.getViewName() + suffix;

        return new MyView(viewPath);
    }


}
