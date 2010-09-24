<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Add SubCode</title>
    <sj:head />
</head>
<body>
<p>Use this form to create a new 'sub-code' (i.e. a Code for TIGER data that is state-based).</p>
<ul>
    <li>State: Name of State</li>
    <li>SubCode: Unique Code for this TIGER data</li>
    <li>SubCodeType: Type of This Sub Code (i.e. "county" or "zip code")</li>
    <li>SubCodeDescription: Description of this Sub Code (i.e. "Smith County" or "63118")</li>
</ul>
<s:form name="/subCode" action="create" method="POST" enctype="multipart/form-data">
    <s:select name="stateCodeId"
              id="stateCodeId"
              list="%{stateCodeList}"
              listKey="id"
              listValue="label"
              label="State"/>

    <s:textfield name="subCode" label="code" />
    <s:textfield name="subCodeDescription" label="Description" />
    <s:textfield name="subCodeType" label="Type" />
    <s:submit value="Create" name="Create" />
</s:form>


</body>
</html>