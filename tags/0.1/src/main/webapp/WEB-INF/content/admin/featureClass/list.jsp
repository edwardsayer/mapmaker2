<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
<head>
    <title>View Feature Classes</title>
    <sj:head/>
</head>
<body>

<s:url id="gridJSON" namespace="/featureClass" action="featureClassesJSON"/>
<sjg:grid gridModel="featureClasses" id="featureClassGrid" caption="Feature Classes" href="%{gridJSON}">
    <sjg:gridColumn name="classCode" id="classCode"/>
    <sjg:grid gridModel="classCodeShortDesc" id="classCodeShortDesc"/>
    <sjg:grid gridModel="classCodeDesc" id="classCodeDesc"/>
</sjg:grid>


</body>
</html>