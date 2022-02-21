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
			                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
			                                <%=E.EH("Freight Incentive Schemes")%>
			                            </a>
			                        </h4>
			                    </div>
			                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
			                        <div class="panel-body">
			                            <ul class="list-group list-group-flush">
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Automatic Freight Rebate Scheme for traffic loaded in Traditional Empty Flow Directions (TEFD)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Railway identifies the routes where it operates a certain number of empty wagons annually. In a endevour to convert these empty movements to loaded, it offers discounted freight in these empty flow routes; i.e. at Class LR1/Class 100/Class 110 for train load/wagon load respectively).  Discount is granted automatically through system on Railway Receipt itself, that is, no action is required to be taken by customer. Details of notified routes and Terms & conditions are as per the circular")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_08_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrig_1_RC_8_20.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Long Term Traffic Contract (LTTC)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Provides freight stability to its customer in the current year of the agreement alongwith the rebate in freight based on incremental growth in Gross Freight Revenue GFR as well as for retention of traffic volumes.Rebate is also available for retention of GFR in each year of the agreement period.Customers are required to apply for and sign contracts with Zonal Railways to avail this scheme")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2017/RC_14_2017_30032017.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Busy Season Charge")%></p>
											  <p class="list-item-desc"> <%=E.EH("which was levied during Oct-June (with some exceptions) @15% on all goods traffic has been withdrawn from 01.10.2019 for all traffic; except Iron Ore and POL traffic. This has provided a direct huge reduction in freight charges")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/<%=E.EH("Corrigendum")%>1_DPP_120919.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Round Trip Tariff (RTT)")%></p>
											  <p class="list-item-desc"> <%=E.EH("Policy has been implemented from 01.07.2020. Under this policy,  freight discount is granted to traffic if customer offers to book traffic in onwards as well as return direction.  The return traffic under this traffic is charged at lower of the two classes of onward vis-a-vis return traffic. Effectively both onwards and return traffic/commodities are charged at lowest class of the two, subject to terms and conditions applicable")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_11_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive to fly ash")%></p>
											  <p class="list-item-desc"> <%=E.EH("40% discount in freight is granted to fly ash traffic booked in bagged condition or in bulk/loose condition when loaded in Open Stock. If it is loaded in bagged condition, then flat wagons are also granted 40% concession; and Covered wagons in bagged condition are charged at Class LR1")%>.</p>
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
											  <p class="list-item-desc"> <%=E.EH("Has been introduced from 01.07.2020 for Coal & Coke, Iron ore and Iron & Steel traffic under which discount in freight is granted; 20% to Coal & Coke for distance >1400KM, 20% to Iron & Steel for distance >1600KM; and 15% for distance >700KM and 20% for distance >1500 Km for Iron ore traffic.Details may be seen in circular attached")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Rates%20circular%2015_30062020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <%--
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Free time relaxation for covered wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Zonal Railways are empowered to relax the free time upto double of normal free time and/or non levy of demurrage/wharfage in case of covered stock during lean season i.e. upto 30.09.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Relaxation%20in%20free%20time%20for%20loading-%20unloading%20of%20covered%20wagons.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  --%>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Permission to accept road weighbridge weighment")%></p>
											  <p class="list-item-desc"> <%=E.EH("Weighment done at specific Non-Railway (road) weighbridges is being accepted for granite at certain good sheds of South Central Railway on trial basis")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Approva1_310720.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Terminal Access Charge Concession @50%")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been granted on container traffic handled at Group-III CRTs.(Container Rail Terminals). It may be noted that some Railway Goods-shed are notified as CRTs, which Container traffic may use on payment of TAC. Group-III terminals are those that handle less than seven rakes a month.These are categorised by concerned Zonal Railway")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Corr_37_RMC_CRT_reg_TAC.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("5% concession on loaded containers")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been permitted with effect from 04.08.2020 to 30.04.2021. Haulage charges for containers are levied at different rates for loaded and empty conatiners and empty wagons. These also vary for different weigh slabs in case of loaded conatiners")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Discount%20on%20loaded%20containers03082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Stabling charges for container traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("Are not being levied from 18.05.2020 to 31.10.2020 as a relief measure during difficult economic conditions prevailing currently. Stabling charges are levied for occupation of Railway lines by container wagons")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Stabling%20charge_03082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>                                                         
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("25% concession on empty containers and wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("Has been given till 30.04.2021. Haulage charges for containers are levied at different rates for loaded and empty containers and empty wagons. The current rates are prescribed in Rates circular no. 20 of 2018")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_6_RC_20_2018.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>                                                         
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Non-levy of Terminal charges at unloading point")%></p>
											  <p class="list-item-desc"> <%=E.EH("For the traffic booked to and from notified alternate goods shed instead of busy goods shed. Terminal charge is otherwise levied at Rs.20 per tonne for goods sheds and PFTs.This intends to offer efficient and faster handling of goods at less busy terminals for benefit of customers. This will also help Indian Railway in better utilisation of its terminal infrastructure")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC-14_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>                                                         
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Permissible Carrying Capacity")%></p>
											  <p class="list-item-desc"> <%=E.EH("For loading of Pet Coke in Open wagons have been reduced, thereby, reducing the chargeable tonnage per wagon.This has been done with a view to make pet coke freight more viable by eliminating the payment of dead freight by customer, as petcoke is high volume-low weight commodity")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corrigendum%20no_2%20to%20Rates%20Master%20Circular%20PCC.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.06.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/PCC-Pet%20Coke.pdf" target="_blank"><%=E.EH("Corrigendum")%> (03.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Incentive Scheme for loading of Bagged consignment in open and flat wagons")%></p>
											  <p class="list-item-desc"> <%=E.EH("This scheme endeavours to utilise open and flat wagons for loading of commodities that otherwise favour covered wagons. Freight rebate of 20% on cement, china clay, chemical manure, food grain etc.; and rebate of 30% on fly ash, urea is offered. Further, as indutry demanded for permitting large size bags so that it could result in cost savings to customers, same has been permitted upto 2.5 tonnes per bag for open wagons (only)")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/RC-27-2016.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2016/Corri_3_RC_27_2016.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>                                                         
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Rates for NMG rakes have been notified for movement to Bangladesh Railway")%></p>
											  <p class="list-item-desc"> <%=E.EH("Rates for NMG rakes have been notified for movement to Bangladesh Railway")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/allowing%20NMG%20coaches%20to%20B-desh.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>                                                         
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("25% concession extended")%></p>
											  <p class="list-item-desc"> <%=E.EH("With retrospective effect i.e. from 30.04.2020 from new loading point Lingraj SILO siding of MCL (LSST) for movement of NTPC’s coal traffic over East Coast Railway. This is a special case of MGR (Merry Go Round) operations systems for transportation of coal")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/25%20percent%20concession%20MGR%20type%20ECoR.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Rebate in freight for commodities loaded in privately owned wagons notified for the period from 01.10.2020 to 30.09.2021. These are the private wagons procured before currently prevalent wagon investment schemes")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_24_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Debit/Credit system of Demurrage has been permitted in case of private sidings, upto 30.09.2021. Demurrage is a charge levied for detention of wagons beyond permissible free time. It is levied on each case of loading and unloading separately. However, now this policy initiative permits demurrage for private sidings to be charged on monthly basis,and to debit-credit or to adjust the time saved in one wagon against other, subject to laid down terms and conditions")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Debit%20Credit%20Hours%20In%20Case%20Of%20Private%20Sidings.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Free time for loading/unloading of wagons has been increased in case of bagged consignment in open wagons at EOL terminals from three hours to six hours, upto 31.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC_I_Corrigendum_12%20to%20RMC%20Demurrage.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Penalty for overloading : Detention Charge levied at @5000/- per overloaded wagon has been withdrwan.  Punitive Charge rules have been revised such that on detection of overloading at other than originating point, if customer carries out load adjustment at any point, then punitive charge will be levied for the distance travelled by the train from originating station to the load adjustment point, rather upto destination point as was the previous policy")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum%20No_2%20to%20Rates%20Madter%20Circular%20weighment20190.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("PCC and loading tolerance of BCFC and BCFCM wagons notified. Since these wagons are loaded using precision loading mechanism using controlled discharge techniques, therefore loading tolerance for these have been reduced. This results in higher Pemrissible Carrying Capacity (PCC). It benefits both customer and Railways by increasing the throughput. These wagons are largely used to carry cement, fly ash etc")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum%20No_6_17092020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("has been reduced from 57 wagons to 42 wagons, extended till 31.12.2020")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_31_RMC_1Mini_Rake_190520.pdf" target="_blank"><%=E.EH("Corrigendum")%> (19.05.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="/RailSAHAY/resources/pdf/MinWgonBCNHL.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Supplementary charge")%></p>
											  <p class="list-item-desc"> <%=E.EH("Which was levied @5% on Mini Rake and Two point rake has been withdrawn from 01.10.2019.That is, now the customer can book mini rake at trainload rates, and does not have to pay supplementary charges")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_22_RMC_MiniRake_1209190001.pdf" target="_blank"><%=E.EH("Corrigendum")%> (12.09.2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/Corrigendum_24_RMC_Mini_rake_200919.pdf" target="_blank"><%=E.EH("Corrigendum")%> (20.09.2019)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Distance for operation of Mini Rake (20 wagons)")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been increased from 600 km to 1500 km. Mini rakes have also been permitted beyond 1500  km up to 2000 km and beyond 2000 km on payment of supplementary charge at 7.5% and 10% respectively. This charge is levied on graded manner, i.e. only for the relevant distance. This is valid up to 31.12.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/corr%2004%20to%20RMC%20block%20rakeetc2020%20Aug%2028%2C%202020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (28.08.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/mini%20rake%20movement.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("The distance between two point/multi point")%></p>
											  <p class="list-item-desc"> <%=E.EH("rake operation has been increased to 500 KM.The validity of this policy is up to 31.12.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_30__Block%20Rakes%2C%20Mini%20Rake%2C%20Two%20point%20combination_150420.pdf" target="_blank"><%=E.EH("Corrigendum")%> (15.04.2020)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/mini%20rake%20movement.pdf" target="_blank"><%=E.EH("Corrigendum")%> (30.09.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two-point booking of automobile traffic")%></p>
											  <p class="list-item-desc"> <%=E.EH("has been permitted in NMG, BCCNR and BCABM wagons")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Mini rakes allowed to be loaded in covered wagon for distances beyond 1500 km")%></p>
											  <p class="list-item-desc"> <%=E.EH("Upto 2000km with supplementary charge of 7.5% and further beyond 2000 km with 10% supplementary charge")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/corr%2004%20to%20RMC%20block%20rakeetc2020%20Aug%2028%2C%202020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (28.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Two point combination for other than covered stock for steel traffic notified")%></p>
											  <p class="list-item-desc"> <%=E.EH("23 pairs of two point combination for other than covered stock for steel traffic notified")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corr_2%20today.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Automatic Freight Rebate Scheme for traffic loaded in Traditional Empty Flow Directions (TEFD)")%></p>
											  <p class="list-item-desc"> <%=E.EH("The scheme lays down minimum number of wagons in a rake that are eligible for availing this schme. Recently, this number has been reduced to 10 wagons for BCN and BCNHL rakes. BCN and BCNHL are covered wagons usually used for transportation of bagged commodities like food grains, sugar, cement etc")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/corr_2%20today.pdf" target="_blank"><%=E.EH("Corrigendum")%> (25.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Running of freight trains over seven CC+8 routes")%></p>
											  <p class="list-item-desc"> <%=E.EH("are included over Northern Railway.Higher pay load helps Railways as well as customers with better stock utilization and higher throughput")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_CC%20plus%208%20NR.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.08.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("NMG rakes allowed with two point unloading destinations")%></p>
											  <p class="list-item-desc"> <%=E.EH("NMG rakes allowed with two point unloading destinations for automobile traffic")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/TCR_Multiple%20point_05082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Standard Rake Size and Minimum number of wagon to be loaded in case of BAFRDR wagons validity extended up to 31.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/05_bafrdr_14092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
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
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/MR_TMS_FOIS_29112019.pdf" target="_blank"><%=E.EH("View Detail")%> (2019)..</a></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/Generation%20of%20Money%20Receipt%20(MR)%20through%20system%20(TMS-FOIS).pdf" target="_blank"><%=E.EH("View Detail")%> (2020)..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("Offers Tariff certainty in lieu of freight advance. Under this scheme, Railway shall accord priority in allocation of rakes within same class to freight customers who have signed agreement. The scheme is open to customers who are e-payment customers for freight payment and have given minimum annual freight revenue of Rs 500 crore as freightpayee in previous calendar year")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Guidelines%20regarding%20Freight%20Advance%20Scheme%20dt_27_01_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Issue of Money Receipt in the name of handling agent of the freight customer has been permitted")%></p>
											  <p class="list-item-desc"> <%=E.EH("Handling agents were already recognised as freight paying entity under e-payment agreement. It has now been taken into cognizance that handling agents in certain cases are appointed by customer for payment of demurrage, wharfage and other charges. Now under GST regime, it is important to issue money receipt in favour of handling agents, so as to ensure proper implementation of GST and also so that handling agents are able to avail Input Tax credit as due")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/RC_23_2020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("On detection of overloading in a rake, if load adjustment is not feasible at originating point, then it may be permitted at nearby location and transit time from weighment point to load adjustment point for levy of Detention Charge has been capped to maximum of two hours plus actual duration of load adjustment.Prior to this modification, the detention charge was levied for actual time taken for rake to transit from overloading detection point to load adjustment point")%>. </p>
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
											  <p class="list-item-head"><%=E.EH("Exemption from mandatory 100% weighment")%></p>
											  <p class="list-item-desc"><%=E.EH("in case of loading of Standard Bags of uniform size in container, Low density commodities like Pet Coke, Met Coke, Chuni and De-oiled cake.The ransom sample checks at 5% shall continue")%>.</p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2019/RMC_Weighment_250719.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-head"><%=E.EH("Exemption from mandatory 100% weighment")%></p>
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
											  <p class="list-item-desc"> <%=E.EH("Integration of all weighbridges with FOIS is being taken up on urgent basis. All General Managers of Zonal Railways have been asked to monitor the progress and ensure implementation")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/MT%20(D_O)%20LETTER.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Power delegated to DRM for permitting installation of private weighbridges on railway land (private sidings as well as goods sheds)")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/TC%201%202020%20108%20NR_13082020.pdf" target="_blank"><%=E.EH("Corrigendum")%>..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("To promote containerizations following measures have been taken, ninety additional commodities have been de-notified and brought under Haulage Charge per TEU rates. Out of 641 commodities notified in goods tariff, 507 commodities are allowed to move in containers at FAK and 38 commodities at Container Class Rate (by applying 15% concession on applicable class rate).It may be noted that CCR rate is one that is arrived at by reducing the normal freight by 15%. FAK rate is even lower than CCR by 15-20%, though a direct comaprision is neither possible nor logical")%></p>
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
											  <p class="list-item-desc"> <%=E.EH("implemented to provide an economical and reliable alternative for ultra short lead (upto 50Kms) container traffic which are moving at Haulage Charge per TEU rates, valid upto 14.09.2020. Conatiner Train Operators are required to apply to concerned Zonal Railways to avail this scheme.Extension of the policy regarding Haulage Charge on Roundtrip basis in case of container traffic upto 30.04.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="/RailSAHAY/resources/pdf/HaulRoundTrip.pdf" target="_blank"><%=E.EH("Corrigendum")%> (14.09.2020)..</a></p>
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
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2019/TC_08072019.pdf" target="_blank"><%=E.EH("Corrigendum")%> (08.07.2019)..</a></p>
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
											  <p class="list-item-desc"> <%=E.EH("Railways are empowered to permit advance Stacking permission at Group-III CRTs for 24 hrs free of charge and, thereafter, on levy of charge @ 20% of prevailing rate of Ground Usage Charge upto five days only. These guidelines are valid from 09.03.2020 upto 08.03.2021")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum_RMC_CRT_CCR_090320.pdf" target="_blank"><%=E.EH("Corrigendum")%> (09.03.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Liberalization in the guidelines for permitting lift on lift off at Group-I and II CRTs by removing the condition of seven rakes per handling line per month, valid upto 17.12.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corr%2035%20to%20RMC_CRT%20reg%20LOLO.pdf" target="_blank"><%=E.EH("Corrigendum")%> (17.06.2020)..</a></p>
										  </li>
										  <li class="list-group-item">
											  <p class="list-item-desc"> <%=E.EH("Stabling Charge on container traffic not to be levied on container traffic during 18.05.2020 to 31.10.2020")%></p>
											  <p class="list-item-link"><a class="card-link1" href="https://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Freight_Rate_2020/Stabling%20charge_03082020.pdf" target="_blank"><%=E.EH("View Detail")%>..</a></p>
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
										<p class="list-item-desc"> <%=E.EH("Exemption from levy of ancillary charges namely Demurrage, Wharfage, Stacking, Stabling, Detention and Ground Usage charges since 22.03.2020 upto 17.05.2020. Post which, these charges were levied with relaxation by doubling the applicable free time for loading/unloading up to 31.05.2020. Further, Railways continue to exercise their powers for waiver of these charges as per prevailing local conditions. In order to provide the relief to customers loading goods traffic in case of covered stock, Railways are empowered to relax the free time upto double of normal free time and/or non levy of demurrage/wharfage during lean season i.e. upto 30.09.2020")%></p>
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
			                                             <ul class="list-group list-group-flush">
									<li class="list-group-item">	
										<p class="list-item-desc"> <%=E.EH("Rates of Hire Charges on wagon interchanged for inter-zonal financial adjustments and for non-railway user notified")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/RC%2021_2020_wagon%20hire%20charges.pdf" target="_blank"><%=E.EH("View Detail")%> (31.08.2020)</a></p>
									</li>
									<li class="list-group-item">	
										<p class="list-item-desc"> <%=E.EH("Classification of new commodity Viscose Staple Fibre notified w.e.f. 23.09.2020 valid up to 22.09.2021")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/RC_22_21092020.pdf" target="_blank"><%=E.EH("View Detail")%> (21.09.2020)</a></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corr_01_29092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (29.09.2020)</a></p>
									</li>
									<li class="list-group-item">	
										<p class="list-item-desc"> <%=E.EH("Loading Tolerance of BCFC and BCFCM wagons reduced from 1 ton to 0.5 ton. PCC increased")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates_Master_Circulars/2020/Corrigendum%20No_6_17092020.pdf" target="_blank"><%=E.EH("Corrigendum")%> (17.09.2020)</a></p>
									</li>
									<li class="list-group-item">	
										<p class="list-item-desc"> <%=E.EH("Procedure for correction in case GSTIN is not mentioned ")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/GSTN%20correction.pdf" target="_blank"><%=E.EH("View Detail")%> (22.09.2020)</a></p>
									</li>
									<li class="list-group-item">	
										<p class="list-item-desc"> <%=E.EH("Illustration given for calculating ODC charges")%></p>
										<p class="list-item-link"><a class="card-link1" href="http://indianrailways.gov.in/railwayboard/uploads/directorate/traffic_comm/Rates-Letters/2020/TCR_ODC_Ccharge_28092020.pdf" target="_blank"><%=E.EH("View Detail")%> (28.09.2020)</a></p>
									</li>
								     </ul>
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
		<input type="hidden" name="toPage1" id="toPage1" value="Policy" />
<%@ include file="/pages/GG_Footer.jspf" %>
