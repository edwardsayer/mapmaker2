<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create Border Points for State and Feature Type</title>
</head>
<body>

<s:actionerror/>

<p>Use this form to create a new set of border points for a given state and feature type.</p>

<s:form namespace="/borderPoint" action="create" enctype="multipart/form-data" method="POST">
    <s:select list="%{statesList}"
              listKey="id"
              listValue="stateName"
              name="stateId"
              id="stateId"
              label="State"
/>
    <s:select list="%{tigerFeatureTypes}"
              listKey="id"
              listValue="label"
              name="featureTypeId"
              label="Tiger Feature Type"
/>

    <s:file name="fileUpload" label="Shapefile"/>
    <s:submit value="Create" name="Create"/>
</s:form>

<p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
</body>
</html>