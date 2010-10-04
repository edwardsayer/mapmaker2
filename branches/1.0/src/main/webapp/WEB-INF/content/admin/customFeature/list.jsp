<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Custom Feature Overview</title>
  </head>
  <body>
  <p>Overview grid will go here.</p>

  <s:a namespace="/customFeature" action="showCreate">Upload Custom Feature Set</s:a>
  <p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
  </body>
</html>