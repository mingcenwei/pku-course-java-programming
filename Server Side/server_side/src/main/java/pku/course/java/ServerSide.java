package pku.course.java;

import java.io.*;
import java.util.stream.Collectors;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/query")
public class ServerSide extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // The doPOST() runs once per HTTP POST request to this servlet.
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException
    {
        request.setCharacterEncoding("UTF-8");
        String jsonString =
            request.getReader().lines().collect(Collectors.joining());
        System.out.println(jsonString);
        String result = pku.course.java.App.query(jsonString);


        // Set the response MIME type of the response message
        response.setContentType("application/json;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket
        PrintWriter out = response.getWriter();

        // Write the response message, in an HTML page
        out.print(result);
        out.flush();
        out.close();  // Always close the output writer
   }
}
