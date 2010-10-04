<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Mapmaker Admin Menu</title>
  </head>
  <body>

  <h1>Admin Menu</h1>
  <p><s:a id="stateCodeManagement" namespace="/stateCode" action="">Manage States</s:a> </p>
  <p><s:a id="borderPointManagement" namespace="/borderPoint" action="">Manage Border Points</s:a> </p>
  <p><s:a id="featureManagement" namespace="/customFeature" action="">Manage Features</s:a> </p>
  </body>
</html>