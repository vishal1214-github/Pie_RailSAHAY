<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"TRANSPORT RESPONSE","/RailSAHAY/pages/TransportResponse.jsp",UserDevice,Browser);
%>
<%
	String strMessage="";
        if((String)request.getAttribute("message")!=null)
            strMessage =(String)request.getAttribute("message");
            
       System.out.println(strMessage);     
%>


	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100">HELP US TO SERVE YOU BETTER !</h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	 	 <fieldset>
                    <div class="form-card">
                        <h2 class="fs-title text-center">Success !</h2> <br><br>
                        <div class="row justify-content-center">
                            <div class="col-3"> <img src="https://img.icons8.com/color/96/000000/ok--v2.png" class="fit-image"> </div>
                        </div> <br><br>
                        <div class="row justify-content-center">
                            <div class="col-7 text-center">
                                <p class="scssmesg">Thank you for contacting us. For detail, kindly check your mail. We will reach you shortly</p>
                                <p class="tktnumb">PLEASE SAVE YOUR REFERENCE NUMBER AS : <b><%=strMessage %></b></p>
                                <p class="bkngregprocess"><a href="/RailSAHAY/pages/BkngRegProcess.jsp" target="_self">BOOKING AND REGISTRATION PROCESS</a></p>
                                <p class="bkngregprocess"><a href="/RailSAHAY/pages/FrgtCalc.jsp" target="_self">FREIGHT CALCULATOR</a></p>
                                <p class="bkngregprocess"><a href="/RailSAHAY/pages/Policy.jsp" target="_self">SCHEMES AND DISCOUNTS</a></p>
    
    
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
