<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Make Map</title>
    <sj:head />
    <style type="text/css">
        label   {width: 10em; display:block}
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $.getJSON("/getStateCodesJson", null, function(j) {
                var stateCodeList = j.stateCodeList;
                for (var i=0; i<stateCodeList.length; i++) {
                    $("select#stateCodeId").append(new Option(stateCodeList[i].label, stateCodeList[i].id));
                }
            })
        });

        function reloadSubcode() {
            $.getJSON("/getSubCodesJson", {id: $("select#stateCodeId").val(), ajax:'true'}, function(j) {
                var subCodeList = j.distinctSubCodes;
                for (var i = 0; i < subCodeList.length; i++) {
                    $("select#subCodeId").append(new Option(subCodeList[i],subCodeList[i]));
                }
            });
        }

        function reloadDescriptions() {
            $.getJSON("/getSubCodeDescriptionsByFeatureTypeJson", {featureName: $("select#subCodeId").val(), ajax:'true'}, function(j) {
                var descriptionList = j.descriptions;
                for (var i = 0; i< descriptionList.length; i++) {
                    $("select#featureName").append(new Option(descriptionList[i],descriptionList[i]));
                }
            })
        }

    </script>
</head>
<body>

<div id="mapSection">
    <p>Generated Map Will Go Here</p>
</div>

<s:form name="mapGeneratorForm">
    <fieldset>

        <p>
            <label for = "stateCodeId">State</label>
            <select id="stateCodeId" name="stateCodeId" onchange="reloadSubcode();">
                <option value="-1">Please Select A State</option>
            </select>
        </p>

        <p>
            <label for="subCodeId">SubCode</label>
            <select id="subCodeId" name="subCodeId" onchange="reloadDescriptions();">
                <option value="-1">Please Select A SubCode</option>
            </select>
        </p>

        <p>
            <label for="featureName">Feature Name</label>
            <select id="featureName" name="featureName">
                <option value="-1">Please Select A Feature Name</option>
            </select>
        </p>

    </fieldset>

</s:form>
<s:a id="menuPage" namespace="/" action="menu">Menu</s:a>

</body>
</html>