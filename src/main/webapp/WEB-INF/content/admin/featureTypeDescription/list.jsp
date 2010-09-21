<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Feature Type Descriptions</title>
        <sj:head />
  </head>
  <body>

    <s:actionmessage />
    <s:url id="featureTypeDescriptionData" namespace="/featureTypeDescription" action="featureTypeDescriptionsJson" />
    <sjg:grid gridModel="featureTypeDescriptions"
              title="Feature Type Descriptions"
              href="%{featureTypeDescriptionData}"
              dataType="json">
        <sjg:gridColumn name="featureTypeName" title="Feature Type" index="featureTypeName" />
        <sjg:gridColumn name="featureClassCode" title="Class Code" index="featureClassName" />
    </sjg:grid>

    <s:a namespace="/featureTypeDescription" action="showCreate">Create New Feature Type Description</s:a>

  </body>
</html>