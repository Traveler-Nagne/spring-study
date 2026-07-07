## 서블릿의 한계

해당 패키지는 서블릿과 자바 코드만으로 HTML을 만든 코드이다.  
코드에서 보면 알 수 있듯 매우 복잡하고 비효율적이다.  
자바 코드로 HTML을 만드는 것보다는 차라리 HTML 문서에 동적으로 변경해야 하는 부분만  
자바 코드를 넣을 수 있다면 더 편리할 것이다.  
이것이 바로 템플릿 엔진이 나온 이유다.  
템플릿 엔진에는 JSP, Thymeleaf 등이 있다.

## JSP의 한계

JSP의 역할이 너무많다!!  
비즈니스 로직, HTML 등의 VIEW 역할 등등 모든 역할을 맡고있다.  
이렇게 다 때려박으면 대체 유지보수는 어떻게 하라고!!!!

## MVC 패턴의 등장

### 책임 분리

view를 담당하는 JSP는 view 와 관련된 책임만 담당하고  
비즈니스 로직은 컨트롤러에서 담당한다.  
view와 비즈니스가 같이 변경되는 일은 많이 없다.  
ex) 버튼 위치 바꾸기 (view), 버튼 누르면 저장하기 (business)

### Model View Controller (MVC 패턴1)

**Cotroller** : HTTP 요청을 받아서 파라미터를 검증하고, 비즈니스 로직을 실행한다.  
**Model** : View 에서 출력할 데이터를 담아둔다. View 에서 필요한 데이터를 모두 모델에 담아서 보내주므로 View는 비즈니스 로직을 몰라도 된다.  
**View** : Model에 담겨있는 데이터를 사용하여 화면을 그리는데만 집중. HTML 생성 담당

### 참고 (MVC 패턴2)

컨트롤러에 비즈니스 로직을 둘 수 있지만 이렇게 되면 컨트롤러가 너무 많은 역할을 담당한다.  
그래서 일반적으로 비즈니스 로직은 서비스라는 계층을 만들어서 처리한다.  
그리고 컨트롤러는 비즈니스 로직이 있는 서비스를 호출하는 역할을 담당한다.

## MVC패턴 관련 용어

- dispatcher.forward() : 다른 서블릿이나 JSP로 이동할 수 있는 기능. 서버 내부에서 다시 호출.
- /WEB-INF : 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다.
    - 항상 Controller를 통해서 JSP를 호출하도록 한다.
- redirect vs forward
    - redirect는 실제 웹 브라우저로 응답이 갔다가, 웹 브라우저가 redirect 경로로 다시 요청하는 것. url 변경됨
    - forward는 서버 내부에서 일어나는 호출이기 때문에 웹 브라우저가 인식을 못함. url 변경 X

## MVC 패턴 한계

MVC 덕분에 비즈니스 로직을 처리하는 Controller 역할과 View 역할이 잘 분리되었다.  
그러나 이번에는 Controller에서 단점이 발생했다.

### 포워드 중복

`RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);`  
`dispatcher.forward(request, response);`

View로 이동하는 코드가 항상 중복되어서 호출된다.

### ViewPath 중복

`String viewPath = "/WEB-INF/views/new-form.jsp";`

- prefix : `/WEB-INF/views/`
- suffix : `.jsp`

만약 jsp 가 아닌 thymeleaf 같은 다른 View 템플릿으로 변경한다면 모든 코드를 다 변경해야한다.

### Request, Response 객체

이 객체들을 사용하지 않을 때도 있고 테스트 코드 작성하기도 어렵다.

### 공통로직이 많은 경우 처리 어려움

메서드로 분리해내는 것이 한계가 있을 것이다.

### 정리하면

Controller 에서 비즈니스 로직외에 HTTP 관련 코드들이 중복되어있고 너무 많이 들어있다는 것이다.  
그래서 분리할 필요가 있다!
