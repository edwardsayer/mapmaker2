<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Add State Codes From National Shapefile</title>
  </head>
  <body>

  <p>Use this form to upload the National State and Equivalent Shapefile. This will populate the StateCode list
  for the application.</p>

  <s:form namespace="/stateCode" action="create" enctype="multipart/form-data" method="POST">
      <s:file name="fileUpload" label="Shapefile"/>
      <s:submit value="Upload" name="Upload" />
  </s:form>

  </body>
</html>