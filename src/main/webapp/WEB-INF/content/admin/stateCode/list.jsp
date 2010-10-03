<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags" %>
<html>
  <head>
      <title>State Code Management</title>
  </head>
  <body>

  <s:url id="stateCodeJsonUrl" namespace="/stateCode" action="getStateCodesJson" />
    <sjg:grid gridModel="stateCodeList"
        caption="State FIPS Codes"
        dataType="json"
        href="%{stateCodeJsonUrl}">
        <sjg:gridColumn name="stateName" title="State" />
        <sjg:gridColumn name="stateAbbr" title="Abbreviation" />
        <sjg:gridColumn name="stateCode" title="FIPS Code" />
    </sjg:grid>

    <s:a namespace="/stateCode" action="showCreate">Create Statecode</s:a>
  </body>
</html>