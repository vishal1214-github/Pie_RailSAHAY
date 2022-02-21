function fetchTrmlFcty(trml, cntlid)
{
	var myurl="/RailSAHAY/GG_AjaxUtil?Qry=TRML_FCTY_SMRY_ONLY&Sttn="+trml;
	var htmlDtls;
	var factype="";
	var agcont=0;
	var trckcont=0;
	var whcont =0;
	var labcont=0;
	$.ajax({
	  url : myurl,
	  type : "post",
	  async : true,
	  success : function(data) {
	  	var obj= JSON.parse(data);
	  	var srvccall=obj.ServiceCall;
	  	if(srvccall=="S")
	  	{
                                
	  		var dataAvbl=obj.DataFlag;
	  		if(dataAvbl=="Y")
	  		{
	  			htmlDtls='<table class="table-borderless"><tr><td><span class="label label-primary"><i class="fas fa-store-alt fa-sm"></i></span>&nbsp;&nbsp;<span class="smryval">';
				htmlDtls+=obj.AggrCont;
				htmlDtls+='</span></td><td><span class="label label-success"><i class="fas fa-truck fa-sm" ></i></span>&nbsp;&nbsp;<span class="smryval">';
				htmlDtls+=obj.TrckCont;
				htmlDtls+='</span></td><td><span class="label label-danger"><i class="fas fa-warehouse fa-sm" ></i></span>&nbsp;&nbsp;<span class="smryval">';
				htmlDtls+=obj.WHCont;
				htmlDtls+='</span></td><td><span class="label label-warning"><i class="fas fa-people-carry fa-sm" ></i></span>&nbsp;&nbsp;<span class="smryval">';
				htmlDtls+=obj.LbrCont;
				htmlDtls+='</span></td></tr></table>';				
	  			$("#"+cntlid).html(htmlDtls);
	  		}
	  	}
	  }
	  });
}
function showFctyDtls(sttn)
{
 		$(".headtrml").html(sttn);
 		$("#txtSttn").val(sttn);
 		$("#txtSttnName").val(sttn);
 		$("#FctyModal").css("display","block");
 		$( "#frmFcty" ).submit();
}
function closeModal()
{
		$("#FctyModal").css("display","none");
}