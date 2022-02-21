<%
    String strFileRfrnId="";
    String strApltType="";
    String strFileUploadStts="";
    String strErorMesg="";
    try
    {
       strFileRfrnId=(String)request.getParameter("FileRfrnId").trim(); 
       strApltType=(String)request.getParameter("ApltCtgr").trim();
    }
    catch(Exception e)
    {
        /*strFileRfrnId="GCT-HUF-08012022214600";
        strApltType="HUF";*/
        strFileRfrnId="";
        strApltType="";
    }
    /*  
    if(strFileRfrnId.equals(""))
    {
        try
        {
            strFileRfrnId=((String)session.getAttribute("FileRfrnId")).trim();
        }
        catch(Exception e)
        {
            strFileRfrnId="";
        }
        
    }
    */
    try
    {
        strFileUploadStts=((String)request.getParameter("Stts")).trim();
        strErorMesg=((String)request.getParameter("ErorMesg")).trim();
    }
    catch(Exception e)
    {
        strFileUploadStts="";
        strErorMesg="";
    }
    System.out.println("strFileRfrnId:"+strFileRfrnId+",  strApltType:"+strApltType);
    System.out.println("strFileUploadStts:"+strFileUploadStts+", strErorMesg:"+strErorMesg);
%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        
<!-- Required Jquery -->
<link rel="stylesheet" href="/RailSAHAY/resources/css/sticky-alert.css">
<link rel="stylesheet" href="/RailSAHAY/resources/css/cute-alert.css">
<!-- Favicon icon -->
<link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
<!-- Google font-->
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Josefin+Sans:ital@0;1&display=swap" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
    <!-- Google font (font-family: 'Roboto Condensed', sans-serif;) -->
    <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,400,700" rel="stylesheet">
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
<link rel="stylesheet" type="text/css" href="/RailSAHAY/resources/css/gct/gctinput.css">
<!-- themify-icons line icon -->
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/jquery.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/jquery-ui.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/popper.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/RailSAHAY/resources/js/cute-alert.js"></script>
<script>
var GG_ApltCtgr="<%=strApltType%>";
</script>
<script type="text/javascript" src="/RailSAHAY/resources/js/gct/gctfileupload.js"></script>
<style>
body
{
background:#F2F7F9;
}
</style>
    </head>
    <body>
    <form id="frmFileUpload" name="frmFileUpload" method="post" action="/RailSAHAY/GCTFileUpload"  enctype="multipart/form-data">
    <table class="table-upload table" align="center">
    <% if(!strApltType.equals("CONCEPT")) { %>
    <thead>
    <tr>
        <td colspan="4">Upload Listed Files (Max size 5 MB per file)</td>
    </tr>
    </thead>
    <% } %>
    <% if(strApltType.equals("IND")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">TAN</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("HUF")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">AFFIDAVIT</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("PRF")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">TAN</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">PARTNERSHIP DEED</td><td class="file-name" id="fileName3"></td><td><input type="file" id="file3" name="file3" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',3);" id="aFileUpload3"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">POWER OF ATTORNEY</td><td class="file-name" id="fileName4"></td><td><input type="file" id="file4" name="file4" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',4);" id="aFileUpload4"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("COM")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">TAN</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">MOA</td><td class="file-name" id="fileName3"></td><td><input type="file" id="file3" name="file3" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',3);" id="aFileUpload3"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">CERTIFICATE OF INCORPORATION</td><td class="file-name" id="fileName4"></td><td><input type="file" id="file4" name="file4" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',4);" id="aFileUpload4"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("LLP")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">TAN</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">LLP AGREEMENT</td><td class="file-name" id="fileName3"></td><td><input type="file" id="file3" name="file3" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',3);" id="aFileUpload3"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">CERTIFICATE OF INCORPORATION</td><td class="file-name" id="fileName4"></td><td><input type="file" id="file4" name="file4" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',4);" id="aFileUpload4"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">POWER OF ATTORNEY</td><td class="file-name" id="fileName5"></td><td><input type="file" id="file5" name="file5" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',5);" id="aFileUpload5"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("SOC")) { %>
       <tr><td class="file-label">PAN</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">TAN</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">CERTIFICATE OF REGISTRATION</td><td class="file-name" id="fileName3"></td><td><input type="file" id="file3" name="file3" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',3);" id="aFileUpload3"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">MOA</td><td class="file-name" id="fileName4"></td><td><input type="file" id="file4" name="file4" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',4);" id="aFileUpload4"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">RULES & REGULATIONS OF SOCIETY</td><td class="file-name" id="fileName5"></td><td><input type="file" id="file5" name="file5" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',5);" id="aFileUpload5"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">POWER OF ATTORNEY</td><td class="file-name" id="fileName6"></td><td><input type="file" id="file6" name="file6" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',6);" id="aFileUpload6"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("JV")) { %>
       <tr><td class="file-label">JV/CONSORTIUM AGREEMENT</td><td class="file-name" id="fileName1"></td><td><input type="file" id="file1" name="file1" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',1);" id="aFileUpload1"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
       <tr><td class="file-label">NAME AND AUTHORIZATION OF LEAD MEMBER</td><td class="file-name" id="fileName2"></td><td><input type="file" id="file2" name="file2" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',2);" id="aFileUpload2"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(strApltType.equals("CONCEPT")) { %>
       <tr><td class="file-label">CONCEPT PLAN</td><td class="file-name" id="fileName0"></td><td><input type="file" id="file0" name="file0" class="custom-file" onchange="fileValidate(this)" /></td><td><a href="javascript:void(0);" class="actn-link" onclick="fileUpload('<%=strFileRfrnId%>',0);" id="aFileUpload0"><i class="fas fa-file-upload"></i>&nbsp;&nbsp;Upload</a></td></tr>
    <%}%>
    <% if(!strApltType.equals("CONCEPT")) { %>
    <tfoot>
    <tr><td colspan="4">*Note: Only pdf, doc, docx, jpg formats are supported</td></tr>
    </tfoot>
    <% } %>
    </table>
    <input type="hidden" name="Optn" id="Optn" value="UPLOAD_FILE" />
    <input type="hidden" name="FileIndx" id="FileIndx" value="" />
    <input type="hidden" name="FileRfrnId" id="FileRfrnId" value="<%=strFileRfrnId%>" />
    <input type="hidden" name="ApltCtgr" id="ApltCtgr" value="<%=strApltType%>" />
    </form>
    <script>
    
    $(document).ready(function(){
        if("<%=strFileUploadStts%>"=="S") {
            successToast("File Uploaded Successfully");
        }
        if("<%=strFileUploadStts%>"=="F") {
            successToast("Failed to Upload the File: <%=strErorMesg%>");
        }
        window.parent.$("#GCTFileRfrnId").val("<%=strFileRfrnId%>");
        fetchFileList('<%=strFileRfrnId%>');
    });
    </script>
    <form id="frmFileDownload" name="frmFileDownload" method="post" action="/RailSAHAY/GCTFileUpload"  enctype="multipart/form-data">
        <input type="hidden" name="Optn" id="Optn" value="DOWNLOAD_FILE" />
        <input type="hidden" name="FileRfrnId" id="FileRfrnId" value="<%=strFileRfrnId%>" />
        <input type="hidden" name="Indx" id="Indx" value="" />
    </form>
    </body>
</html>