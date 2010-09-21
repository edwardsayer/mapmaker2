<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
<head>
    <title>View Census Code Types</title>
    <sj:head />
</head>
<body>

<s:url id="censusCodeData" namespace="/censusCode" action="censusCodesJson" />
<sjg:grid gridModel="censusCodes"
        href="%{censusCodeJson}"
        caption="Census Codes"
        dataType="json">
    
        </sjg:grid>
<s:a id="createCensusCode" namespace="/censusCode" action="showCreate">Create New Census Code</s:a>
</body>
</html>