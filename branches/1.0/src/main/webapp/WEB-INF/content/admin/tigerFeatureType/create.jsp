<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create New TIGER Feature Type</title>
  </head>
  <body>
    <p>Each TIGER feature type has a feature type class code, such as G5200 for U.S. Congressional districts. Use
    this form to create new TIGER Feature Class Codes</p>

  <s:form name="/tigerFeatureType" action="create" method="POST">
      <s:textfield name="tigerFeatureClassCode" label="TIGER Feature Class Code" />
      <s:textfield name="tigerFeatureClassCodeDescription" label="TIGER Feature Class Code Description" />
      <s:submit value="Create" name="Create" />
  </s:form>
  </body>
</html>