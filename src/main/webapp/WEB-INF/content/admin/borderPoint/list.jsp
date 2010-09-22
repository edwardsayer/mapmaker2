<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>BorderPoint Overview</title>
  </head>
  <body>
  <s:actionmessage />

  <p>Overview Grid Goes Here</p>

  <s:a namespace="/borderPoint" action="showCreate">Create New BorderPoint Set</s:a>
  </body>
</html>