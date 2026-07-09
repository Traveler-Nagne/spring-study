package hello.servlet.web.frontcontroller.v5;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HandlerResult {

    private final String view;
    private final Map<String, Object> model;
}
