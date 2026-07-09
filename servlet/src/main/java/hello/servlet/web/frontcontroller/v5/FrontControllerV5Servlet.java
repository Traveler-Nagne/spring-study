package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "frontControllerV5Servlet", urlPatterns = "/front-controller/v5/*")
public class FrontControllerV5Servlet extends HttpServlet {

    Map<String, Object> handlers = new HashMap<>();
    List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();


    public FrontControllerV5Servlet() {

        this.initHandlers();
        this.initHandlerAdapters();
    }


    private void initHandlers() {

        handlers.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlers.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlers.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlers.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlers.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlers.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }


    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        Object handler = handlers.get(request.getRequestURI());
        if (Objects.isNull(handler)) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter myHandlerAdapter = handlerAdapters.stream()
                .filter(handlerAdapter -> handlerAdapter.support(handler))
                .findFirst()
                .orElseThrow();

        HandlerResult handlerResult = myHandlerAdapter.handle(request, response, handler);

        MyView myView = this.viewResolve(handlerResult.getView());
        myView.render(handlerResult.getModel(), request, response);
    }


    private MyView viewResolve(String viewName) {

        String prefix = "/WEB-INF/views/";
        String suffix = ".jsp";
        String viewPath = prefix + viewName + suffix;

        return new MyView(viewPath);
    }
}
