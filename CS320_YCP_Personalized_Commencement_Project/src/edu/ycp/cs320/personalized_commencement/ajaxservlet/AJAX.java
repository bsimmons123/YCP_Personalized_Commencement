package edu.ycp.cs320.personalized_commencement.ajaxservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/AjaxHandler")
public class AJAX extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AJAX() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");

      System.out.println(name);

      response.getWriter().print("Hello "+ name + "!!");
    }
}
