<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ include file="/pages/GG_Header.jspf" %>
<script src="/RailSAHAY/resources/js/sahaystats.js"></script>
<% 
   strLastUpdate=Logg.setPortal(si_UserId,si_ClntId,si_Theme,si_LangFlag,"Eligibility for GCT Operators (GCTO)","/RailSAHAY/pages/GCTElgb.jsp",UserDevice,Browser);
DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
Date dateobj = new Date();
String strCrntDate=df.format(dateobj);
%>
  	  <nav class="navbar navbar-expand-sm bg-origprimary navbar-dark" style="margin-top:12px;">
	</nav>
	<div class="container-fullwidth">
        <div class="row align-items-center justify-content-center text-center">
          <div class="col-lg-12">

            <div class="box-shadow-content">
              <div class="block-heading-1" style="margin-top:15px;">
                <h3 class="mb-4" data-aos="fade-up" data-aos-delay="100"><%=E.EH("Eligibility for GCT Operators (GCTO) ")%></h3>
              </div>
            </div>
           </div>
          </div>
 <div class="row">
          <div class="row">
	<div class="col-lg-12 col-sm-12">	
	<div class="card mr-3 ml-3">
	  <div class="card-header head-faq">
	    <%=E.EH("The categories of applicants permitted to set-up GCTs, as GCTO, shall be as under:")%>
	  </div>
	  <div class="card-body">
              <div class="container">
                <div class="row">                
                <div class="col table-responsive">
                            <table id="tblSchm" class="display table table-striped table-bordered tabformat table-sm">
                            <thead>
                             <tr>
                             <th class="align-middle" class="w-2"> Category</th>
                             <th tyle="text-align:center;" class="w-28">Supporting Documents(self-attested copies to be submitted/uploaded)</th>
                             </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>Individual/sole Proprietorship Firm</td>
                                    <td>PAN and TAN</td>
                                </tr>
                                <tr>
                                    <td>Hindu Undivided Family HUF)</td>
                                    <td>
                                        <ul>
                                            <li>PAN</li>
                                            <li>Notarized Affidavit declaring that the signatory is the &apos;Karta&apos; of the HUF and has the authority, power and consent of other members</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Partnership Firm</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Notarized copy of the Partnership Deed/Partnership Deed registered with the Registrar</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of the individual to sign on behalf of, and to create liability against the firm</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Company registered under Companies Act 2013</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Memorandum of Association (MoA)/Articles of Association AoA)</li>
                                            <li>Certificate of Incorporation</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the company</li>
                                            <li>Resolution of the Directors of the Company, permitting the company to setup and/or operate a GCT</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Limited Liability Partnership (LLP)</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>LLP Agreement</li>
                                            <li>Certificate of Incorporation</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the partnership</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Registered Society/Registered Trust</td>
                                    <td>
                                        <ul>
                                            <li>PAN and TAN</li>
                                            <li>Certificate of Registration</li>
                                            <li>Memorandum of Association of Society/Trust Deed</li>
                                            <li>Rules & Regulations of the Society</li>
                                            <li>Notarized or registered copy of Power of Attorney in favour of individual to sign on behalf of, and to create liability against the Society/Trust</li>
                                        </ul>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Joint venture (JV) / Consortium</td>
                                    <td>
                                        <ul>
                                            <li>JV/Consortium Agreement</li>
                                            <li>Name and authorization of lead member</li>
                                        </ul>
                                        (Note. On receipt of approval of Railway Administration for setting- up and/or operation of GCT, JV/Consortium shall have to convert into a legal entity &minus; Firm/ Company/ LLP. Agreement shall be entered into by RA with the above legal entity only.)
                                    </td>
                                </tr>
                            </tbody>
                </table>
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
