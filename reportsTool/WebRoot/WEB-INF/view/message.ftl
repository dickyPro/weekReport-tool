<!DOCTYPE html>
<html>
  <head>
    <title></title>
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
	<link rel="stylesheet" type="text/css" href="../css/global.css" />
	<link rel="stylesheet" type="text/css" href="../css/common.css" />
	<script type="text/javascript" src="../jquery/jquery-1.9.1.min.js"></script>
	<script src="../script/util.js" type="text/javascript"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
	<link href="../script/mobiscroll.custom-2.6.2.min.css" rel="stylesheet" type="text/css" />
	<script src="../script/mobiscroll.custom-2.6.2.min.js" type="text/javascript"></script>
	<style type="text/css">
		button{background:none;border:0;}
		.tip_css{background:url('../images/success1.png') no-repeat;height:54px;padding:0px 0px 0px 80px;font-size:14pt;color:#59b500;font-weight:bold;}
		p{ color:#9b9b9b; font-size:14px;}
	</style>
	<script type="text/javascript">
		 $(document).ready(function () {
		 	var targetUrl=location.href.split('#')[0];
		 	var jsconfig="";
		 	showloading();
		 	$.post("../zzborrow/getJsJDKConfig",{targetUrl:targetUrl},
		 		function(result){
		 		 var data = eval('(' + result + ')');
			 		if(data._ajax_op_flag== "success"){
			 			jsconfig=data._ajax_data;
				 		wx.config({
						  //  debug: true, // 
						    appId: jsconfig.appId, // 
						    timestamp: jsconfig.timestamp, // 
						    nonceStr: jsconfig.noncestr, // 
						    signature: jsconfig.signature, // 
						    jsApiList: ['checkJsApi','closeWindow'] // 
						});
			         }
			      }
			);
			wx.ready(function(){
			    hideloading();
				alert('${result.MSG}');
				WeixinJSBridge.call('closeWindow');
			});
			wx.error(function(res){
				alert(res);
			});
         })
	</script>
  </head>
  <body>
  	<!--<div class="page_header">
		<div>${result.TITLE}</div>
	</div>
  	<div class="page_body">
    	<div style="padding-top:30px;text-align:center;"><button type="button" class="tip_css">提交成功</button></div>
		<div id="message">${result.MSG}</div>
  	</div>-->
  	  </body>
</html>