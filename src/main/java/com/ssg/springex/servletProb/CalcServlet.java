package com.ssg.springex.servletProb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
