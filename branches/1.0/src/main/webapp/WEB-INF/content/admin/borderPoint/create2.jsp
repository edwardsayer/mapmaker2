<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
  <head>
      <title>Create Border Point</title>
      <sj:head />
  </head>
  <body>
  <p>Use this form to load border points for a given state and state feature.</p>

  <s:form id="borderPointForm"
          namespace="/borderPoint" action="create"
          enctype="multipart/form-data"
          method="POST">
      <s:url id="stateUrl" namespace="/stateCode" action="getStateCodesJson" />
      <sj:select href="%{stateUrl}"
                 id="stateId"
                 name="stateId"
                 onChangeTopics="reloadSecondList"
                 list="stateCodeList"
                 listKey="id"
                 dataType="json"
                 listValue="label"
              label="State Code"/>
      
      <sj:select id="subCodeId"
                 name="subCodeId"
                 href="%{stateUrl}"
                 reloadTopics="reloadSecondList"
                 formIds="borderPointForm"
                 dataType="json"
                 list="subCodeList"
                 listKey="id"
                 listValue="subCodeDescription"
              label="Sub Code"/>
  </s:form>

  </body>
</html>