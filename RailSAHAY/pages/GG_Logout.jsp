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

<!DOCTYPE html>
<html>
    <head>
        <title>IR.BDU Dashboard..Freight Business Development Portal</title>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <link href="/RailSAHAY/resources/css/bootstrap.min.css" rel="stylesheet" />        
	<script>
	function initPage()
	{
		alert("Successfully Logged Out !");
		window.close();
		
	}
	function login()
	{
		location.href="../IRLogin.jsp";
	}
	function closeWindow()
	{
		window.close();
	}
	</script>
        <style>
            p.title {
                font-size:26px;
                color:#fff;
                font-weight:bold;
                margin-block-end:5px;
            }
            p.title span {
                color:#ffff33;
            }
            .table-signout td {
                text-align:center;
                padding:1.8rem;
                border:0px;
            }
            .table-signout tr td:first-child
            {
                background: #0059b3;
            }
            p.desc
            {
                font-size:15px;
                color:#fff;
                margin-block-end:0px;
            }
            p.mesg
            {
                color:#4d4d4d;
                font-weight:600;
                font-size:22px;
            }
            p.mesg-desc
            {
                color:#4d4d4d;
                font-weight:600;
                font-size:13px;
            }
            .card 
            {
              /* Add shadows to create the "card" effect */
              box-shadow: 0 1px 2px 0 rgba(0,0,0,0.2);
              transition: 0.3s;
              width:90vw;
              padding:1rem;
              padding-bottom:0px;
              margin:5vw;
            }
        </style>
    </head>
    <body onload="initPage()" id="document">
        <div class="card">
        <table class="table table-signout table-striped" style="width:100%;">
            <tr>
                <td>
                    <p class="title">Freight Business Development Portal - <span>IR.BDU Dashboard</span></p>
                    <p class="desc">Faster and convenient way to present the IR Growth Prospects</p>
                </td>
                <td>
                    <p class="mesg">You've signed out of your account!</p>
                    <p class="mesg-desc">Thank you for using IR.BDU Dashboard. It's a good idea to close all your browser windows.</p>
                    <button class="btn btn-sm btn-info" onclick="login();">Sign-In Again</button>
                </td>
            </tr>
	    <tr>
		<td colspan="2" style="background:#fff;padding:0.5rem;padding-top:1rem;">
			<button class="btn btn-sm btn-danger" onclick="closeWindow();">Close this window</button>
		</td>
	    </tr>
        </table>
	
        </div>
    </body>
</html>