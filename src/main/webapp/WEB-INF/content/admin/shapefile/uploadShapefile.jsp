<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <title>Upload Shapefile</title>
  </head>
  <body>

  <s:actionmessage />
  <p>Use this form to upload a Shapefile for a location</p>
    <s:form namespace="/shapefile" action="processShapefile" enctype="multipart/form-data" method="POST">

        <s:select list="%{states}"
                  name="stateId"
                  listKey="id"
                  listValue="stateName"
                label="State"/>
        
        <s:file name="fileUpload" label="Shapefile" />
        <s:submit name="Upload" value="Upload" />
    </s:form>
  </body>
</html>