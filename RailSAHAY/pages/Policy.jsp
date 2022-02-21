<%@ include file="/pages/GG_Header.jspf" %>
<% 

strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"REBATE SCHEMES AND LONG TERM CONTRACTS","/RailSAHAY/pages/Policy.jsp",UserDevice,Browser);
%>
	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:1px;"></nav>

      <div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("REBATE SCHEMES AND LONG TERM CONTRACTS")%></h3>
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
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
			                                <%=E.EH("Freight Incentive Schemes/Concessions")%>
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
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrig_1_RC_8_20.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Long Term Traffic Contract (LTTC)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Provides freight stability to its customer in the current year of the agreement alongwith the rebate in freight based on incremental growth in Gross Freight Revenue GFR). Rebate is also available for retention of GFR in each year of the agreement period")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2017/RC_14_2017_30032017.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Station to Station Rates (STS)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Concession in freight rates for specific stream of traffic, for specific commodities for movement between specific pair of originating and destination stations/points. Existing as well as New Traffic shall be eligible concession under STS")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://fois.indianrail.gov.in/FOISWebPortal/resources/pdf/RC_26_2016_STS.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Round Trip Tariff (RTT)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Policy has been implemented from 24.10.2020 and will remain valid upto 23.10.2021.  Under this policy, freight discount is granted to traffic if customer offers to book traffic in onwards as well as return direction. The return traffic under this traffic is charged at lower of the two classes of onward vis a vis return traffic, effectively both onwards at return traffic/commodities are charged at lower class among these, subject to terms and conditions applicable, except for coal and coke for which maximum discount is capped at 10%. Rail users desirous of availing incentives under RTT policy shall be required to apply to Zonal Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_11_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Concession on long lead traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been introduced from 01.07.2020 for Coal & Coke, Iron ore and Iron & Steel traffic under which discount in freight is granted, 20% to Coal & Coke for distance >1400KM, 20% to Iron & Steel for distance >1600KM; and 15% for distance >700KM and 20% for distance >1500 Km for Iron ore")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_27_2020_R.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Short lead concession")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been re-introduced from 01.07.2020 under which discount in freight at the rate of 50%, 25% and 10% is granted to the traffic booked upto 0-50KM, 51-75KM and 76-90 KM respectively except Coal & Coke and Iron ore traffic. Further, Zonal Railways have been empowered to get into long term contracts with customers for short lead traffic")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/RC_16_20.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Short_Lead_05082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Non-levy of Terminal charges at unloading point")%></p>
											  <p class="list-item-desc"> <%=E.EH("For the traffic booked to and from notified alternate goods shed instead of busy goods shed. Terminal charge is otherwise levied at Rs.20 per tonne for goods sheds and PFTs. This intends to offer efficient and faster handling of goods at less busy terminals for benefit of customer. This will also help Indian Railway in better utilization of its terminal infrastructure")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC-14_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
                                                                                  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Permissible Carrying Capacity")%></p>
											  <p class="list-item-desc"> <%=E.EH("for loading of Pet Coke in Open wagons has been reduced, thereby, reducing the chargeable tonnage per wagon. This has been done with a view to make Pet Coke freight more viable by eliminating the payment of dead freight by customer, as pet coke is high volume-low weight commodity")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corrigendum%20no_2%20to%20Rates%20Master%20Circular%20PCC.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.06.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/PCC-Pet%20Coke.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive Scheme for loading of Bagged consignment in open and flat wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("This scheme endeavor to utilize open and flat wagons for loading of commodities that otherwise favour covered wagons. Rebate of 20% on cement, china clay, chemical manure, food grain etc. and rebate of 30% on fly ash, urea is offered. Further, as industry demanded for permitting large sized bags so that it could result in cost savings to customers, same has been permitted upto  2.5 tons per bag for open wagons (only)")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/RC-27-2016.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/Corri_3_RC_27_2016.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.10.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Rates for NMG rakes")%></p>
											  <p class="list-item-desc"> <%=E.EH("been notified for movement to Bangladesh Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/allowing%20NMG%20coaches%20to%20B-desh.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("25% concession extended")%></p>
											  <p class="list-item-desc"> <%=E.EH("with retrospective effect i.e. from 30.04.2020 from new loading point Lingraj SILO siding of MCL (LSST) for movement of NTPC’s coal traffic over East Coast Railway.This is a special case of MGR(Merry Go Round) operation system for transportation of coal")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/25%20percent%20concession%20MGR%20type%20ECoR.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Rebate in freight for commodities loaded in privately owned wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Rebate in freight for commodities loaded in privately owned wagons notified for the period from 01.10.2020 to 30.09.2021. These are the private wagons procured before currently prevailing wagon investment schemes")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_24_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
                                                                                  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Debit/Credit system of Demurrage")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted in case of private sidings, upto 30.09.2021. Demurrage is a charge levied for detention of wagons beyond permissible free time. It is levied on each case of loading and unloading separately. However, now this policy initiative permits demurrage for private sidings to be charged on monthly basis, and to debit-credit or to adjust the time saved in one wagon against other, subject to laid down terms and conditions")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Debit-credit-220121.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Debit%20Credit%20Hours%20In%20Case%20Of%20Private%20Sidings.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Free time for loading/unloading increased")%></p>
											  <p class="list-item-desc"> <%=E.EH("in case of bagged consignment in open wagons at EOL terminals from three hours to six hours,upto 31.03.2021")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC_I_Corrigendum_12%20to%20RMC%20Demurrage.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Additional free time of one hour per rake")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted for covering open wagons with tarpaulins during loading of loose/bulk commodity (e.g. Coal & Coke etc., for which packing condition P2(a) is prescribed in the Goods Tariff) at all kinds of freight terminals e.g. goods sheds, sidings (including EOL sidings), PFTs etc")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC_I_Corr_14_21102020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (21.10.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Penalty for overloading")%></p>
											  <p class="list-item-desc"> <%=E.EH("Detention Charge levied @5000/- per overloaded wagon has been withdrawn. Punitive Charge rules have been revised such that on detection of overloading at other than originating point, if customer carries out load adjustment at such point, Punitive Charge will be levied for the distance travelled by the train from originating station to the load adjustment point, rather upto destination point as was the previous rules")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum%20No_2%20to%20Rates%20Madter%20Circular%20weighment20190.pdf" target="_blank"><%=E.EH("Corrigendum")%> (16.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("PCC and loading tolerance of BCFC and BCFCM wagons notified")%></p>
											  <p class="list-item-desc"> <%=E.EH("Since these wagons are loaded using precision loading mechanism using controlled discharge techniques, therefore, loading tolerance for these wagons have been reduced. This results in higher permissible carrying capacity(PCC). It benefits both customer and railways by increasing the throughput. These wagons are largely used to carry cement, fly ash etc")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum%20No_6_17092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (17.09.2020)..</a></p>
										  </li>
                                                                                   <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Freight Incentive Scheme")%></p>
											  <p class="list-item-desc"> <%=E.EH("For loading of Bed Ash was notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corrigendum-2-to-RC-09-2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("LTTC policy reviewed")%></p>
											  <p class="list-item-desc"> <%=E.EH("that no new LTTC contract shall be executed on zonal railways w.e.f. 18.12.2020")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/review-lttc.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Power has been delegated to DRMs")%></p>
											  <p class="list-item-desc"> <%=E.EH("to implement round the clock working at goods sheds/sidings")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/Corrigendum_15_Demurrage_Wharfage_040121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (04.01.2021)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Zonal Railways have empowered")%></p>
											  <p class="list-item-desc"> <%=E.EH("to enhance free time for loading/unloading of wagons and removal of consignment in case of alternate goods sheds")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/Corri-16-Demurrage-waiver-2016.pdf" target="_blank"><%=E.EH("Corrigendum")%> (11.01.2021)..</a></p>
										  </li>

									</ul>			                       
                                                  </div>
			                    </div>
			                </div>
			                
					<div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingOneA">
						<h4 class="panel-title">
						    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOneA" aria-expanded="false" aria-controls="collapseTwo">
						       <%=E.EH("Concessions under various Schemes")%>
						    </a>
						</h4>
					    </div>
					    <div id="collapseOneA" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOneA">
						<div class="panel-body">
										<ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Withdrawal of Busy Season Charge on all Commodities except Iron Ore and POL Traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("which was levied during busy season @15% on all goods traffic has been withdrawn from 01.10.2019 for all traffic; except Iron Ore and POL traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>1_DPP_120919.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Terminal Access Charge Concession @50%")%></p>
											  <p class="list-item-desc"> <%=E.EH("A concession of 50% has been granted on Terminal Access Charges applicable on Group-III CRTs")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corr_37_RMC_CRT_reg_TAC.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive Scheme for loading of Bagged consignment in open and flat wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Rebate of 20% on cement, china clay, chemical manure, food grain etc., and rebate of 30% on fly ash,urea. Besides large size bags upto 2.5 tons have been permitted on open wagons")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/RC-27-2016.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/Corri_3_RC_27_2016.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>	
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("40% Discount on Fly Ash Traffic loaded in open wagon")%></p>
											  <p class="list-item-desc"> <%=E.EH("40% discount in freight is granted to fly ash traffic booked in bagged condition or in bulk/loose condition when loaded in Open Stock. If it is loaded in bagged condition, then flat wagons are also granted 40% concession; and Covered wagons are charged at Class LR1")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_09_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/fly%20ash%20concession_03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>	
										</ul>
						</div>
					    </div>
			                </div>
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingTwo">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
			                               <%=E.EH("Transportation Products")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
			                        <div class="panel-body">
										<ul class="list-group list-group-flush">	
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Reduced minimum number of wagons for availing trainload benefit for BCNHL")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been reduced from 58 wagons to 42 wagons initially w.e.f. 16.04.2020 till 30.09.2020. The same has been further extended upto 31.06.2021")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_31_RMC_1Mini_Rake_190520.pdf" target="_blank"><%=E.EH("Corrigendum")%> (19.05.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/BCNHL%20from%2057-42%20wagons.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Transportation%20Product-Block%20RakeMini%20Rake_Corri_11.pdf" target="_blank"><%=E.EH("Corrigendum")%> (24.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Distance, for operation of Mini Rake (20 wagons), has been increased to 1500KM")%></p>
											  <p class="list-item-desc"> <%=E.EH("Mini rakes have also been permitted beyond 1500KM upto 2000KM and beyond 2000KM on payment of supplementary charge @7.5% and @10%, respectively. This charge is levied on graded manner, i.e. only for the relevant distance. This is valid upto 31.12.2020.  This distance restriction modified w.e.f. 01.01.2021 till 30.06.2021 and mini rakes allowed up to 500km with NIL supplementary charge; beyond 500KM upto 1000KM with supplementary charge @5%; beyond 1000km up to 2000km with supplementary charge @7.5%; and beyond 2000km with supplementary charge @10%")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/corr%2004%20to%20RMC%20block%20rakeetc2020%20Aug%2028%2C%202020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (28.08.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/mini%20rake%20movement.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Transportation%20Product-Block%20RakeMini%20Rake_Corri_11.pdf" target="_blank"><%=E.EH("Corrigendum")%> (24.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two-point combination for Automobile Traffic/Mini Rake and Steel Traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("Automobile traffic has been permitted in NMG, BCCNR and BCABM wagons")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-desc"> <%=E.EH("23 pairs of two point combination for other than covered stock for steel traffic notified")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corr_2%20today.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Supplementary charge which was levied @5% on Mini Rake and Two point rake was withdrawn from 01.10.2019")%></p>
											  <p class="list-item-desc"> <%=E.EH("The charge was modified w.e.f. 01.01.2021 till 30.06.2021 in graded form for Mini Rakes as NIL up to 500km; beyond 500KM upto 1000KM @5%; beyond 1000km up to 2000km @7.5%; and beyond 2000km @10%")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_22_RMC_MiniRake_1209190001.pdf" target="_blank"><%=E.EH("Corrigendum")%> (12.09.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_24_RMC_Mini_rake_200919.pdf" target="_blank"><%=E.EH("Corrigendum")%> (20.09.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corri_13_Mini_Rake_311220.pdf" target="_blank"><%=E.EH("Corrigendum")%> (31.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two point destination terminals should not be more than 500KM apart in lean season as well as in busy season")%></p>
											  <p class="list-item-desc"> <%=E.EH("Further, two point originating/destination terminals should not be more than 500KM apart. This distance restriction was earlier 200KM. It has now been relaxed to 500KM. The validity of this policy is up to 31.12.2020. The policy extended from 01.01.2021 up to 30.06.2021 as with NIL supplementary charge up to 200km; beyond 200KM upto 500KM with supplementary charge @5%. All two point combination for steel traffic allowed with supplementary charge @5%")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/two%20point%20not%20more%20than%20500km.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Transportation%20Product-Block%20RakeMini%20Rake_Corri_11.pdf" target="_blank"><%=E.EH("Corrigendum")%> (24.12.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corri_13_Mini_Rake_311220.pdf" target="_blank"><%=E.EH("Corrigendum")%> (31.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two-point booking of automobile traffic")%> </p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted in NMG, BCCNR and BCABM wagons")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two point combination for other than covered stock for steel traffic are notified by Railway Board")%>.</p>
											  <p class="list-item-desc"> <%=E.EH("Recently, 23 new pairs have been included in the list")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corr_2%20today.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Automatic Freight Rebate Scheme for traffic loaded in traditional empty flow direction(TEFD)")%></p>
											  <p class="list-item-desc"> <%=E.EH("The scheme lays down minimum number of wagons in a rake that are eligible for availing this scheme. Recently, this number has been reduced to 10 wagons for BCN and BCNHL rakes. BCN and BCNHL are covered wagons usually used for transportation of bagged commodities like food grains, sugar, cement etc")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/corrigendum%202%20TEFD.pdf" target="_blank"><%=E.EH("Corrigendum")%> (18.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Running of freight trains over seven CC+8 routes are included over Northern Railway")%>.</p>
											  <p class="list-item-desc"> <%=E.EH("Higher payload helps railways as well as customers with better stock utilization and higher throughput")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_CC%20plus%208%20NR.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("NMG rakes")%></p>
											  <p class="list-item-desc"> <%=E.EH("Allowed with two point unloading destinations for automobile traffic")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Standard Rake Size and Minimum number of wagon to be loaded in case of BAFRDR")%></p>
											  <p class="list-item-desc"> <%=E.EH("wagons validity extended up to 31.03.2021")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/05_bafrdr_14092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Routes Notifications")%></p>
											  <p class="list-item-desc"> <%=E.EH("Two CC+8 routes were notified over Northern Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/corri-1-running-freight-trains.pdf" target="_blank"><%=E.EH("Corrigendum")%> (23.11.2020)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("Routes of running of 25 tonne Axle load wagons were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corrigendum-12-RMC-25-Tonnne-axle.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.11.2020)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("Routes of running of 25 Tonne Axle load wagons/SER were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corrigendum%2013%20to%20RMC%20PCC.pdf" target="_blank"><%=E.EH("Corrigendum")%> (11.11.2020)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("04 routes of Two Point Combination for steel traffic were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corri-10-RMC.pdf" target="_blank"><%=E.EH("Corrigendum")%> (17.12.2020)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("Four routes as CC+8 notified over Western Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/CC%2B8%20MSH%20PTN%20BLDI_Corri_14_RMC_PCC_291220.pdf" target="_blank"><%=E.EH("Corrigendum")%> (29.12.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corri-15-2020-CC-8.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.12.2020)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("02 Routes of running of 25 Tonne Axle load wagons over DFCCIL were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/Corri_16_RMC_PCC_070121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (07.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("One route of 25 ton axle load were notified over ECoR")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/25%20tonne%20axle%20load%20over%20ECoR_Corri_17_PCC_140121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("03 routes of CC+8 were notified over NER")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/CC%2B8%20over%20NER_Corri_18_PCC_140121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("02 routes of Two Point Combination for steel traffic were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/Corri-14-RMC-2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (19.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("04 routes of CC+8 routes were notified over SER & ER")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/Corri-19-RMC-2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (19.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("02 routes of CC+8 routes were notified over WR")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/Corrigendum-20-RMC-2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (20.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("02 routes of Two Point Combination for steel traffic were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/corrig-15-2020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (22.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("03 routes of CC+8 over SECR/NCR/WR were notified")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/Corrigendum-21-2020-250121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.01.2021)..</a></p>
											  <p class="list-item-desc"> <%=E.EH("04 routes of CC+8 over ER & 1 route over SECR")%>.</p>
											  <p class="list-item-desc"> <%=E.EH("BOXNHL25T wagon was notified as fit for 25 tonne axle load route, Excepted CC+6, Universalized CC+6 and CC+8 route")%>.</p>
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
											  <p class="list-item-head"><%=E.EH("e-Registration of Customers")%> </p>
											  <p class="list-item-desc"> <%=E.EH("A customer can register with Indian Railways by filling details and uploading scanned copies of self-attested, KYC documents- Passport Size Photo, PAN Card, Aadhaar Card and ID Card")%></p>
											  <p class="list-item-link"><a class="card-link1" href="/RailSAHAY/resources/pdf/Corri-RMC-e-RD-291220.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="javascript:void(0)" onclick="regUser();"><%=E.EH("Visit Now")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Registration of demand for wagons electronically (e-RD)")%> </p>
											  <p class="list-item-desc"> <%=E.EH("has been implemented to provide electronic demand note facility through FOIS website wherein, customer can register their demand for wagons electronically. This facility has been extended to Container traffic, Freight Forwarders, Iron & Steel, Iron Ore, Salt and Granite traffic.The process for registration of customer has been eased out. The requirement of customer to visit Sr. DCM’s office for verification of document has been done away with. Customer will now be asked to upload self attested documents and indicate choice of his/her Division. Sr. DCM will download the documents and complete the process of registration")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/e_RD_RMC_111119.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Addendum_e-RD_RMC_030420.pdf" target="_blank"><%=E.EH("Addendum")%> (03.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corri-RMC-e-RD-291220.pdf" target="_blank"><%=E.EH("Corrigendum")%> (29.12.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/e-Demand-250121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.01.2021)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Electronic Transmission of Railway Receipt (eT-RR)")%> </p>
											  <p class="list-item-desc"> <%=E.EH("has been launched to provide paperless transaction system where Railway Receipt is generated and transmitted electronically to customer through FOIS, and even delivery of goods is given through e-surrender of eT-RR.This facility has been extended to container traffic, Freight Forwarders, Iron & Steel, Iron Ore,Salt and Granite traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>_22_RMC_MiniRake_1209190001.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Adendum_RMC_eT-R_030420.pdf" target="_blank"><%=E.EH("Addendum")%> (03.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/Electronic%20Transmission%20of%20Railway%20Receipt_180121.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Terminal Management System (TMS)")%></p>
											  <p class="list-item-desc"> <%=E.EH("provides system based preparation of Railway Receipt for freight traffic. Two new features have been added to promote digital working (a) Preparation and submission of online goods balance sheet and (b) System generated MoneyReceipt for ancillary charges e.g. Demurrage, Wharfage etc")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/MR_TMS_FOIS_29112019.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Generation%20of%20Money%20Receipt%20(MR)%20through%20system%20(TMS-FOIS).pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Preparation%20of%20online%20Goids%20Balance%20Sheet.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Online interface to Freight Customers")%></p>
											  <p class="list-item-desc"> <%=E.EH("through Freight Operation Information system(FOIS) to provide all requisite information e.g. freight class and rate; position of indent/demand for each station; outstanding indents, shortest/popular routes, terminal handling facilities; freight calculator; facility to track and trace rakes")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/<%=E.EH("Corrigendum")%>_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
										  </li>
										  <%--
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("E-Drishti updation for Goods Sheds")%></p>
											  <p class="list-item-desc"> <%=E.EH("Zonal Railways have been directed that details of facilities at all goods sheds and recent photographs (including night photographs for lighting levels) be uploaded on E-Drishti portal")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/e-Drishti%20updation%20for%20Goods%20sheds-20200320175305.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>--%>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Electronic Payment System")%></p>
											  <p class="list-item-desc"> <%=E.EH("enables collection of freight charges directly from customer’s bank account. In recent past, Automobile Freight Train Operators (AFTOs) have been allowed single agreement e-payment facility with base terminal Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://www.indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Marketing_2019/Corrig_3_rmc_200519.pdf" target="_blank"><%=E.EH("View Detail")%> (2014)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Epayment_270819.pdf" target="_blank"><%=E.EH("View Detail")%> (2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Freight Advance Scheme")%></p>
											  <p class="list-item-desc"> <%=E.EH("Offers Tariff certainty in lieu of freight advance. Under this scheme, Railway shall accord priority in allocation of rakes within same class to freight customers who have signed agreement. The scheme is open to customers who are e-payment customers for freight payment and have given minimum annual freight revenue of Rs 500 crore as freightpayee in previous calendar year")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Guidelines%20regarding%20Freight%20Advance%20Scheme%20dt_27_01_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Issue of Money Receipt in the name of handling agent of freight customer has been permitted")%></p>
											  <p class="list-item-desc"> <%=E.EH("Handling agents were already recognized as freight payee entity under e-payment agreement. It has now been taken into cognizance that handling agents in certain cases are appointed by customers for payment of demurrage, wharfage and other charges. Now under GST regime, it is important to issue Money Receipt in favour of handling agents, so as to ensure proper implementation of GST and also so that handling agents are able to avail input tax credit as due")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_23_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("FOIS has developed Freight Business Development portal wherein the facility for uploading application by customer for various freight activities have been provided")%></p>
											  <p class="list-item-desc"> <%=E.EH("To ease out the business process, it has been decided that freight customers (registered/non-registered/new customer) may be allowed to upload his/her signed application on the FBD portal itself for various freight activities like stacking permission, diversion, rebooking, short of destination delivery, waiver of demurrage/wharfage, Station to Station Rates etc. System will forward the same to the concerned Sr. DCM or Sr. DOM, as the case may be, on his mail ID. Sr. DCM/Sr. DOM may download the same and will process it further according to the extant guidelines. This will facilitate the customer and make business process hassle free")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC-34-2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Guidelines regarding preparation of online goods balance sheet have been reiterated")%>.</p>
											  <p class="list-item-desc"> <%=E.EH("Zonal railways have been directed to ensure correct feeding of data and timely submission of balance sheet in FOIS")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Preparation-Online-Goods-130121.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Preparation of Demurrage & Wharfage bill through the system")%></p>
											  <p class="list-item-desc"> <%=E.EH("Zonal Railways have been directed for correct feeding of data in respect of Demurrage & Wharfage in TMS/FOIS and also to ensure system based preparation of Demurrage & Wharfage bill")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Preparation-Demurrage-280121.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Generation of Money Receipt through system (TMS/FOIS)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Guidelines has been issued that with effect from 15th February 2021,all Money Receipts should be generated through the system(TMS/FOIS) only and no manual Money Receipt should be issued")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Generation-Money-receipt.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
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
											  <p class="list-item-desc"><%=E.EH("in case of loading of Standard Bags of uniform size in container, Low density commodities like Pet Coke, Met Coke, Chuni and De-oiled cake.The random sample checks at 5% shall continue")%>.</p>
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
											  <p class="list-item-desc"> <%=E.EH("GMs have been asked to monitor the progress of the integration of all weighbridges with FOIS")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/MT%20(D_O)%20LETTER.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Power delegated to DRM for permitting installation of private weighbridges on railway land (private sidings as well as goods sheds)")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC%201%202020%20108%20NR_13082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Permission has been given for Electronic static weighbridge at six sidings of Bhillai Steel Plant over SECR")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Electronic%20Static%20Weighbridge%20at%20Sidings%20of%20Bhilai%20Steel%20Plant.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("To move in containers at CCR,was valid upto 20.10.2020.This permission has been extended till further advise")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corrigendum_39_RMC_CRT%20_CCR.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("implemented to provide an economical and reliable alternative for ultra short lead (upto 50Kms) container traffic which are moving at Haulage Charge per TEU rates, extended upto 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="/RailSAHAY/resources/pdf/HaulRoundTrip.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_30_CRT_CCR_060919.pdf" target="_blank"><%=E.EH("Corrigendum")%> (06.09.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"><%=E.EH("New delivery model of Double Stack Dwarf Container to be charged by applying 17% concession on Haulage Charge per TEU rate extended upto 31.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2017/Corrigendum_RC_22_2017.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.03.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("25% Concession on Empty Containers and Wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("An innovative business decision for movement of empty containers and empty flat wagon for private container rakes at a discount of 25%, valid upto 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/<%=E.EH("Corrigendum")%>_6_RC_20_2018.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.08.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_6_RC_20_2018.pdf" target="_blank"><%=E.EH("Corrigendum")%> (01.05.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("5% Concession on Loaded Containers")%></p>
											  <p class="list-item-desc"> <%=E.EH("Discount of 5% Haulage Charge per TEU rates on loaded containers from 04.08.2020 to 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Discount%20on%20loaded%20containers03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Round trip based charging")%></p>
											  <p class="list-item-desc"> <%=E.EH("implemented to provide an economical and reliable alternative for ultra short lead (upto 50Kms) container traffic which are moving at Haulage Charge per TEU rates, initially valid upto 14.09.2020. Container train operators are required to apply to concerned zonal railways to avail this scheme. This policy has extended upto 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Haulage%20Charge%20on%20Roundtrip%20basis%20For%20Transportation%20Of%20Container%20Traffic.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Stabling charges for container traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("are not being levied from 18.05.2020 to 31.03.2021 as a relief measure during difficult economic conditions prevailing currently. Stabling Charges are levied for occupation of railway lines by container wagons")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/No-Stabling-Charge023-11-20.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Loading of ‘Zinc Concentrate, Lead Concentrate, Soap Stone Powder’ in containers")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted by levying Haulage Charge at Container Class Rate i.e. by applying 15% concession on applicable class rate as published in IRCA Goods Tariff No.49 Pt.I Vol-II and as amended from time to time")%></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Guidelines for levy of Haulage Charge for movement of cube containers issued")%></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2021/Corri_43_RMC_CRT_CCR_070121.pdf" target="_blank"><%=E.EH("Corrigendum")%> (07.01.2021)..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("A concession of 50% has been granted on Terminal Access Charges applicable on Group-III CRTs")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corr_37_RMC_CRT_reg_TAC.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Benefit of night incentive at par with goods traffic extended to container traffic handled at CRT")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/TCR_Container_Rail_Terminal_Night.pdf" target="_blank"><%=E.EH("Corrigendum")%> (26.06.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Railways are empowered to permit advance Stacking permission at Group-III CRTs for 24 hrs free of charge and, thereafter, on levy of charge @ 20% of prevailing rate of Ground Usage Charge upto five days only. These guidelines are valid from 09.03.2020 upto 08.03.2021.Further, Zonal Railways have been empowered to permit advance stacking permission for more than five days period in case of underutilized Group-III CRTs")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_RMC_CRT_CCR_090320.pdf" target="_blank"><%=E.EH("Corrigendum")%> (09.03.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC-I_Corr_26102020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (26.10.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Liberalization in the guidelines for permitting lift on lift off at Group-I and II CRTs by removing the condition of seven rakes per handling line per month, valid upto 30.06.2021")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/Corri-35-RMC.pdf" target="_blank"><%=E.EH("Corrigendum")%> (09.12.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Stabling Charge on container traffic not to be levied on container traffic during 18.05.2020 to 31.10.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Stabling%20charge_03082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										</ul>
			                        </div>
			                    </div>
			                </div>			                <div class="panel panel-default">
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
			                <div class="panel panel-default">
			                    <div class="panel-heading" role="tab" id="headingSix">
			                        <h4 class="panel-title">
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOthr" aria-expanded="false" aria-controls="collapseOthr">
			                               <%=E.EH("OTHERS")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseOthr" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
			                        <div class="panel-body">
										<p class="list-item-desc"> <%=E.EH("Rates of Hire Charges on wagon interchanged for inter-zonal financial adjustments and for non-railway user notified")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/RC%2021_2020_wagon%20hire%20charges.pdf" target="_blank"><%=E.EH("View Detail")%> (31.08.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Classification of new commodity Viscose Staple Fibre notified w.e.f. 23.09.2020 valid up to 22.09.2021")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/RC_22_21092020.pdf" target="_blank"><%=E.EH("View Detail")%> (21.09.2020)</a></p>
									        <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corr_01_29092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (29.09.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Procedure laid down for correction in case GSTIN is not mentioned")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/GSTN%20correction.pdf" target="_blank"><%=E.EH("View Detail")%> (22.09.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Illustration given for calculating ODC charges")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/TCR_ODC_Ccharge_28092020.pdf" target="_blank"><%=E.EH("View Detail")%> (28.09.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("In case of iron ore traffic, guidelines have been issued for forfeiture of wagon registration fee if indent is cancelled after allotment of rake in Rake Allotment System")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Rates_Circular_25.pdf" target="_blank"><%=E.EH("View Detail")%> (08.10.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("In case of Military Traffic, the guidelines for coaching train and mixed train has been relaxed. The charge for coaching train shall be levied for actual number of coaches in the train subject to a minimum charge for 10 coaches (instead of 18 coaches) per train. This relaxation is applicable till 31.03.2021 and post which, the charge for coaching train shall be levied for actual number of coaches in the train subject to a minimum charge for 18 coaches per train. In case of mixed train (coaching + goods), the total charge should be worked out for actual number of coaches/wagons used subject to minimum charge for 30 bogie wagons (including a minimum of one coach). This relaxation will remain valid till further advice")%>.</p>
									        <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corrigendum%20to%20Rates%20Circular%20No%2032%20Of%202014.pdf" target="_blank"><%=E.EH("Corrigendum")%> (16.10.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Classification of ‘Bed Ash’ at Base Class-120A")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Rates%20Circular%20No%2028%20Of%202020.pdf" target="_blank"><%=E.EH("View Detail")%> (06.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Empty Haulage Charge for newly manufactured privately owned wagons levied on the basis of Designed Tare Weight of the wagon")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_29_20.pdf" target="_blank"><%=E.EH("View Detail")%> (11.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Rates for Automobile traffic when carried on NMG BCCNR, BCAM wagon were prescribed/extended beyond 30.11.2020 till 30.11.2021")%>.</p>
									        <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/CORRI1_RC_24_2019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (16.11.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Rates for Automobile traffic when carried on NMG BCCNR, BCAM wagon beyond 3500 km distance limit were fixed")%>.</p>
									        <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Rate_2019/corrig-2-RC-24-2019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (27.11.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Classification of ‘Pet Coke Cinder’ at base class-145A")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Classification-Pet-Cok-Cinder-231120.pdf" target="_blank"><%=E.EH("View Detail")%> (23.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Rebooking/Diversion of traffic permitted considering Punjab situation")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Diversion%20of%20traffic.pdf" target="_blank"><%=E.EH("View Detail")%> (10.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Automobile traffic does not qualify for concession under STS")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Clarification%20Regarding%20Concession%20On%20Automobile%20Traffic%20Under%20STS.pdf" target="_blank"><%=E.EH("View Detail")%> (11.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Permission granted for Diversion of Iron Ore traffic within port area")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/TCR_121120.pdf" target="_blank"><%=E.EH("View Detail")%> (12.11.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Premium indent has been implemented for benefit of freight customers in sidings and goods sheds on payment of 15% premium on base freight.The scheme is optional")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC-32-2020.pdf" target="_blank"><%=E.EH("View Detail")%> (11.12.2020)</a></p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Guidelines-Premium-25_1_2021.pdf" target="_blank"><%=E.EH("View Detail")%> (25.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Rates for Automobile Traffic - Illustration advised for correct charging of freight")%>.</p>
									        <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Rate_2019/Corrigendum-3-of-RC-24-2019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.12.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Rates for Automobile traffic when carried on BRN wagons were prescribed from 12.12.2020 till 31.01.2021")%>.</p>
									        <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/downloads/Freight_Rate_2019/Corri-4-RCNo24-2019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (10.12.2020)..</a></p>
										<p class="list-item-desc"> <%=E.EH("Classification of ‘Coriander Seeds’ at Base Class - LR-3")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC-33-1412020.pdf" target="_blank"><%=E.EH("View Detail")%> (14.12.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Methodology of calculation on concession under STS elaborated")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Methodology_Calculation_101220.pdf" target="_blank"><%=E.EH("View Detail")%> (10.12.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("FOIS was advised to treat SEZ invoices as Inter-state supplies")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/sez-invoices-151220.pdf" target="_blank"><%=E.EH("View Detail")%> (15.12.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("Commercial Protocol for DFCCIL have been prescribed")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/DFCCIL-2412.pdf" target="_blank"><%=E.EH("View Detail")%> (24.12.2020)</a></p>
										<p class="list-item-desc"> <%=E.EH("In principle approval granted for Ro-Ro service over one route of DFCCIL for one year")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/Ro-Ro-Service-150121.pdf" target="_blank"><%=E.EH("View Detail")%> (15.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Classification of ‘Talc Powder’. Trainload Base Class - 145 assigned")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight-Rate-Circular-2021/RC-01-2021.pdf" target="_blank"><%=E.EH("View Detail")%> (19.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Zonal Railways can extract information of NTKM on their own")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/sts-251-250121.pdf" target="_blank"><%=E.EH("View Detail")%> (25.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Designed Tare Weight of BCACBM wagon notified")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/15_01_21-newly-manufactured-wagons.pdf" target="_blank"><%=E.EH("View Detail")%> (15.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Clarification - Over Dimensional Consignments over Indian Railways are not over DFCCIL network")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/odc-dfccil-220121.pdf" target="_blank"><%=E.EH("View Detail")%> (22.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Methodology of calculation under STS clarified")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Iron Ore rejects classified at class 165")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Permission for inter-circuit loading of Adani owned BCBFG wagons granted")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Withdrawal of Congestion surcharge on Goods Traffic Originating from Ports extended")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Classification of Water for Industrial use in BTPN wagons over NWR at Base Class - 140 notified as special case")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Petroleum Coke segregated from Main Commodity Head Coal and Coke and made eligible for STS concession")%>.</p>
										<p class="list-item-desc"> <%=E.EH("RO-RO Services between New Palanpur - New Rewari stations over DFCCIL approved")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Approval was granted to execute agreement with M/s Adani Agri Logistic Ltd")%>.</p>
										<p class="list-item-desc"> <%=E.EH("Designed Tare Weight of BOXNHL25T wagon notified")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/15_01_21-newly-manufactured-wagons.pdf" target="_blank"><%=E.EH("View Detail")%> (15.01.2021)</a></p>
										<p class="list-item-desc"> <%=E.EH("Revision in Wagon Registration Fee in case of Iron Ore/Pellet-With the implementation of Programming of Iron Ore traffic, the Wagon Registration Fee in case of Iron Ore/Pellet has been revised to Rs.2 lakh per rake w.e.f. 20.03.2021")%>.</p>
										<p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2021/odc-dfccil-220121.pdf" target="_blank"><%=E.EH("View Detail")%> (22.01.2021)</a></p>

			                        </div>
			                    </div>
			                </div>
			    </div>
</div>
          </div>		<div class="col-lg-6 col-sm-12">
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
