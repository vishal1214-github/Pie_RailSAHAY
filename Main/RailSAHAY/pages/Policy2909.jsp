<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"SCHEMES AND POLICIES","/RailSAHAY/pages/Policy.jsp",UserDevice,Browser);
%>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:1px;"></nav>

      <div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("INNOVATIVE SCHEMES AND POLICIES")%></h3>
              </div>
            </div>
           </div>
          </div>
          <div class="row">
          <div class="col-lg-6 col-sm-12">
         	<div class="container">
			            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingOne">
			                        <h4 class="panel-title">
			                            <a class="" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
			                                <%=E.EH("Freight Incentive Schemes")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			                        <div class="panel-body">
			                            <ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Automatic Freight Rebate Scheme for traffic loaded in Traditional Empty Flow Directions (TEFD)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Discounted freight is charged at Class LR1/Class 100/Class 110 for train load/wagon load respectively), if traffic is booked in notified empty flow directions. Discount is granted through system. Terms & conditions as per the circular apply")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_08_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Long Term Traffic Contract (LTTC)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Provides freight stability to its customer in the current year of the agreement alongwith the rebate in freight based on incremental growth in Gross Freight Revenue GFR). Rebate is also available for retention of GFR in each year of the agreement period")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2017/RC_14_2017_30032017.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Busy Season Charge")%></p>
											  <p class="list-item-desc"> <%=E.EH("which was levied during busy season @15% on all goods traffic has been withdrawn from 01.10.2019 for all traffic; except Iron Ore and POL traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>1_DPP_120919.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Round Trip Tariff (RTT)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Policy has been implemented from 01.07.2020. Discount is granted to traffic if booked in the return direction, by way of charging the return traffic at lower of the two classes")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_11_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive to fly ash")%></p>
											  <p class="list-item-desc"> <%=E.EH("40% discount in freight is granted to fly ash traffic booked in bagged condition or in bulk/loose condition when loaded in Open Stock. If it is loaded in bagged condition, then flat wagons are also granted 40% concession; and Covered wagons are charged at Class LR1")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_09_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/fly%20ash%20concession_03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Short lead concession")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been re-introduced from 01.07.2020 under which discount in freight at the rate of 50%, 25% and 10% is granted to the traffic booked upto 0-50KM, 51-75KM and 76-90 KM respectively except Coal & Coke and Iron ore traffic. Further, Zonal Railways have been empowered to get into long term contracts with customers for short lead traffic")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/RC_16_20.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Short_Lead_05082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Concession on long lead traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been introduced from 01.07.2020 for Coal & Coke, Iron ore and Iron & Steel traffic under which discount in freight is granted; 20% to Coal & Coke for distance >1400KM, 20% to Iron & Steel for distance >1600KM; and 15% for distance >700KM and 20% for distance >1500 Km for Iron ore")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Rates%20circular%2015_30062020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Free time relaxation for covered wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Zonal Railways are empowered to relax the free time upto double of normal free time and/or non levy of demurrage/wharfage in case of covered stock during lean season i.e. upto 30.09.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Relaxation%20in%20free%20time%20for%20loading-%20unloading%20of%20covered%20wagons.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Permission to accept road weighbridge weighment")%></p>
											  <p class="list-item-desc"> <%=E.EH("To certain goods sheds of SCR for loading of Granite- all documents and data to be captured in TMS")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Approva1_310720.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Terminal Access Charge Concession @50%")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been granted on container traffic handled at Group-III CRTs")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corr_37_RMC_CRT_reg_TAC.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("5% concession on loaded containers")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been permitted with effect from 04.08.2020 to 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Discount%20on%20loaded%20containers03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive Scheme for loading of Bagged consignment in open and flat wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Rebate of 20% on cement, china clay, chemical manure, food grain etc., and rebate of 30% on fly ash,urea. Besides large size bags upto 2.5 tons have been permitted on open wagons")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/RC-27-2016.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/Corri_3_RC_27_2016.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										</ul>

			                        </div>
			                    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingTwo">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			                               <%=E.EH("Transporation Product")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			                        <div class="panel-body">
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Minimum number of wagons for availing trainload benefit for BCNHL")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been reduced from 58 wagons to 42 wagons w.e.f. 16.04.2020 till 30.09.2020")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_31_RMC_1Mini_Rake_190520.pdf" target="_blank"><%=E.EH("Corrigendum")%> (19.05.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Supplementary charge")%></p>
											  <p class="list-item-desc"> <%=E.EH("Which was levied @5% on Mini Rake and Two point rake has been withdrawn from 01.10.2019")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_22_RMC_MiniRake_1209190001.pdf" target="_blank"><%=E.EH("Corrigendum")%> (12.09.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_24_RMC_Mini_rake_200919.pdf" target="_blank"><%=E.EH("Corrigendum")%> (20.09.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Distance for operation of Mini Rake (20 wagons)")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been increased to 1500 KM")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("The distance between two point/multi point")%></p>
											  <p class="list-item-desc"> <%=E.EH("rake operation has been increased to 500 KM")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two-point booking of automobile traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted in NMG, BCCNR and BCABM wagons")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										</ul>
			                        </div>
			                    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingThree">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
			                                <%=E.EH("Ease of Doing Business")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
			                        <div class="panel-body">
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Registration of demand for wagons electronically (e-RD)")%> </p>
											  <p class="list-item-desc"> <%=E.EH("has been implemented to provide electronic demand note facility through FOIS website wherein, customer can register their demand for wagons electronically. This facility has been extended to Container traffic, Freight Forwarders, Iron & Steel, Iron Ore, Salt and Granite traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/e_RD_RMC_111119.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Addendum_e-RD_RMC_030420.pdf" target="_blank"><%=E.EH("Addendum")%> (03.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Electronic Transmission of Railway Receipt (eT-RR)")%> </p>
											  <p class="list-item-desc"> <%=E.EH("has been launched to provide paperless transaction system where Railway Receipt is generated and transmitted electronically to customer through FOIS, and even delivery of goods is given through e-surrender of eT-RR.This facility has been extended to container traffic, Freight Forwarders, Iron & Steel, Iron Ore,Salt and Granite traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>_22_RMC_MiniRake_1209190001.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Adendum_RMC_eT-R_030420.pdf" target="_blank"><%=E.EH("Addendum")%> (03.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Terminal Management System (TMS)")%></p>
											  <p class="list-item-desc"> <%=E.EH("provides system based preparation of Railway Receipt for freight traffic. Two new features have been added to promote digital working (a) Preparation and submission of online goods balance sheet and (b) System generated MoneyReceipt for ancillary charges e.g. Demurrage, Wharfage etc")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/MR_TMS_FOIS_29112019.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Online interface to Freight Customers")%></p>
											  <p class="list-item-desc"> <%=E.EH("through Freight Operation Information system(FOIS) to provide all requisite information e.g. freight class and rate; position of indent/demand for each station; outstanding indents, shortest/popular routes, terminal handling facilities; freight calculator; facility to track and trace rakes")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/<%=E.EH("Corrigendum")%>_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("E-Drishti updation for Goods Sheds")%></p>
											  <p class="list-item-desc"> <%=E.EH("Zonal Railways have been directed that details of facilities at all goods sheds and recent photographs (including night photographs for lighting levels) be uploaded on E-Drishti portal")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/e-Drishti%20updation%20for%20Goods%20sheds-20200320175305.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Electronic Payment System")%></p>
											  <p class="list-item-desc"> <%=E.EH("enables collection of freight charges directly from customer’s bank account. In recent past, Automobile Freight Train Operators (AFTOs) have been allowed single agreement e-payment facility with base terminal Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://www.indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Marketing_2019/Corrig_3_rmc_200519.pdf" target="_blank"><%=E.EH("View Detail")%> (2014)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Epayment_270819.pdf" target="_blank"><%=E.EH("View Detail")%> (2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Freight Advance Scheme")%></p>
											  <p class="list-item-desc"> <%=E.EH("Offers Tariff certainty in lieu of freight advance. Under this scheme, Railway shall accord priority in allocation of rakes within same class to freight customers who have signed agreement. The scheme is open to customers who are e-payment customers for freight payment and have given minimum annual freight revenue of ? 500 crore as freightpayee in previous calendar year")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Guidelines%20regarding%20Freight%20Advance%20Scheme%20dt_27_01_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										</ul>

			                        </div>
			                    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingFour">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseWgmt" aria-expanded="false" aria-controls="collapseThree">
			                               <%=E.EH("Customer-Friendly Rationalization of Weighment Policy")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseWgmt" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
			                        <div class="panel-body">
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("In case of variation between two weighment results of a rake, the weight of second weighment will be the chargeable weight and not the higher of the two")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Weighment_250719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("On detection of overloading in a rake, if load adjustment is not feasible at originating point, then it may be permitted at nearby location and transit time from weighment point to load adjustment point for levy of Detention Charge has been capped to maximum of two hours plus actual duration of load adjustment")%>. </p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Weighment_250719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("DRMs vested with full powers")%></p>
											  <p class="list-item-desc"> <%=E.EH("to permit loading if a weighbridge gets out of order for three months")%>. </p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Guidelines%20regarding%20provision%20and%20functioning%20of%20weighbridges_110719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"><%=E.EH("Zonal Railways empowered to permit installation of Preweighbin or Weightometer for weighment of goods traffic in private sidings")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Guidelines%20regarding%20provision%20and%20functioning%20of%20weighbridges_110719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Exemption from mandatory weighment")%></p>
											  <p class="list-item-desc"><%=E.EH("in case of loading of Standard Bags of uniform size in container, Low density commodities like Pet Coke, Met Coke, Chuni and De-oiled cake")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Weighment_250719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Exemption from mandatory weighment")%></p>
											  <p class="list-item-desc"> <%=E.EH("in case of import containerized traffic to eight container operators (CONCOR, GRFL, DLI, Adani, ICT&IPL, HTPL, IILPL and PMLPPL) on the basis of transmission of SMTP details from Custom server to FOIS via EDI. Further, Nepal bound containerized import traffic has also been exempted from mandatory weighment from 02.09.2019 on the basis of Custom document submitted by CONCOR")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Weighment_250719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Addendum")%>_RMC_weighment_020919.pdf" target="_blank"><%=E.EH("Addendum")%> (2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Permission to accept road weighbridge weighment")%></p>
											  <p class="list-item-desc"> <%=E.EH("to certain goods sheds of SCR for loading of Granite- all documents and data to be captured in TMS")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Approva1_310720.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("GMs given the target for integration of all weighbridges with FOIS by 22.08.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/MT%20(D_O)%20LETTER.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										</ul>
			                        </div>
			                    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingFive">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseCont" aria-expanded="false" aria-controls="collapseThree">
			                               <%=E.EH("Encouraging Container Traffic")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseCont" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
			                        <div class="panel-body">
			                        	<p class="list-item-ctgr"><%=E.EH("RELAXATION IN HAULAGE CHARGE")%></p>
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("To promote containerizations following measures have been taken, ninety additional commodities have been de-notified and brought under Haulage Charge per TEU rates. Out of 641 commodities notified in goods tariff, 507 commodities are allowed to move in containers at FAK and 38 commodities at Container Class Rate (by applying 15% concession on applicable class rate)")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Marketing_2019/TCR_10052019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (13.05.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/TCR_31052019_2.pdf" target="_blank"><%=E.EH("Corrigendum")%> (31.05.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>_28_CRT_CCR_220719.pdf" target="_blank"><%=E.EH("Corrigendum")%> (22.07.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/RMC_Container_080415.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Chemical Gypsum, a restricted commodity, has been allowed")%></p>
											  <p class="list-item-desc"> <%=E.EH("To move in containers at CCR, valid upto 20.10.2020")%> </p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_32_CRT-CCR_Hub_Spoke_170420.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Charging Kota Stone and Toluene at Haulage Charge per TEU rates extended")%></p>
											  <p class="list-item-desc"> <%=E.EH("till further advice")%> </p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_33Hub_Spoke_200420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (20.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/No_TC-1_2014_302-2_13_07_2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (13.07.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"><%=E.EH("Charging of CR Coils by applying 20% concession on applicable class rate extended till further advic")%>e</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_34_RMC_CRT-CCR-Hub%26Spoke_300420_R.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.04.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Round trip based charging")%></p>
											  <p class="list-item-desc"> <%=E.EH("implemented to provide an economical and reliable alternative for ultra short lead (upto 50Kms) container traffic which are moving at Haulage Charge per TEU rates, valid upto 14.09.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_30_CRT_CCR_060919.pdf" target="_blank"><%=E.EH("Corrigendum")%> (06.09.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"><%=E.EH("New delivery model of Double Stack Dwarf Container to be charged by applying 17% concession on Haulage Charge per TEU rate extended upto 31.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2017/Corrigendum_RC_22_2017.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.03.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("An innovative business decision for movement of empty containers and empty flat wagon for private container rakes at a discount of 25%, valid upto 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/<%=E.EH("Corrigendum")%>_6_RC_20_2018.pdf" target="_blank"><%=E.EH("Corrigendum")%> (01.05.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Discount of 5% Haulage Charge per TEU rates on loaded containers")%></p>
											  <p class="list-item-desc"> <%=E.EH("from 04.08.2020 to 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Discount%20on%20loaded%20containers03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.08.2020)..</a></p>
										  </li>
										</ul>
			                        	<p class="list-item-ctgr"><%=E.EH("LIBERALIZATION IN USAGE OF RAILWAY OWNED TERMINALS")%></p>
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Policy for operation of container trains in railway terminals by notifying as Container Rail Terminal (CRT) extended upto 31.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://www.indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/Operation%20of%20contianer%20trains_13032019.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Methodology for levying of Terminal Access Charge from 1.5 times to one time for double operation (i.e. unloading followed by loading) extended till further advice")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/TC_08072019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (08.07.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Rate of Terminal Access Charge reduced in case of Group-III CRT to be levied @ 50% of the base rate")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corr_37_RMC_CRT_reg_TAC.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Benefit of night incentive at par with goods traffic extended to container traffic handled at CRT")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/TCR_Container_Rail_Terminal_Night.pdf" target="_blank"><%=E.EH("Corrigendum")%> (26.06.2019)..</a></p>
										  </li>
										</ul>
			                        </div>
			                    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingSix">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseCovid" aria-expanded="false" aria-controls="collapseThree">
			                               <%=E.EH("Measures in view of COVID Pandemic")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseCovid" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
			                        <div class="panel-body">
										<p class="list-item-desc"> <%=E.EH("Exemption from levy of ancillary charges namely Demurrage, Wharfage, Stacking, Stabling, Detention and Ground Usage charges since 22.03.2020 up o 17.05.2020. Post which, these charges were levied with relaxation by doubling the applicable free time for loading/unloading up to 31.05.2020. Further, Railways continue to exercise their powers for waiver of these charges as per prevailing local conditions. In order to provide the relief to customers loading goods traffic in case of covered stock, Railways are empowered to relax the free time upto double of normal free time and/or non levy of demurrage/wharfage during lean season i.e. upto 30.09.2020")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://www.indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Resumption%20of%20levy%20of%20Demurrage%20wharfage%20etc%20Wef%2018_05_2020.pdf" target="_blank"><%=E.EH("View Detail")%> (15.05.2020)</a></p>
										<p class="list-item-link"><a class="card-link1" href="http://www.indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Relaxation%20in%20free%20time%20for%20loading-%20unloading%20of%20covered%20wagons.pdf" target="_blank"><%=E.EH("View Detail")%> (27.07.2020)</a></p>
			                        </div>
			                    </div>
			                </div>
			    </div>
</div>
          </div>
		<div class="col-lg-6 col-sm-12">
			<div class="card">
			  <div class="card-header headcard"><%=E.EH("Policies/Rate Circulars")%></div>
			  <div class="card-body">
				<ul class="list-group list-group-flush">
				  <li class="list-group-item">
					  <p class="list-item-desc"> <%=E.EH("Liberalized Wagon Investment Scheme (LWIS)")%> <a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/FM_07_08_LWIS.pdf" target="_blank"><%=E.EH("View Detail")%></a></p>
				  </li>
				  <li class="list-group-item">
					  <p class="list-item-desc"> <%=E.EH("Registration of Demand for Wagons electronically through web (e-RD)")%> <a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/RC_21_14_Edmnd.pdf" target="_blank"><%=E.EH("View Detail")%></a></p>
				  </li>
				  <li class="list-group-item">
					  <p class="list-item-desc"> <%=E.EH("Electronic Transmission of Railway Receipt")%> <a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/RC_02_15_Revised_ETRR.pdf" target="_blank"><%=E.EH("View Detail")%></a></p>
				  </li>
				  <li class="list-group-item">
					  <p class="list-item-desc"> <%=E.EH("Freight Forwarder Scheme")%> <a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/RMC_FIS_160615_FFS.pdf" target="_blank"><%=E.EH("View Detail")%></a></p>
				  </li>
				  <li class="list-group-item">
					  <p class="list-item-desc"> <%=E.EH("Station to Station Rates")%> <a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/RC_26_2016_STS.pdf" target="_blank"><%=E.EH("View Detail")%></a></p>
				  </li>
				</ul>
			  </div>
			  <div class="card-footer"><a class="card-link1" href="javascript:void(0)" onclick="openExtnLink('RATE_CIRCULAR','https://indianrailways.gov.in/railwayboard/view_section.jsp?lang=0&id=0,1,304,366,555,765');"><%=E.EH("More")%>..</a></div>
			</div>
		  </div>
		</div>
<%@ include file="/pages/GG_Footer.jspf" %>
