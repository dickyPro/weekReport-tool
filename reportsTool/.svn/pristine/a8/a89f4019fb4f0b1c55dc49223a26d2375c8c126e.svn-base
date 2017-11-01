<!DOCTYPE html>
<html>
<head>
<title>借阅批复</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/global.css" />
<link rel="stylesheet" type="text/css" href="../css/page.css" />
<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
<script src="../script/util.js" type="text/javascript"></script>
<style type="text/css">
	button
	{
		background:none;
		border:0;
	}
	.div_paddingTop_10
	{
		padding-left:5px;
		padding-top:5px;
	}
	.input_no_border{
	width:95%;
	border-left:0px;
	border-top:0px;
	border-right:0px;
	border-bottom:1px:
	background:#F0F0F0;
	border-style:solid;
	border-color:#eaeaea;
	padding-top:20px;
	}
</style>
<script type="text/javascript">
    $(document).ready(function () {
    	var param = getQueryString();
    	$("#applyno").val(param.applyno);
    	$("#borrow_user").val(param.borrow_user);
    })
	function submitReason(){
         $("#queryform").attr("action","../app/submitDesagreation");
		 $("#queryform").submit();
	}
	
</script>
</head>

<body>
	<div class="page_header">
		<div>借阅批复</div>
	</div>
	<div class="page_body">
	  <form id="queryform" action="" data-ajax="false" method="post">
	    <input name="applyno"  id="applyno" value="0"  type="text" style="display:none;"/>
	    <input name="borrow_user"  id="borrow_user" value="0"  type="text" style="display:none;"/>
		 <p>
                  请您填写不能借阅的原因：<br/>
			<textarea name="no_borrow_reason" rows="2" cols="20" id="no_borrow_reason" style="margin-top:10px;margin-left:5px;width:95%; height:60px;" placeholder="  ...   "></textarea>
		</p>
		<p>
		 <div class="form_button" onMouseOver="this.style.cursor='pointer'" onClick="submitReason();">
			 <label style="color: white;font-weight: bold;font-size: 12pt;">确定</label>
		</div>
		</p>
		</form>
	</div>
</body>
</html>
