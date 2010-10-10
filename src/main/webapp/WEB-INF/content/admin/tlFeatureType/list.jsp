<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>TIGER Line Feature Type Overview</title>
</head>
<style>
    table {
        border-collapse: collapse;
        border: 1px solid black;
    }
</style>
<body>

<p>To import a particular type of Shapefile, click on the link next to the state you wish to import it for.</p>

<s:actionerror />

<table border="1">
    <tr>
        <th>State Name</th>
        <s:iterator var="tlft0" value="%{stateCodeList.stateTLFeatureTypeList}">
            <th><s:property value="description"/></th>
        </s:iterator>
    </tr>

    <s:iterator id="sc" value="%{stateCodeList}">
        <tr>
            <td><s:property value="stateName"/></td>
            <s:iterator id="tlft" value="stateTLFeatureTypeList">
                <td>
                <s:if test="%{#tlft.imported==false}">
                    <s:a namespace="/borderPoint" action="import">
                        <s:param name="tlftId" value="%{#tlft.id}" />
                        Import</s:a>
                </s:if>
                <s:else>
                    Imported</td>
                </s:else>
            </s:iterator>
        </tr>
    </s:iterator>

</table>
<s:a id="tlFeatureTypeCreate" namespace="/tlFeatureType" action="showCreate">Create New Feature Type</s:a>
</body>
</html>