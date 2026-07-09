package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.HandlerResult;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }


    @Override
    public HandlerResult handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, Object> model = new HashMap<>();
        Map<String, String> params = this.createParams(request);
        String viewName = controller.process(params, model);

        return HandlerResult.builder()
                .view(viewName)
                .model(model)
                .build();
    }


    private Map<String, String> createParams(HttpServletRequest request) {

        Map<String, String> params = new HashMap<>();

        request.getParameterNames()
                .asIterator()
                .forEachRemaining(paramName -> params.put(paramName,
                        request.getParameter(paramName)));

        return params;
    }


}
