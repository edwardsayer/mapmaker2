<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create New State</title>
  </head>
  <body>

  <p>Use this form to create a new State.</p>

  <s:form namespace="/state" action="create" method="POST">
      <s:textfield name="stateName" label="Name"/>
      <s:textfield name="stateAbbr" label="Abbreviation" maxLength="2"/>
      <s:textfield name="stateFIPS" label="FIPS Code"/>
      <s:submit name="Create" value="Create" />
  </s:form>
  </body>
</html>