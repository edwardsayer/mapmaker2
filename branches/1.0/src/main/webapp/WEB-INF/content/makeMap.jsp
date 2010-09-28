<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Make Map</title>
    <sj:head debug="true"/>
    <script type="text/javascript">
        $(document).ready(function() {
            $.getJSON("/getStateCodesJson", null, function(j) {
                var options='';
                var stateCodeList = j.stateCodeList;
                for (var i=0; i<stateCodeList.length; i++) {
                    options += '<option value="' + stateCodeList[i].id + '">' + stateCodeList[i].label + '</option>';
                }
                $("select#stateCodeId").html(options);
            })
        });

        function reloadSubcode() {
            $.getJSON("/getSubCodesJson", {id: $("select#stateCodeId").val(), ajax:'true'}, function(j) {
                var options = '';
                var subCodeList = j.distinctSubCodes;
                for (var i = 0; i < subCodeList.length; i++) {
                    options += '<option value="' + subCodeList[i] + '">' + subCodeList[i] + '</option>';
                }

                $("select#subCodeId").html(options);
            });
        }

    </script>
</head>
<body>

<div id="mapSection">
    <p>Generated Map Will Go Here</p>
</div>

<s:form name="mapGeneratorForm">
    <fieldset>
<%--        <s:url id="ajaxActionUrl" namespace="/" action="getStateCodesJson"/>
        <s:select name="stateCodeId"
                  id="stateCodeId"
                  label="State"
                  list="stateCodeList"
                  listKey="id"
                  listValue="label"
                onchange="reloadSubcode();"/>--%>

        <p>
            <label for = "stateCodeId">State</label>
            <select id="stateCodeId" name="stateCodeId" onchange="reloadSubcode();">
                <option value="-1">Please Select A State</option>
            </select>
        </p>

        <p>
            <label for="subCodeId">SubCode</label>
            <select id="subCodeId" name="subCodeId">
                <option value="-1">Please Select A SubCode</option>
            </select>
        </p>

    </fieldset>

</s:form>
<s:a id="menuPage" namespace="/" action="menu">Menu</s:a>

</body>
</html>