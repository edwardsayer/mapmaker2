<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Upload Shapefile</title>
  </head>
  <body>

  <p>Use this form to upload a Shapefile</p>
    <s:form name="shapefile" action="processShapefile" enctype="multipart/form-data" method="POST">

        <s:file name="fileUpload" label="Shapefile" />
        <s:submit name="Upload" value="upload" />
    </s:form>
  </body>
</html>