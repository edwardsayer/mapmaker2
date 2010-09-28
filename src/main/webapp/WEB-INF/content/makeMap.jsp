<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Make Map</title>
    <sj:head />
    <style type="text/css">
        label   {width: 10em; display:block}
        html { height: 100% }
        body { height: 100%; margin: 0px; padding: 0px }
        #map_canvas { height: 100% }
        
    </style>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
</script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.getJSON("/getStateCodesJson", null, function(j) {
                var stateCodeList = j.stateCodeList;
                for (var i=0; i<stateCodeList.length; i++) {
                    $("select#stateCodeId").append(new Option(stateCodeList[i].label, stateCodeList[i].id));
                }
            });

            initialize();
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

        function initialize() {
          var latlng = new google.maps.LatLng(-34.397, 150.644);
          var myOptions = {
            zoom: 8,
            center: latlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
          };
          var map = new google.maps.Map(document.getElementById("map_canvas"),
              myOptions);
        }
        
    </script>
</head>
<body>

<div id="map_canvas" style="width: 640px; height: 480px; display: block; margin-left:auto; margin-right:auto">
    
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