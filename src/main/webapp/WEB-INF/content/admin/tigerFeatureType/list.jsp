<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
<head>
    <title>Tiger Feature Types</title>
    <sj:head />
</head>
<body>

<s:actionmessage/>

<s:url id="tigerFeatureTypesJson" namespace="/tigerFeatureType" action="tigerFeatureTypesJson"/>
<sjg:grid gridModel="tigerFeatureTypes"
          caption="Tiger Feature Class Codes"
          dataType="json"
          href="%{tigerFeatureTypesJson}">
    <sjg:gridColumn name="tigerFeatureClassCode" title="Class Code" align="center"/>
    <sjg:gridColumn name="description" title="Description" width="200"/>
</sjg:grid>

<p><s:a id="createTFT" namespace="/tigerFeatureType" action="showCreate">Create New Feature Type</s:a></p>

<p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>
</body>
</html>