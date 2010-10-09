<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
  <head>
      <title>Create TIGER Line Feature Type</title>
  </head>
  <body>

  <p>This will create a new type of TIGER Line feature and assign it to ALL states.</p>
    <s:form namespace="/tlFeatureType" action="create" method="POST">
        <s:textfield name="year" label="Year of TIGER Line Data" />
        <s:textfield name="postfix" label="Postfix for type" />
        <s:textfield name="description" label="Description" />
        <s:submit name="Submit" value="Submit" />
    </s:form>
  </body>
</html>