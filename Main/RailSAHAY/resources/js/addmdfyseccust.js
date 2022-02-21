  function getDataAMScCust()
{
        //alert("in getdata");
        var custcode = '';
        var cust = '';
        var pcust='';
        /*if($("#txtCustCode").val() == "")*/
       // var custOrgn =$("#custList").val();
       // alert("11");
       /*var custOrgn1=$("#custList1").html();
        if((custOrgn!='') && (custOrgn!=undefined) && (custOrgn!=null))
            cust=custOrgn;
        else
            cust=custOrgn1; */
           // cust=$("txtScCustCode").html();
           cust=document.getElementById("txtScCustCode").value;
           pcust=document.getElementById("txtCustCode_AM").value;
           // alert("cust:"+cust+":");
       if(cust=='')
       {
                cuteToast({
                  type: "info",
                  message: "Specify Secondary Customer Organization",
                  timer: 5000
                });    
                return;
        }
        //cust=$("#txtScCustCode").val();
        //var custcode = '';
        var emailid=$("#txtEmailId").val();
        var moblnumb=$("#txtMoblNumb").val();
        var station = '';
        if(cust=="" && emailid=="" && moblnumb=="")
        {            
                 cuteToast({
                  type: "info",
                  message: "Specify filters to select Secondary Customer Organization",
                  timer: 5000
                })          
        }
        else
        {
            if(cust.indexOf("(")>-1)
            {
                    //var c=cust.substring(cust.indexOf("-")+1);
                    custcode=(cust.substring(1,cust.indexOf(")"))).trim();
            }	
            else
            {
                    custcode=cust.toUpperCase();
            }
            fetchData("DATA",moblnumb, emailid, station, custcode, pcust) ;
        }    
}   
function addMdfyScCst_New(){
 //alert("in addMdfyScCst_New.....");
        GG_CrntView="SeccustAdMf";
	$("#divScndCust").slideUp();
        $("#dataD").hide();
            $("#frmSr").hide();
            $("#frmCncrn").hide();
           // alert("0");
            $("#trmlProf").hide();
            $("#divTrmlProf").html("");
            $("#NodlOfcr").hide();
            $("#divFrgtPymt").hide();
           // alert("show1.....");
           $("#divScndCust").show();
           // $("#divAdMfScndCust").show();
           //alert("pop");
           //alert("show.....");
           $("#divScndCustNew").show();
            $( "#Schd").hide();
            //document.getElementById("divScndCustNew").scrollIntoView() ;
            fetchOrg('ORG' ,'', '', '', '');
            fetchOrg('GORG' ,'', '', '', '');
           // alert("show.....");
           // document.getElementById("txtCustCode_AM").focus();
          // $("#divAdMfScndCust").hide();
}