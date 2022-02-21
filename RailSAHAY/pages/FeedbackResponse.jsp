<%
String strStts="";
String strRefID="";
String strEror="";
String strCT="";
try
{
	strStts=((String)request.getAttribute("TRXNSTTS")).trim();
	strRefID=((String)request.getAttribute("REF_ID")).trim();
	strEror=((String)request.getAttribute("ERROR")).trim();
	strCT=((String)request.getAttribute("CRTYPE"));
}
catch(Exception e)
{
	strStts="";
	strRefID="";
	strEror="";
        strCT="";
	
}
%>
<%@ include file="/pages/GG_Header.jspf" %>

<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FEEDBACK RESPONSE","/RailSAHAY/pages/FeedbackResponse.jsp",UserDevice,Browser);
%>

	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">

          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	 	 <fieldset>
                    <div class="form-card">
                        <h2 class="fs-title text-center"><%=E.EH("Success")%> !</h2> <br><br>
                        <div class="row justify-content-center">
                            <div class="col-3"> <img src="/RailSAHAY/resources/images/OK.png" class="fit-image"> </div>
                        </div> <br><br>
                        <div class="row justify-content-center">
                            <div class="col-7 text-center">
                            <% if(!strCT.equals("COMPLIMENT")){ %>
                                <p class="scssmesg"><%=E.EH("Thank you for contacting us. For detail, kindly check your mail. We will reach you shortly")%></p>
                                <p class="tktnumb"><%=E.EH("PLEASE SAVE YOUR REFERENCE NUMBER AS")%> : <b><%=strRefID %></b></p>
                            <%} else{ %>  
                            <p class="scssmesg"><b><%=E.EH("THANK YOU FOR YOUR VALUABLE FEEDBACK")%>!</b></p>
                            <%} %>
    
                            </div>
                        </div>
                    </div>
                </fieldset>
			  
			  	
	
      </div>      
      <br/>
      

        </form>
</div>
      </div>
</div>
 <script>
                var strsttn='';
                for (var i=0; i < aSttn.length;++i)
		{
                	strsttn += '<option value="'+aSttn[i]+'" />'; // Storing options in variable
                }
		var strdstt='';
                for (var i=0; i < aDistrict.length;++i)
		{
                	strdstt+= '<option value="'+aDistrict[i]+'" />'; // Storing options in variable
                }


                var orgnlist=document.getElementById("orgnlist");
                orgnlist.innerHTML = strdstt;
                var dstnlist=document.getElementById("dstnlist");
                dstnlist.innerHTML = strdstt;
                
                var str="";            
                
                for (var i=0; i < gCNCode.length;++i)
		{
	                str += '<option value="'+gCNCode[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("cmdtlist");
                my_list.innerHTML = str;
                str="";            
                
                for (var i=0; i < gStck.length;++i)
		{
        	        str += '<option value="'+gStck[i]+'" />'; // Storing options in variable
                }
                var my_list=document.getElementById("wgonlist");
                my_list.innerHTML = str;                
               
</script>
<script>
	$(document).ready(function(){
		$( "#selOrigClstType").change(function() {
  			var origclsttype=$( "#selOrigClstType").val();
			if(origclsttype=="S")
			{                
				var orgnlist=document.getElementById("orgnlist");
		                orgnlist.innerHTML = strsttn;
			}
			if(origclsttype=="D")
			{                
				var orgnlist=document.getElementById("orgnlist");
		                orgnlist.innerHTML = strdstt;
			}
		});
		$( "#selDstnClstType").change(function() {
  			var dstnclsttype=$( "#selDstnClstType").val();
			if(dstnclsttype=="S")
			{                
				var dstnlist=document.getElementById("dstnlist");
		                dstnlist.innerHTML = strsttn;
			}
			if(dstnclsttype=="D")
			{                
				var dstnlist=document.getElementById("dstnlist");
		                dstnlist.innerHTML = strdstt;
			}
		});
		
	});
</script>

<%@ include file="/pages/GG_Footer.jspf" %>
