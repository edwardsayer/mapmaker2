<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Mapmaker Admin Menu</title>
  </head>
  <body>

  <h1>Admin Menu</h1>
  <p><s:a id="stateManagement" namespace="/state" action="">Manage States</s:a> </p>
  <p><s:a id="borderPointManagement" namespace="/borderPoint" action="">Manage Border Points</s:a> </p>
  <p><s:a id="tigerFeatureTypeManagement" namespace="/tigerFeatureType" action="">Manage TIGER Feature Types</s:a> </p>
  <p><s:a id="locationManagement" namespace="/location" action="">Manage Locations</s:a> </p>
  <p><s:a id="importShapefile" namespace="/shapefile" action="">Import Shapefile</s:a>
  </body>
</html>