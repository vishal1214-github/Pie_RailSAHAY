    var ResArr=[];
    $(document).ready(function(){
		
 		var cmdtstr='';
   		for (var i=0; i < aRakeCmdt.length;++i)
		{
        		cmdtstr+= '<option value="'+aRakeCmdt[i]+'" />'; // Helplist for Rake Commodity
   		}
   		$("#cmdtlist").html(cmdtstr);

  });
    function checkKeyEntry(e) {
         var k = e.keyCode;
         return ((k > 64 && k < 94) ||  (k > 96 && k < 123)|| (k > 39 && k < 47) || (k == 8)|| (k == 16)|| (k == 20) || (k == 219)|| (k == 221)||(k==32)|| (k==13)|| (k==61)  || (k >= 48 && k <= 57));
    }
    function addRecord()
    {
	$("#resModal").modal('show');

	$('#resModal').on('hidden.bs.modal', function () {		    
	    $(".table-input>tr").removeClass("selected-row");
	    $("tr").removeClass("selected-row");
	});
    }
    function fetchResourceList()
    {
		var zone=$("#txtZone").val();
		var dvsn=$("#txtDvsn").val();
		var myurl="/RailSAHAY/AcqnPlanTrxn";
		$("#dtlsGrid").html('<div class="spinner-border text-danger"></div>');
		var htmlstr='<table class="table table-input table-striped table-bordered"><thead><tr><th rowspan="2">SR.No.</th><th colspan="9">Resource Detail</th><th colspan="2">Updation</th><th rowspan="2">&nbsp;</th></tr><tr><th>Zone</th><th>Division</th><th>Commodity</th><th>Industry</th><th>Name</th><th>Contact Mobile</th><th>Contact Landline</th><th>Email Id</th><th>Address</th><th>By</th><th>Date</th></tr></thead><tbody>';

		$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'RESOURCE_CENTRE',Ctgr:'RESOURCE_LIST',Zone:zone,Dvsn:dvsn},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			for(var i=0;i<obj.DataList1.length;i++)
			{
				ResArr[i]=new Array(10)
				htmlstr+='<tr><td class="text-center">'+(i+1)+'</td><td>'+obj.DataList1[i].Zone+'</td><td>'+obj.DataList1[i].Dvsn+'</td><td>'+obj.DataList1[i].Cmdt+'</td><td>'+obj.DataList1[i].Inds+'</td><td>'+obj.DataList1[i].Name+'</td><td>'+obj.DataList1[i].Mobl+'</td><td>'+obj.DataList1[i].Landline+'</td><td>'+obj.DataList1[i].Email+'</td><td>'+obj.DataList1[i].Addr+'</td><td>'+obj.DataList1[i].UserId+'</td><td>'+obj.DataList1[i].UpdtTime+'</td><td><div class="btn-group btn-group-sm"><button type="button" class="btn btn-light btn-edit" onclick="editResource(this,\''+obj.DataList1[i].ResId+'\');"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></button><button class="btn btn-light btn-sm btnicon" onclick="delResource(this,\''+obj.DataList1[i].ResId+'\');"><i class="fa fa-trash-o" aria-hidden="true"></i></button></div></td></tr>';
				ResArr[i][0]=obj.DataList1[i].Zone;
				ResArr[i][1]=obj.DataList1[i].Dvsn;
				ResArr[i][2]=obj.DataList1[i].Cmdt;
				ResArr[i][3]=obj.DataList1[i].Inds;
				ResArr[i][4]=obj.DataList1[i].Name;
				ResArr[i][5]=obj.DataList1[i].Mobl;
				ResArr[i][6]=obj.DataList1[i].Landline;
				ResArr[i][7]=obj.DataList1[i].Email;
				ResArr[i][8]=obj.DataList1[i].Addr;
				ResArr[i][9]=obj.DataList1[i].ResId;

			}
			htmlstr+='</tbody>';
			htmlstr+='<tfoot><tr><td colspan="12"><a class="btn-add-row shtw" onclick="addRecord();"><i class="fa fa-plus"></i></a></tfoot>';
			htmlstr+='</table>';

			$("#dtlsGrid").html(htmlstr);
			$(".table-data").dataTable();
		}
		});
    	}
	function delResource(delitem,resid)
	{
			var zone=$("#selZone").val();
			$(delitem).closest('tr').addClass("selected-row");
			if (confirm('The selected resource detail will get deleted! You want to continue'))
			{
				var myurl="/RailSAHAY/AcqnPlanTrxn";
				$.ajax({
				url : myurl,
				type : "post",
				data: {Optn:'RESOURCE_CENTRE',Ctgr:'DEL_RESOURCE',ResId:resid},
				async : true,
				success : function(data) {
					var obj= JSON.parse(data);
					var stts=obj.Stts;
					if(stts=="SUCCESS")
					{
					   successToast("Resource Detail Deleted Successfully !");
					   fetchResourceList();
					}
					else
					{
						failedToast("Failed to Delete! "+obj.ErorMesg);
						$(delitem).closest('tr').removeClass("selected-row");
					}
				}
			});
		}
		else
		{
			$(delitem).closest('tr').removeClass("selected-row");
		}
    }

	function editResource(edititem,resid)
	{
		addRecord();
		var indx=0;
		for(var i=0;i<ResArr.length;i++)
		{
			if(ResArr[i][9]==resid)
			{
				indx=i;
				break;
			}
		}
		$("#txtResId").val(resid);
		$("#txtCmdt").val(ResArr[indx][2]);
		$("#txtInds").val(ResArr[indx][3]);
		$("#txtName").val(ResArr[indx][4]);
		$("#txtMobl").val(ResArr[indx][5]);
		$("#txtLandline").val(ResArr[indx][6]);
		$("#txtEmail").val(ResArr[indx][7]);
		$("#txtAddr").val(ResArr[indx][8]);
		$(edititem).closest('tr').addClass("selected-row");
	}
	function addResourceDtls()
	{
		var zone=$("#txtZone").val();
		var dvsn=$("#txtDvsn").val();
		var cmdt=refineValue($("#txtCmdt").val(),'CM');
		var inds=$("#txtInds").val();
		var name=$("#txtName").val();
		var mobl=$("#txtMobl").val();
		var landline=$("#txtLandline").val();
		var email=$("#txtEmail").val();
		var addr=$("#txtAddr").val();
		var resid=$("#txtResId").val();
		var myurl="/RailSAHAY/AcqnPlanTrxn";
		$.ajax({
		url : myurl,
		type : "post",
		data: {Optn:'RESOURCE_CENTRE',Ctgr:'ADD_RESOURCE',Zone:zone,Dvsn:dvsn,Cmdt:cmdt,Inds:inds,Name:name,Mobl:mobl,LandLine:landline,Email:email,Addr:addr,ResId:resid},
		async : true,
		success : function(data) {
			var obj= JSON.parse(data);
			var stts=obj.Stts;
			if(stts=="SUCCESS")
			{
			   successToast("Resource Detail Added Successfully !");
			   fetchResourceList();
			}
			else
			{
				failedToast("Failed to Add Resource Detail! "+obj.ErorMesg);
			}
		}
		});
	}