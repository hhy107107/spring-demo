<#assign contextPath=springMacroRequestContext.getContextPath() />
<!DOCTYPE html>
<html>
 <head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
  <meta name="keywords" content="小黄人">
  <meta name="description" content="小黄人">  
  <meta name="viewport" content="width=device-width,initial-scale=1" />
  <meta name="renderer" content="webkit" />
  <meta http-equiv="Cache-Control" content="no-transform" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />
  <title>小黄人</title>
  <link rel="icon" type="image/png" href="${contextStaticPath}/favicon.png" />
  <link href="${contextStaticPath}/smallyellow/css/${vue.appCss!''}" rel="stylesheet" />
 </head>
 <body>
  <div id="app"></div>
  <input id="contextPath" type="hidden" value="${contextPath}" />
  <script type="text/javascript" src="${contextStaticPath}/smallyellow/js/${vue.manifestJs!''}"></script>
  <script type="text/javascript" src="${contextStaticPath}/smallyellow/js/${vue.vendorJs!''}"></script>
  <script type="text/javascript" src="${contextStaticPath}/smallyellow/js/${vue.appJS!''}"></script>
 </body>
</html>