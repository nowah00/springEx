package com.ssg.springex;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/memberRegister")
public class MemberRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doHandle(req, resp);
    }

    public void doHandle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String user_id = req.getParameter("user_id");
        String user_pwd = req.getParameter("user_pwd");
        String gender = req.getParameter("gender");
        String[] hobby = req.getParameterValues("hobby");

        String data = "<html>";
        data += "<body>";
        data += "<h1> 회원정보 </h1>";
        data += "<P> 아이디: " + user_id + "</p>";
        data += "<P> 비밀번호 : " + user_pwd + "</p>";
        data += "<P> 성별 : " + gender + "</p>";
        data += "<p> 취미: ";
        for (String h : hobby) {
            data += h + " ";
        }
        data += "</p>";
        data += "</body>";
        data += "</html>";
        out.println(data);
    }
}
