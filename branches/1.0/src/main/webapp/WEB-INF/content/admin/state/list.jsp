<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
<head>
    <title>State List</title>
    <sj:head/>
</head>
<body>
<s:actionmessage/>

<s:url id="statesJsonUrl" action="statesJson" namespace="/state" />
<sjg:grid gridModel="states"
          caption="States"
          dataType="json"
          href="%{statesJsonUrl}" title="States">

    <sjg:gridColumn name="stateName" index="stateName" title="State" />
    <sjg:gridColumn name="stateAbbr" index="stateAbbr" title="Abbreviation" />
    <sjg:gridColumn name="stateFIPS" index="stateFIPS" title="FIPS Code" />
</sjg:grid>

<p><s:a namespace="/state" action="showCreate">Create New State</s:a></p>
<p><s:a id="menuPage" namespace="/" action="menu">Menu</s:a></p>

</body>
</html>