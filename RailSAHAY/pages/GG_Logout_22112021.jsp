<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page isThreadSafe="true" session="true"%>
<%@ page import="logn.GG_LgotActn"%>
<%
	String strUserId		= (String)session.getAttribute("UserID");
	String strClntId		= (String)session.getAttribute("ClntID");
	String strProj			= (String)session.getAttribute("ProjName");
	String strLang			= ((String) session.getAttribute("LangFlag")).trim();
	
	GG_LgotActn lgotAction	= new GG_LgotActn();
	lgotAction.doPost(request, response);
	System.out.println("Logout Success");
	try
	{
		response.setContentType("text/html");
		javax.servlet.RequestDispatcher requestdispatcher= null;
		
		 requestdispatcher = request.getRequestDispatcher("Logout.jsp");
		
		requestdispatcher.forward(request, response);
	}
	catch(Exception e)
	{
		//System.out.println("Error::" + e);
	}
	//System.out.println("Success: Deva");
	%>
<html>
	<head>
		<title>
			FBD Application
		</title>
		<script>
			 function initPage()
			{
				window.close();
			} 
		</script>
	</head>
	<body  style="overflow: auto" onload="initPage()" id="document">
		
		<p align="center"><b>You Have Been Logout Successfully.</b></p>
	</body>
</html>