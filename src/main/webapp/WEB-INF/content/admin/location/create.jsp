<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create New Location</title>
  </head>
  <body>
  <p>Use this form to create a new location (State-based only)</p>

  <s:actionerror />

  <s:form namespace="/location" action="create">
      <s:select list="%{states}"
                name="stateId"
                label="State"
                listKey="id"
                listValue="stateName" />

      <s:textfield name="locationName" label="Location Name" />
      <s:textfield name="fipsCode" label="FIPS Code" />

      <s:select list="%{featureTypeDescriptions}"
                name="featureTypeDescriptionId"
                label="Feature Type Description"
                listKey="id"
                listValue="featureTypeName" />

      <s:submit name="Create" value="Create" />
  </s:form>
  </body>
</html>