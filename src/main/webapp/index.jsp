<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<link rel="stylesheet" type="text/css" href="static/css/xcConfirm.css"/>
<body>
<input type="text" value="ruiqi" onclick="nnn();">
<input type="text" value="ruiqi" onclick="nnn();">
<input type="text" value="新建索引" onclick="nnn();">
<input type="button" value="新建索引" onclick="nnn();">
</body>
<script type="text/javascript" src="static/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="static/js/xcConfirm.js"></script>
<script type="text/javascript">
	function nnn() {
		$.post("/search/createIndex"){
			
		}
		window.wxc.xcConfirm("删除失败，请稍后再试！", window.wxc.xcConfirm.typeEnum.error);		
	}
</script>
</html>