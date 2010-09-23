<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create New TIGER Feature Type</title>
  </head>
  <body>
    <p>Each TIGER feature type has a feature type class code, such as G5200 for U.S. Congressional districts. Use
    this form to create new TIGER Feature Class Codes</p>

    <s:actionerror />

  <s:form name="/tigerFeatureType" action="create" method="POST">
      <s:textfield name="tigerFeatureClassCode" label="TIGER Feature Class Code" maxlength="5"/>
      <s:textfield name="tigerFeatureClassCodeDescription" label="TIGER Feature Class Code Description"
              maxLength="25"/>
      <s:submit value="Create" name="Create" />
  </s:form>

    <p><s:a namespace="/tigerFeatureType" action="">Manage TIGER Feature Types</s:a> </p>
  <p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
  </body>
</html>