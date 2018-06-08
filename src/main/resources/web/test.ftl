<#import "spring.ftl" as spring/>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
语言选择  
<a href="?lang=en_US">English(US)</a>  
<a href="?lang=zh_CN">简体中文</a>  
<@spring.message code="hello"/>  
</body>
</html>