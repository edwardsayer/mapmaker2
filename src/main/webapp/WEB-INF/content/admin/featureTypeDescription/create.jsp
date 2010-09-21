<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Feature Class Description</title>
</head>

<body>

<s:actionerror/>

<p>Use this form to create a new Feature Class Code</p>

<s:form namespace="/featureTypeDescription" action="create">
    <s:textfield label="Name" name="ftName" />
    <s:textfield label="Census Code" name="ftCensusCode" />
    <s:submit name="Create" value="Create" />
</s:form>
</body>
</html>