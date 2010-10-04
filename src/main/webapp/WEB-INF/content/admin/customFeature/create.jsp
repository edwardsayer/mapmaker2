<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Create Custom Features</title>
  </head>
  <body>

  <p>Use this page to upload a feature set.</p>
  <s:form namespace="/customFeature" action="create" enctype="multipart/form-data" method="POST">
      <s:file name="fileUpload" label="File" />
      <s:submit name="Upload" value="Upload" />
  </s:form>

  <p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
  </body>
</html>