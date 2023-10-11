package myDBoxClientMediator;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myDBoxClientMediator.ClientMediator;


/**
 * Servlet implementation class DBoxClientMediator
 */
@WebServlet("/DBoxClientMediator")
public class DBoxClientMediator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBoxClientMediator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		doPost(request,response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		ClientMediator mediator = new ClientMediator();

		if(request.getParameter("reqType").toString().equals("doQuery")){
			String endPoint = "";
			try {
				mediator.sendRequest(endPoint);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
/*			
//System.out.println("Try to redirect to : " + mediator.getResult());			
//			response.sendRedirect(mediator.getResult());
	
			response.setContentType("text/html");

			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", mediator.getResult());  
*/

			PrintWriter out = response.getWriter();
			out.write(mediator.getResult());  
			out.flush();
		    out.close();
			
			
			
	    }else if(request.getParameter("reqType").toString().equals("doQuery1")){
	    		    	
	    	String code = "";
			try {
				code = request.getParameter("code").toString();
				mediator.accessToken(code);
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	    	
			PrintWriter out = response.getWriter();
			out.write(mediator.getResult());  
			out.flush();
		    out.close();
			
			  
			 
	    	
	    }else if(request.getParameter("reqType").toString().equals("doQuery2")){
	    	
	    	String access_token = "";
	    	String account_id = "";
            try {
	    		access_token = request.getParameter("access_token").toString();
	    		account_id = request.getParameter("account_id").toString();
	    		mediator.getAccountInfo(access_token, account_id);
	    	} catch (URISyntaxException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    
 
	    	PrintWriter out = response.getWriter();
	    	out.write(mediator.getResult());  
	    	out.flush();
	    	out.close();


 
  
	    }else if(request.getParameter("reqType").toString().equals("doQuery3")){
	    	
	    	String access_token = "";
	    	String filePath = "";
	    	try {
	    		access_token = request.getParameter("access_token").toString();
	    		
	    		filePath = request.getParameter("filePath").toString();
System.out.println("___ Path : " + filePath);		    		
	    		ServletContext context = getServletContext();
	    		String fullPath = context.getRealPath("/"+filePath);
//	    		URL resourceUrl = context.getResource(filePath);
//	    		String fullPath = resourceUrl.toString();
System.out.println("Path : " + fullPath);	    		    		
	    		
	    		mediator.uploadFile(access_token,fullPath);
	    	} catch (URISyntaxException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
      
      
	    	PrintWriter out = response.getWriter();
	    	out.write(mediator.getResult());  
	    	out.flush();
	    	out.close();


 
  
	    }else if (request.getParameter("reqType").equals("doQuery4")) {
	        String access_token = request.getParameter("access_token");
	        try {
	            // Send the API request to list file requests
	            mediator.listFileRequests(access_token);
	        } catch (URISyntaxException e) {
	            e.printStackTrace();
	        }
	        // Get the API response from the mediator
	        String apiResponse = mediator.getResult();
	        // Send the API response to the client
	        PrintWriter out = response.getWriter();
	        out.write(apiResponse);
	        out.flush();
	        out.close();
	    }

		
	}

}
