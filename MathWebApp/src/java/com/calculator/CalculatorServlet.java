/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.calculator;

/**
 *
 * @author gunti
 */



import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CalculatorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("display");
        double result = 0;

        try {
            if (input.contains("+")) {
                String[] parts = input.split("\\+");
                result = Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
            } else if (input.contains("-")) {
                String[] parts = input.split("-");
                result = Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
            } else if (input.contains("*")) {
                String[] parts = input.split("\\*");
                result = Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
            } else if (input.contains("/")) {
                String[] parts = input.split("/");
                result = Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            } else if (input.contains("%")) {
                String[] parts = input.split("%");
                result = Double.parseDouble(parts[0]) % Double.parseDouble(parts[1]);
            } else if (input.contains("^2")) {
                String num = input.replace("^2", "");
                result = Math.pow(Double.parseDouble(num), 2);
            } else if (input.contains("√")) {
                String num = input.replace("√", "");
                result = Math.sqrt(Double.parseDouble(num));
            } else {
                result = Double.parseDouble(input);
            }
        } catch (Exception e) {
            result = 0;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Same output rendering code as before...
        out.println("<html><head><title>Result</title><link rel='stylesheet' href='style.css'></head><body>");
        out.println("<div class='calculator'><form action='CalculatorServlet' method='post'>");
        out.println("<input type='text' id='display' name='display' value='" + result + "' readonly>");
        out.println("<div class='buttons'>");

        // buttons here...
        String[] btns = {"7","8","9","4","5","6","1","2","3","0",".","C","+","-","*","/","%","^2","√"};
        for(String b : btns){
            if(b.equals("C"))
                out.println("<button type='button' onclick=\"clearDisplay()\">" + b + "</button>");
            else
                out.println("<button type='button' onclick=\"append('" + b + "')\">" + b + "</button>");
        }

        out.println("<button type='submit'>=</button>");
        out.println("</div></form></div>");
        out.println("<script>function append(v){document.getElementById('display').value+=v;} function clearDisplay(){document.getElementById('display').value='';}</script>");
        out.println("</body></html>");
    }
}
