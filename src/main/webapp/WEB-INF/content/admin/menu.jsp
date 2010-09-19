<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Mapmaker Admin Menu</title>
  </head>
  <body>

  <h1>Admin Menu</h1>
  <p><s:a id="censusCodeManagement" namespace="/censusCode" action="">Manage Census Codes</s:a></p>
  <p><s:a id="featureTypeDescription" namespace="/featureTypeDescription" action="">Manage Feature Type Descriptions</s:a> </p>
  <p><s:a id="locationManagement" name="/location" action="">Manage Locations</s:a> </p>
  <p><s:a id="stateManagement" name="/state" action="">Manage States</s:a> </p>
  <p><s:a id="importShapefile" namespace="/shapefile" action="">Import Shapefile</s:a>
  </body>
</html>