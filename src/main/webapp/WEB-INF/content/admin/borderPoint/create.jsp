<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Create Border Points for State and Feature Type</title>
</head>
<body>

<s:actionerror/>

<p>Use this form to create a new set of border points for a given state and feature type.</p>

<s:form namespace="/borderPoint" action="create">
    <s:select list="%{states}"
              name="stateId"
              label="State"
              listKey="id"
              listValue="stateName"/>
    <s:select list="%{tigerFeatureTypes}"
              name="tigerFeatureTypeId"
              label="Tiger Feature Type"
              listKey="id"
              listValue="tigerFeatureClassCode"/>

    <s:file name="fileUpload" label="Shapefile"/>
    <s:submit value="Create" name="Create"/>
</s:form>
</body>
</html>