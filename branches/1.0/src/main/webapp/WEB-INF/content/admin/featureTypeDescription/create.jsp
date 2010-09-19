<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Simple jsp page</title>
</head>

<s:actionerror/>
<s:form namespace="/featureTypeDescription" action="create">
    <s:textfield label="Name" name="ftName" />
    <s:textfield label="Census Code" name="ftCensusCode" />
    <s:submit name="Create" value="Create" />
</s:form>
</html>