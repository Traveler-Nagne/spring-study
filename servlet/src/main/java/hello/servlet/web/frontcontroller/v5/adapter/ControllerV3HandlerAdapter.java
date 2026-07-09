package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.ModelView;
import hello.servlet.web.frontcontroller.v5.HandlerResult;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {


    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV3);
    }


    @Override
    public HandlerResult handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> params = this.createParams(request);
        ModelView modelView = controller.process(params);

        return HandlerResult.builder()
                .view(modelView.getViewName())
                .model(modelView.getModel())
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
