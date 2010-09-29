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

            //drawMap2(-34.397, 150.644);
        });

        function reloadSubcode() {
            $.getJSON("/getSubCodesJson", {id: $("select#stateCodeId").val(), ajax:'true'}, function(j) {
                var subCodeList = j.distinctSubCodes;
                for (var i = 0; i < subCodeList.length; i++) {
                    $("select#subCodeId").append(new Option(subCodeList[i], subCodeList[i]));
                }
            });
        }

        function reloadDescriptions() {
            $.getJSON("/getSubCodeDescriptionsByFeatureTypeJson", {featureName: $("select#subCodeId").val(), ajax:'true'}, function(j) {
                var descriptionList = j.descriptions;
                for (var i = 0; i < descriptionList.length; i++) {
                    $("select#featureName").append(new Option(descriptionList[i], descriptionList[i]));
                }
            });
        }

        function drawMap2() {

                $.getJSON("/customMap/getCustomMapJson", {stateId: $("stateCodeId").val(), subCodeId: $("subCodeId").val()}, function(j) {
                    // get the map
                    var mapData = j.map;

                    // figure out the center
                    var ctrLat = (j.minLat + j.maxLat) / 2;
                    var ctrLng = (j.minLng + j.maxLng) / 2;

                    //compute the polygon
                    var borderPoints;

                    for (var i = 0; i < j.borderPoints; i++) {
                        var ll = new (google.maps.LatLng(j.borderPoints[i].latitude, j.borderPoints[i].longitude));
                        borderPoints.push(ll);
                    }

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

<div id="map_canvas" style="width: 640px; height: 480px; display: block; margin-left:auto; margin-right:auto">

</div>

<s:form name="mapGeneratorForm">
    <fieldset>

        <p>
            <label for="stateCodeId">State</label>
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
            <select id="featureName" name="featureName" onchange="drawmap2();">
                <option value="-1">Please Select A Feature Name</option>
            </select>
        </p>

    </fieldset>

</s:form>
<s:a id="menuPage" namespace="/" action="menu">Menu</s:a>

</body>
</html>