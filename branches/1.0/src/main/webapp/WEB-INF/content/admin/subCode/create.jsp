<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Add SubCode</title>
</head>
<body>
<s:form name="/subCode" action="create" method="POST">
    <s:select name="stateCodeId"
              id="stateCodeId"
              list="stateCodeList"
              listKey="id"
              listValue="label"
              label="State"/>

    <s:textfield name="code" label="code" />
    <s:textfield name="subCodeDescription" label="Description" />
    <s:submit value="Create" name="Create" />
</s:form>


</body>
</html>