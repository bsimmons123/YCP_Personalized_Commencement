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

@WebServlet("/ajax")
public class AJAXServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AJAXServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         System.out.println("AJAXServlet: Do Get");


         req.getRequestDispatcher("/_view/ajaxJquery.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
