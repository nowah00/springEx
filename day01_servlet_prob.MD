## 1) HelloServlet & 매핑

**요구사항**

- `/hello`로 GET 요청 시 `안녕하세요, 서블릿!` 출력
- 응답 Content-Type을 `text/html; charset=UTF-8`로 설정
- 현재 시간도 함께 출력하세요

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>hello servlet</title>
</head>
<body>
<a href="/hello">hello</a>
</body>
</html>
```

```java
@WebServlet("/hello")
public class ProbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        LocalDateTime now = LocalDateTime.now();
        String formattedNow = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>안녕하세요, 서블릿!</h1>");
        out.println("<h2>" + formattedNow + "</h2>");
        out.println("</body>");
        out.println("</html>");
    }
}
```

## 2) GET 파라미터 처리 & 검증

**요구사항 (사칙연산 프로그램  입력화면,  처리 서블릿 , 결과 처리)**

- `/calc?x=10&y=3&op=add`
- 결과를 HTML로 출력

  예)  3 add 2  선택 전송 시  ⇒  출력 : 3 + 2 = 5
  3 sub  2  전송 시  ⇒  출력: 3  -  2 = 1
  3 mul  2  전송 시  ⇒  출력: 3  *  2 = 6
  3 div   2  전송 시 ⇒   출력:  3 / 2  = 1

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>사칙연산 프로그램</title>
    <script>
        function send(op) {
            const f = document.frmCalculate;
            if (!f.x.value || !f.y.value) {
                alert('숫자를 입력하세요.');
                return;
            }
            document.getElementById('op').value = op;
            f.submit();
        }
    </script>
</head>
<body>
<form name="frmCalculate" method="get" action="/calc">
    첫번째 숫자: <input type="number" name="x"><br>
    두번째 숫자: <input type="number" name="y"><br>
    <input type="hidden" name="op" id="op">
    <button type="button" onclick="send('add')">+</button>
    <button type="button" onclick="send('sub')">-</button>
    <button type="button" onclick="send('mul')">×</button>
    <button type="button" onclick="send('div')">÷</button>
</form>
</body>
</html>
```

```java
@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String sx = req.getParameter("x");
        String sy = req.getParameter("y");
        String op = req.getParameter("op");

        Integer x, y;
        x = Integer.parseInt(sx);
        y = Integer.parseInt(sy);

        String result;
        String c;

        switch (op) {
            case "add":
                c = "+";
                result = String.valueOf(x + y);
                break;
            case "sub":
                c = "-";
                result = String.valueOf(x - y);
                break;
            case "mul":
                c = "*";
                result = String.valueOf(x * y);
                break;
            case "div":
                c = "/";
                result = String.valueOf(x / y);
                break;
            default:
                c = "null";
                result = "지원하지 않는 op";
        }

        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body><h2>" + x + c + y + " = " + result + "</h2></body></html>");
        }
    }
}
```

1. (OX) 서블릿 수명주기 기본 순서는 `init()` → `service()` → `destroy()` 이다.
- O
2. `doGet`과 `doPost`의 올바른 설명은?

   A) `doGet`은 보안에 강하므로 비밀번호 전송에 권장된다

   **B) `doPost`는 본문(body)에 데이터를 담아 보낼 수 있다**

   C) `doGet`은 본문을 반드시 사용해야 한다

   D) 둘 다 브라우저가 자동 캐시를 막는다


3. 한글 응답 본문이 깨질 때 **반드시** 설정해야 하는 응답 헤더(또는 API)는?
- **response.setContentType(“text/html; charset=UTF-8”);**
4.  다음 중 **상태 코드**와 의미가 맞는 것은?

    A) 200 - Redirect 완료

    **B) 302 - 임시 리다이렉트**

    C) 404 - 서버 내부 오류

    D) 500 - 리소스를 찾을 수 없음

5.  쿼리 파라미터를 읽는 메서드는?

    A) `getAttribute`  **B) `getParameter`**  C) `getHeader`  D) `getCookie`
    
6. WAS 란 무엇이고 상용화 프로그램 종류2가지
- 웹서버 + 웹컨테이너 기능을 제공, JSP·Servlet 실행 담당 예: **Tomcat**, **Jeus**

7. 응답객체에 클라이언트에 전송할 데이터를 쓸 때 사용되는 클래스와 메서드는 무엇인가?
- **PrintWriter out = response.getWriter();** → out.println("출력내용");

8. 클라이언트가 서버에 접속했을때 WAS 부여해 주는 2가지 객체는 ?
- **HttpServletRequest**, **HttpServletResponse**
9. 서블릿을 WAS 등록할때 사용되는 어노테이션은 무엇이고,  해당 서블릿의 이름이  myServlet 이고  my란 요청명으로  등록 방법은?
- @WebServlet("/my")

10. 클라이언트가 서버에 요청시 해당 요청을 처리하는 서블릿을 연결해주는 배치관리자 역할의 파일명은 무엇이고  프로젝트에서의 위치(디렉토리명)는 어디인가?
- **web.xml**, 위치는 **WEB-INF** 