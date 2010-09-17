<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
      <title>Create Feature Class</title>
  </head>
  <body>
    <p>Use This page to create new feature classes</p>

  <s:form namespace="/featureClass" action="create" method="POST">
      <s:textfield name="classCode" label="Feature Class Code" maxLength="5" />
      <s:textfield name="classCodeShortDesc" label="Feature Class Code Short Description" maxLength="25"/>
      <s:textfield name="classCodeDesc" label="Feature Class Code Description" maxlength="255" />
      <s:submit value="Create" name="Create" />
  </s:form>


  </body>
</html>