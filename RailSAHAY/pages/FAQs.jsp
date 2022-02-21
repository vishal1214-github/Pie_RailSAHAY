<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"FAQ","/RailSAHAY/pages/FAQs.jsp",UserDevice,Browser);
%>
  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("FREQUENTLY ASKED QUESTIONS")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
	<div class="col-lg-10 offset-lg-1 col-sm-12">	
	<div class="card">
	  <div class="card-header head-faq">
	    <%=E.EH("Booking")%>
	  </div>
	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#BkngOne">
			   <%=E.EH("What is the procedure of booking Freight Traffic")%>?
			 </a>
		       </div>
		       <div id="BkngOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Customer is required to furnish a filled forwarding note containing details of commodity, Originating and Destination points(Railway Locations) etc. to the goods clerk/ chief goods supervisor at the Loading Location over Indian Railways. Now customer can register their demand electronically from the place of their convenience through Indian Railway's Freight Operations Information System (FOIS) e-Demand Service")%>. <a href="https://www.fois.indianrail.gov.in/foisEfnote/OpenPages/Login_new.jsp"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngTwo">
			   <%=E.EH("How can we place indent for wagons")%>?
			 </a>
		       </div>
		       <div id="BkngTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Customers can place request for wagons by making application and payment of Wagon Registration Fee (WRF) at goods booking office.  Now customer can register their demand electronically from the place of their convenience through Indian Railway's Freight Operations Information System (FOIS) e-Demand Service")%>. <a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngThree">
			   <%=E.EH("How can I know the distance between source/booking location and destination location for my booking")%>?
			 </a>
		       </div>
		       <div id="BkngThree" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("The distance / routes can be viewed through Indian Railway's Rates Branch System or Indian Railway's Freight Operations Information System (FOIS) website")%>.<a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngFour">
			   <%=E.EH("What is Wagon Registration Fee or WRF")%>?
			 </a>
		       </div>
		       <div id="BkngFour" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Wagon Registration Fee (WRF) is a booking amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake (full train). This amount may be adjusted in Freight")%>. </p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#BkngFive">
			   <%=E.EH("How can a customer book their Rakes/Wagons with Indian Railways")%>?
			 </a>
		       </div>
		       <div id="BkngFive" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Customer may visit the Railway Goods Sheds with the request for booking or can book their consignemnt through Indian Railway's Freight Operations Information System (FOIS) electronically through e-Demand Service, which is simpler, convenient and transparent mode")%>. <a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
			 </div>
		       </div>
		     </div>
		</div>
	  </div>	  
	  	<div class="card">
	  	  <div class="card-header head-faq">
	  	    <%=E.EH("Payments")%>
	  	  </div>
	  	  <div class="card-body">
		   <div id="accordion">	   
		     <div class="card">
		       <div class="card-header">
			 <a class="card-link" data-toggle="collapse" href="#PymtOne">
			   <%=E.EH("What is Indian Railways e-payment system for Payment of Freight")%>? 
			 </a>
		       </div>
		       <div id="PymtOne" class="collapse show" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Railway has introduced Electronic payment system. It enables collection of freight charges directly from customer's bank account. Customers can avail this facility by executing agreement with railway administration and bank. Handling Agents of customers are also permitted for making e-payment on behalf of their customers")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtTwo">
			   <%=E.EH("What is Wagon Registration Fee")%>?
			 </a>
		       </div>
		       <div id="PymtTwo" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Wagon Registration Fee (WRF) is a token amount collected at the time of placing indent. At present, the WRF is Rs.50000/- per rake. This amount may be adjusted in Freight")%>.</p>
			 </div>
		       </div>
		     </div>

		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtThree">
			   <%=E.EH("Which type of charges, can I pay through FBD Portal")%>?
			 </a>
		       </div>
		       <div id="PymtThree" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("As of now, Freight Charges can be paid through FBD Portal. Charges related to Demurrage, Wharfage, Other Charges etc. shall soon form the part of this Online Payment utility")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtFour">
			   <%=E.EH("When can I pay the freight charges through FBD Portal")%>?
			 </a>
		       </div>
		       <div id="PymtFour" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("After the physical loading of the rake, Railway Goods Clerk (GC) will verifies all the charges which have been accrued. After considering various adjustments, GC will confirm the final amount for collection and will become available to customer for payment through FBD Portal. The customer will also receive notification for making payment through SMS/email")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtFive">
			   <%=E.EH("How could I make the payment of Freight Charges through FBD Portal")%>?
			 </a>
		       </div>
		       <div id="PymtFive" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("A customer may login (with e-Demand Login Credentials) to his personalized dashboard through Existing Customers->Individual Login menu. The fright charges against the logged in customer will be listed through Pending Payments option in that Dashboard. Payment against each outstanding charge can be conveniently made through the Pay Now option which directs the customer to the  SBI Payment Gateway. The customer can make online payment using one of the available options on SBI payment Gateway")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtSix">
			   <%=E.EH("How would I know that I have successfully paid the Freight Charges")%>?
			 </a>
		       </div>
		       <div id="PymtSix" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("A customer shall receive a communication on his registered Email Id and Mobile Number with Bank Transaction Id and FOIS Transaction Id for the successful payments. The payment details will be available in the ‘Payment History’ on the Customer’s dashboard on FBD portal")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtSeven">
			   <%=E.EH("Which are the online transaction modes that I can use to make payments")%>?
			 </a>
		       </div>
		       <div id="PymtSeven" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Online payment can be made through Net Banking/RTGS/NEFT, Credit card/Debit Card/UPI")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtEight">
			   <%=E.EH("What if payment has been made but confirmation has not been received from Railways")%>?
			 </a>
		       </div>
		       <div id="PymtEight" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Please check the status of the payment. If it is showing ‘pending’, please wait for 10 minutes for confirmation")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtNine">
			   <%=E.EH("What if payment has been deducted from my bank but it is shown failed on my dashboard")%>?
			 </a>
		       </div>
		       <div id="PymtNine" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("There are chances that the failed payment will get Successful in 10 minutes. Otherwise, the payment received by Bank will be refunded within 2-3 working days")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtTen">
			   <%=E.EH("What if I have made payment twice")%>?
			 </a>
		       </div>
		       <div id="PymtTen" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("The excess payment will be automatically be refunded to your account within 2-3 working days")%>.</p>
			 </div>
		       </div>
		     </div>
		     <div class="card">
		       <div class="card-header">
			 <a class="collapsed card-link" data-toggle="collapse" href="#PymtEleven">
			   <%=E.EH("Whom shall I contact in case I am facing any problem in making online payment")%>?
			 </a>
		       </div>
		       <div id="PymtEleven" class="collapse" data-parent="#accordion">
			 <div class="card-body">
			   <p class="justify"><%=E.EH("Please use Contact Us option available on home page of FBD portal")%>.</p>
			 </div>
		       </div>
		     </div>

		</div>
		</div>
			  
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    <%=E.EH("Freight and Other Charges")%>
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 		<a class="card-link" data-toggle="collapse" href="#FctChrg8">
					 			<%=E.EH("Why Railway levies Demurrage Charge")%>?
					 		</a>
				       	</div>
				       	<div id="FctChrg8" class="collapse show" data-parent="#accordion">
					 		<div class="card-body">
					   			<p class="justify"><%=E.EH("Demurrage Charge is levied for the detention of railway’s rolling stock beyond prescribed free time")%>. </p>
					 		</div>
				       	</div>
				     </div>
				     
				     <div class="card">
				       <div class="card-header">
					 		<a class="collapsed card-link" data-toggle="collapse" href="#FctChrg9">
					 			<%=E.EH("What does free time mean")%>?
					 		</a>
				       	</div>
				        <div id="FctChrg9" class="collapse" data-parent="#accordion">
					 		<div class="card-body">
					 			 <p class="justify"><%=E.EH("Railway has permitted certain time as free time for loading and unloading of wagons. Free time are prescribed for different types of wagons at goods sheds, sidings, old steel plants, other steel plants and freight terminals/sidings/steel plants/goods sheds which work on Engine-on-Load basis")%>.</p>
					 		</div>
				       	</div>
				     </div>
				     
				     <div class="card">
				       <div class="card-header">
							 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg10">
					 			 <%=E.EH("What is the rate of Demurrage Charge")%>?
					 		</a>
				      	 </div>
				      	 <div id="FctChrg10" class="collapse" data-parent="#accordion">
					 		<div class="card-body">
					 			  <p class="justify"><%=E.EH("At present, rate of Demurrage Charge is Rs 150/- per wagon per hour or part of an hour")%>.</p>
					 		</div>
				     	  </div>
				     </div>
				   
				   	<div class="card">
				    	 <div class="card-header">
					 		<a class="collapsed card-link" data-toggle="collapse" href="#FctChrg1">
					   			<%=E.EH("What are the guidelines for waiver of Demurrage/Wharfage")%>?
					 		</a>
				       	</div>
				       	<div id="FctChrg1" class="collapse" data-parent="#accordion">
					 		<div class="card-body">
					 			<p class="justify"><%=E.EH("In case the consignor/consignee feels that Demurrage or Wharfage was imposed due to reasons beyond their control, he can prefer application for waiver to the station master/goods supervisor at the Loading or Delivery location as the case be")%>.</p>
					 		</div>
				       </div>
				     </div>
				     
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg2">
					   <%=E.EH("What is Siding Charge")%>?
					 </a>
				       </div>
				       <div id="FctChrg2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Siding Charges are charges levied for hauling a rake (Freight Train) between Railway serving station and its siding for loading / unloading of Goods")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg3">
					   <%=E.EH("How is the Siding Charge calculated")%>?
					 </a>
				       </div>
				       <div id="FctChrg3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Siding Charge is worked out on the basis of Engine Hour Cost and average trip time for movement of rake between serving station and siding")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg4">
					  <%=E.EH("What is Engine Hour Cost")%>?
					 </a>
				       </div>
				       <div id="FctChrg4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("All India Engine Hour cost is notified by Railway Board for Diesel and Electric Locomotive")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg5">
					  <%=E.EH("What is Shunting Charge")%>?
					 </a>
				       </div>
				       <div id="FctChrg5" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Shunting Charge is levied for the utilization of Railway Loco to perform shunting operation on the Freight Train")%>. </p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FctChrg6">
					  <%=E.EH("What is through distance based charging")%>?
					 </a>
				       </div>
				       <div id="FctChrg6" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Through distance based charging is the system of charging freight upto the buffer end of the siding, in place of levying Siding Charge")%>. </p>
					 </div>
				       </div>
				     </div>
				     
				     <div class="card">
				       <div class="card-header">
					 		<a class="collapsed card-link" data-toggle="collapse" href="#FctChrg7">
					 			 <%=E.EH("What are the conditions for through distance based charging")%>?
					 		</a>
				       </div>
				       	<div id="FctChrg7" class="collapse" data-parent="#accordion">
					 		<div class="card-body">
					  		 <p class="justify"><%=E.EH("The system of charging freight on through distance basis is extended to all block rakes going into the siding directly or indirectly with the engine pulling or pushing, provided (a) there is no detention to engines except for change of ends and (b) no separate shunting staff is required exclusively for this purpose")%>. </p>
					 		</div>
				       </div>
				     </div>
				     			     
				</div>
			  </div>
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    <%=E.EH("Policies and Rules")%>
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 <a class="card-link" data-toggle="collapse" href="#Pol1">
					    <%=E.EH("What are the policies for weighbridge")%>? 
					 </a>
				       </div>
				       <div id="Pol1" class="collapse show" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("All loading points are progressively covered by weighbridges. The in-motion weighbridges are progressively  linked with  Indian Railways Freight Operations Information System (FOIS). Freight customers are permitted to install  weighbridges on railway land at private siding/goods sheds. Guidelines are laid down for Weightometer/Pre-weighbin System of weighment in private sidings")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol2">
					   <%=E.EH("What are the guidelines for weighment of rake")%>? 
					 </a>
				       </div>
				       <div id="Pol2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("The traffic loaded from any terminals are required to be weighed at nominated weighbridges. Zonal Railway notifies associate weighbridge for each loading point and alternate associate weighbridge where weighment will be done if the associate weighbridge is defective")%>. </p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol3">
					   <%=E.EH("Which all commodities are exempted from mandatory weighment")%>?
					 </a>
				       </div>
				       <div id="Pol3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Dispensation from mandatory weighment has been permitted in following cases.Commodities loaded in standard bags of uniform size like foodgrains, sugar, fertilizers, cement etc.HR Coil, CR Coil and other steel consignment which are pre-weighed on certified mill scale and bear marking/label of Central Excise/Customs.Low density commodities namely Petroleum Coke, Metallurgical Coke, Chuni, De-Oiled Cake.Containerized Import traffic where SMTP details are transmitted though EDI from Customs server to FOIS. Nepal bound containerized Import traffic on submission of Custom's document")%>.</p>

					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#Pol4">
					   <%=E.EH("What are the Guidelines for stacking permission")%>?
					 </a>
				       </div>
				       <div id="Pol4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Customers who have placed indent for wagon may apply for free advance stacking permission. Permission for advance stacking is granted keeping in view the traffic pattern, number of rakes handled, availability of space etc. at the station/goods shed concerned")%>.</p>
					 </div>
				       </div>
				     </div>
		</div>
		</div>
			  
			  	<div class="card">
			  	  <div class="card-header head-faq">
			  	    <%=E.EH("Freight Operations Information System (FOIS)")%>
			  	  </div>
			  	  <div class="card-body">
				   <div id="accordion">	   
				     <div class="card">
				       <div class="card-header">
					 <a class="card-link" data-toggle="collapse" href="#FOIS1">
					   <%=E.EH("What steps have been taken to promote digital working in freight business")%>?
					 </a>
				       </div>
				       <div id="FOIS1" class="collapse show" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("A number of initiatives have been taken to promote digital working in freight business e.g. Registration of demand for wagons electronically (e-RD), Electronic Transmission of Railway Receipt (eT-RR), Terminal Management System, Online Interface to Freight Customers, Electronic payment system, Freight Advance Scheme, Online Goods Balance sheet and system generated Money Receipt")%>. <a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS2">
					   <%=E.EH("What is e-RD or Electronic Registration of Demand")%>? 
					 </a>
				       </div>
				       <div id="FOIS2" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Railway has implemented electronic interface for registration of demand for wagons Electronic Registration of Demand(e-RD). It provides electronic demand note facility through FOIS website wherein, customer can register their demand for wagons electronically. They need not to visit goods booking office")%>.<a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS3">
					   <%=E.EH("What is Electronic Railway Receipt or eT-RR")%>? 
					 </a>
				       </div>
				       <div id="FOIS3" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Electronic Railway Receipt or eT-RR has been launched to provide paperless transaction system where Railway Receipt is generated and transmitted electronically to customer through")%> <a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" >FOIS</a>,  <%=E.EH("and even delivery of goods is given through e-surrender of eT-RR")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS4">
					   <%=E.EH("What is Terminal Management System or TMS")%>?
					 </a>
				       </div>
				       <div id="FOIS4" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Terminal Management System or TMS provides system based preparation of Railway Receipt for freight traffic. In recent past, two new features have been added to promote digital working at field level (a) Preparation and submission of online goods balance sheet and (b) System generated Money Receipt which includes preparation of  bill for miscellaneous charges i.e. Demurrage/Wharfage etc")%>.</p>
					 </div>
				       </div>
				     </div>
				     <div class="card">
				       <div class="card-header">
					 <a class="collapsed card-link" data-toggle="collapse" href="#FOIS5">
					   <%=E.EH("Are there any Online Interfaces for Freight Customers")%>?
					 </a>
				       </div>
				       <div id="FOIS5" class="collapse" data-parent="#accordion">
					 <div class="card-body">
					   <p class="justify"><%=E.EH("Online interface through Freight Operation Information system(FOIS) provide all requisite information e.g. freight class and rate for all commodities, position of indent/demand for each station, outstanding indents, shortest/popular routes, terminal handling facilities and freight calculator are available. Customers can track and trace their rakes while login their accounts in FOIS")%>. <a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
					 </div>
				       </div>
				     </div>
		</div>
	  </div>
	  <div class="card">
			  <div class="card-header head-faq">
			    <%=E.EH("How to Reach Us")%>
			  </div>
			  <div class="card-body">
			   <div id="accordion">	   
			     <div class="card">
			       <div class="card-header">
				 <a class="card-link" data-toggle="collapse" href="#Reach1">
				   <%=E.EH("Who can be approached if I want to offer traffic to Indian Railways")%>?
				 </a>
			       </div>
			       <div id="Reach1" class="collapse show" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("Step by Step guidance can be provided by Chief Goods Supervisor/Goods clerk at station.Please visit nearest Goods Shed")%>. </p>
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach2">
				   <%=E.EH("Who decides and fixes the rates for Goods Traffic")%>?
				 </a>
			       </div>
			       <div id="Reach2" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("Central Government/Railway Board is vested with the power to fix rates for transportation of goods and passengers.However,Zonal Railway administration is empowered to quote Station to Station rates and lumpsum rates")%>.</p>

				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach3">
				   <%=E.EH("How can a customer book their Rakes/Wagons with Indian Railways")%>?
				 </a>
			       </div>
			       <div id="Reach3" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("Customer can book their rake through Indian Railway's Freight Operations Inforamtion System (FOIS) website electronically, which is simpler, convenient and transparent process")%>.<a href="https://www.fois.indianrail.gov.in"  target="_blank" class="card-link1" ><%=E.EH("Visit Now")%>..</a></p>
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach4">
				   <%=E.EH("How can we get information about freight policies")%>?
				 </a>
			       </div>
			       <div id="Reach4" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("The information regarding freight policy are uploaded on the webpage of Traffic Commercial Dte under")%> <a href="http://www.indianrailways.gov.in" target="_blank" class="card-link1" >www.indianrailways.gov.in</a></p> 

				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach5">
				   <%=E.EH("If a customer wants to request Railway administration to revise the rates or classification of any commodity, who should be approached")%>?
				 </a>
			       </div>
			       <div id="Reach5" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("Customer may represent to Member Operations and Business Development(MOBD)/ Railway Board for any such relaxation.  They may also approach Zonal Railway who in turn forwards the same to Railway Board along with analysis and their recommendations")%>.</p>
				 </div>
			       </div>
			     </div>
			     <div class="card">
			       <div class="card-header">
				 <a class="collapsed card-link" data-toggle="collapse" href="#Reach6">
				   <%=E.EH("For availing any benefit, who is the point of contact at Zonal and Divisional level")%>?
				 </a>
			       </div>
			       <div id="Reach6" class="collapse" data-parent="#accordion">
				 <div class="card-body">
				   <p class="justify"><%=E.EH("Chief Commercial Manager/Freight Marketing/Freight Services (CCM/FM/FS) and Dy.CCM/FS/FM at Zonal level and Sr. Divisional Commercial Manager (Sr. DCM) at Divisional level are the point of contact for the customers")%>.</p>
				 </div>
			       </div>
			     </div>
	  		</div>
	  </div>
	</div>
      </div>
</div>
      </div>
	</div>
      </div>
</div>
      </div>

<%@ include file="/pages/GG_Footer.jspf" %>
