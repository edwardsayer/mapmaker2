<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create Census Code Type</title>
  </head>
  <body>
  <p>Use this page to create a new type of Census (formerly FIPS 55) Code</p>

  <s:actionerror />
  
  <s:form namespace="censusCode" action="create" validate="true">
      <s:textfield label="State Code" name="stateCode" />
      <s:textfield label="State Name" name="stateName" />
      <s:textfield label="County Code" name="countyCode" />
      <s:textfield label="County Name" name="countyName" />
      <s:submit value="Create" name="create" />
      
  </s:form>
  </body>
</html>