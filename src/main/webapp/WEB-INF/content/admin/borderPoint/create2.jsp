<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Create Border Point</title>
    <sj:head/>
</head>
<body>
<p>Use this form to load border points for a given state and state feature.</p>

<s:form id="borderPointForm"
        namespace="/borderPoint" action="create"
        enctype="multipart/form-data"
        method="POST">

    <s:file name="fileUpload" label="Shapefile"/>
    <s:submit value="Create" name="Create"/>
</s:form>

</body>
</html>