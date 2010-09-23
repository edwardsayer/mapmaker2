<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Add State Code</title>
  </head>
  <body>
  <s:form namespace="/stateCode" action="create">
      <s:textfield name="stateName" label="State Name" />
      <s:textfield name="stateAbbr" label="State Abbreviation" />
      <s:textfield name="stateCode" label="Code" />
      <s:submit name="Create" value="Create" />
  </s:form>

  </body>
</html>