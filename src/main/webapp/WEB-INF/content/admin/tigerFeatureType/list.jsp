<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
  <head>
      <title>Tiger Feature Types</title>
  </head>
  <body>

  <s:actionmessage />
  
  <s:url id="tigerFeatureTypesJson" namespace="/tigerFeatureType" action="tigerFeatureTypesJson" />
  <sjg:grid gridModel="tigerFeatureTypes"
        caption="Tiger Feature Class Codes"
        dataType="json"
        href="%{tigerFeatureTypesJson}">
      <sjg:gridColumn name="tigerFeatureClassCode" title="Class Code" />
      <sjg:gridColumn name="description" title="Description" />
        </sjg:grid>


  </body>
</html>