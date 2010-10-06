<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<html>
<head>
    <title>Make Map</title>
    <sj:head/>
    <style type="text/css">
        label {
            width: 10em;
            display: block;
            font-size: 12px
        }

        select { font-size: 12px}

        #container {
            width: 90%;
            margin: 10px auto;
            background-color: #fff;
            color: #333;
            border: 1px solid gray;
            line-height: 130%;
        }

        #top {
            padding: .5em;
            background-color: #ddd;
            border-bottom: 1px solid gray;
        }

        #top h1 {
            padding: 0;
            margin: 0;
        }

        #leftnav {
            float: left;
            width: 160px;
            margin: 0;
            padding: 1em;
        }

        #content {
            margin-left: 200px;
            border-left: 1px solid gray;
            padding: 1em;
            max-width: 36em;
        }

        #footer {
            clear: both;
            margin: 0;
            padding: .5em;
            color: #333;
            background-color: #ddd;
            border-top: 1px solid gray;
        }

        #leftnav p {
            margin: 0 0 1em 0;
        }

        #content h2 {
            margin: 0 0 .5em 0;
        }


    </style>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.getJSON("/stateCode/getStateCodes", null, function(j) {
                var stateCodeList = j.stateCodeList;
                for (var i = 0; i < stateCodeList.length; i++) {
                    $("select#stateCodeId").append(new Option(stateCodeList[i].label, stateCodeList[i].id));
                }
            });

        });

        function reloadSubcode() {
            $.getJSON("/subCode/getSubCodeFeatureTypes", {stateCodeId: $("select#stateCodeId").val(), ajax:'true'}, function(j) {
                var subCodeList = j.distinctSubCodes;
                for (var i = 0; i < subCodeList.length; i++) {
                    $("select#subCodeFeatureType").append(new Option(subCodeList[i], subCodeList[i]));
                }
            });
        }

        // descriptions need to come back with description and actual subcode id
        function reloadDescriptions() {
            $.getJSON("/subCode/getSubCodes", {stateCodeId: $("select#stateCodeId").val() , featureClass: $("select#subCodeFeatureType").val(), ajax:'true'}, function(j) {
                var subcodes = j.subCodes;
                for (var i = 0; i < subcodes.length; i++) {
                    $("select#featureName").append(new Option(subcodes[i].subCodeDescription, subcodes[i].id));
                }
            });
        }

        function reloadFeatureTypes() {
            $.getJSON("/customFeature/getFeatureTypesJSON", {stateId: $("select#stateCodeId").val(), subCodeId: $("select#featureName").val(), ajax:'true'}, function(j) {
                var featureTypes = j.cmbFeatureTypes;
                for (var i=0; i<featureTypes.length; i++) {
                    $("select#cmbFeatureTypes").append(new Option(featureTypes[i], featureTypes[i]));
                }
            });
        }

        function drawMap2() {

            $.getJSON("/customMap/getCustomMapJson", {stateId: $("select#stateCodeId").val(), subCodeId: $("select#featureName").val(), ajax:'true'}, function(j) {
                // get the map
                var mapData = j.map;
                // figure out the center
                var ctrLat = (mapData.minLat + mapData.maxLat) / 2;
                var ctrLng = (mapData.minLng + mapData.maxLng) / 2;

                //compute the polygon
                var mapBorderPoints = mapData.borderPoints;
                var borderPoints = new Array();

                for (var i = 0; i < mapBorderPoints.length; i++) {
                    var ll = new google.maps.LatLng(mapBorderPoints[i].latitude, mapBorderPoints[i].longitude);
                    borderPoints.push(ll);
                }

                var borderPolyline = new google.maps.Polyline({
                    path: borderPoints,
                    strokeColor: "#FF0000",
                    strokeOpacity: 0.8,
                    strokeWeight: 2
                });

                var latLng = new google.maps.LatLng(ctrLat, ctrLng);
                var myOptions = {
                    zoom: 8,
                    center: latLng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

                borderPolyline.setMap(map);
            });
        }
    </script>
</head>
<body>
<div id="container">
    <div id="top">
        <h1>Mapmaker 2: The Revenge</h1>
    </div>
    <div id="leftnav">
        <s:form name="mapGeneratorForm">
            <fieldset>

                <p>
                    <label for="stateCodeId">State</label>
                    <select id="stateCodeId" name="stateCodeId" onchange="reloadSubcode();">
                        <option value="-1">Please Select</option>
                    </select>
                </p>

                <p>
                    <label for="subCodeFeatureType">Sub Code Feature Type</label>
                    <select name="subCodeFeatureType" id="subCodeFeatureType" onchange="reloadDescriptions();">
                        <option value="-1">Please Select</option>
                    </select>
                <p>
                    <label for="featureName">Feature Name</label>
                <%--<select id="featureName" name="featureName" onchange="drawMap2();">--%>
                    <select id="featureName" name="featureName" onchange="reloadFeatureTypes();">
                        <option value="-1">Please Select</option>
                    </select>
                </p>
                <p>
                    <label for="cmbFeatureTypes">Feature Types</label>
                    <select id="cmbFeatureTypes" name="cmbFeatureTypes" size="8" multiple="multiple">
                        <option value="-1">Please Select</option>    
                    </select>
                </p>
            </fieldset>

        </s:form>
        <s:a id="menuPage" namespace="/" action="menu">Menu</s:a>
    </div>
    <div id="content" style="margin-left:auto; margin-right: auto;">
        <s:actionerror/>

        <div id="map_canvas" style="width: 640px; height: 480px; margin-left:auto; margin-right:auto">

        </div>
    </div>
</div>

</body>
</html>