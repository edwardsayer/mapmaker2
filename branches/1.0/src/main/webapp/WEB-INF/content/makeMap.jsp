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
            display: block
        }

        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        #map_canvas {
            height: 100%
        }

        #left {
            width: 250px;
        }

        #main {
            width: 750px;
        }

    </style>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
    </script>
    <script type="text/javascript">
        $(document).ready(function() {
            $.getJSON("/getStateCodesJson", null, function(j) {
                var stateCodeList = j.stateCodeList;
                for (var i = 0; i < stateCodeList.length; i++) {
                    $("select#stateCodeId").append(new Option(stateCodeList[i].label, stateCodeList[i].id));
                }
            });

        });

        function reloadSubcode() {
            $.getJSON("/getSubCodesJson", {id: $("select#stateCodeId").val(), ajax:'true'}, function(j) {
                var subCodeList = j.distinctSubCodes;
                for (var i = 0; i < subCodeList.length; i++) {
                    $("select#subCodeFeatureType").append(new Option(subCodeList[i], subCodeList[i]));
                }
            });
        }

        // descriptions need to come back with description and actual subcode id
        function reloadDescriptions() {
            $.getJSON("/getSubCodeDescriptionsByFeatureTypeJson", {stateCodeId: $("select#stateCodeId").val() , featureName: $("select#subCodeFeatureType").val(), ajax:'true'}, function(j) {
                var subcodes = j.subCodes;
                for (var i = 0; i < subcodes.length; i++) {
                    $("select#featureName").append(new Option(subcodes[i].subCodeDescription, subcodes[i].id));
                }
                //                var descriptionList = j.descriptions;
                //                for (var i = 0; i < descriptionList.length; i++) {
                //                    $("select#featureName").append(new Option(descriptionList[i], descriptionList[i]));
                //                }
            });
        }

        function drawMap2() {

            $.getJSON("/customMap/getCustomMapJson", {stateId: $("select#stateCodeId").val(), subCodeId: $("select#featureName").val(), ajax:'true'}, function(j) {
                // get the map
                var mapData = j.map;

                // figure out the center
                var ctrLat = (j.minLat + j.maxLat) / 2;
                var ctrLng = (j.minLng + j.maxLng) / 2;

                //compute the polygon
                var mapBorderPoints = mapData.borderPoints;
                var borderPoints = new Array();

                alert(mapBorderPoints.length);
                alert("First point:" + mapBorderPoints[0].latitude + "," + mapBorderPoints[0].longitude);
                for (var i = 0; i < mapBorderPoints.length; i++) {
                    var ll = new (google.maps.LatLng(mapBorderPoints[i].latitude, mapBorderPoints[i].longitude));
                    borderPoints.push(ll);
                }

                alert(borderPoints.length);
                
                borderPolygon = new google.maps.Polygon({
                    paths: borderPoints,
                    strokeColor: "#FF0000",
                    strokeOpacity: 0.8,
                    strokeWeight: 2,
                    fillColor: "#FF0000",
                    fillOpacity: 0.35
                });

                var latLng = new google.maps.LatLng(ctrLat, ctrLng);
                var myOptions = {
                    zoom: 8,
                    center: latlng,
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };
                //var map = new google.maps.Map($("div#map_canvas"), myOptions);
                var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
            });
            //            var latlng = new google.maps.LatLng(lat, lng);
            //            var myOptions = {
            //                zoom: 8,
            //                center: latlng,
            //                mapTypeId: google.maps.MapTypeId.ROADMAP
            //            };
            //            //var map = new google.maps.Map($("div#map_canvas"), myOptions);
            //            var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
        }
    </script>
</head>
<body>

<div id="left">
    <s:form name="mapGeneratorForm">
        <fieldset>

            <p>
                <label for="stateCodeId">State</label>
                <select id="stateCodeId" name="stateCodeId" onchange="reloadSubcode();">
                    <option value="-1">Please Select A State</option>
                </select>
            </p>

            <p>
                <label for="subCodeFeatureType">Sub Code Feature Type</label>
                <select name="subCodeFeatureType" id="subCodeFeatureType" onchange="reloadDescriptions();">
                    <option value="-1">Please Select A Feature Type</option>
                </select>
            <p>
                <label for="featureName">Feature Name</label>
                <select id="featureName" name="featureName" onchange="drawMap2();">
                    <option value="-1">Please Select A Feature Name</option>
                </select>
            </p>

        </fieldset>

    </s:form>
    <s:a id="menuPage" namespace="/" action="menu">Menu</s:a>
</div>

<div id="main">
    <s:actionerror/>

    <div id="map_canvas" style="width: 640px; height: 480px; display: block; margin-left:auto; margin-right:auto">

    </div>
</div>


</body>
</html>