<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Sub Code Overview</title>
  </head>
  <body>
    <p>Overview Grid Goes Here</p>

    <p><s:a namespace="/subCode" action="create">Create New Subcode</s:a> </p>
  <p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
  </body>
</html>