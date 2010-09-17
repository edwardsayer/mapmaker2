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
      <s:textfield label="Census Code" name="censusCode" />
      <s:textfield label="Description" name="description" />
      <s:submit value="Create" name="create" />
      
  </s:form>
  </body>
</html>