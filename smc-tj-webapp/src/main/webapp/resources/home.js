/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mtsp = {
    stopFrom: null,
    stopTo: null,
    issues: [],
    map: null,
    routesGroup: null,
    initMap: function () {

        var map = L.map('map').setView([37.389254195966004, -5.977935791015625], 13);

        mtsp.routesGroup = L.featureGroup([]);
        mtsp.routesGroup.addTo(map);


        L.tileLayer('http://{s}.tile.openstreetmap.se/hydda/full/{z}/{x}/{y}.png', {
            attribution: 'Tiles courtesy of <a href="http://openstreetmap.se/" target="_blank">OpenStreetMap Sweden</a> &mdash; Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
            maxZoom: 18
        }).addTo(map);
        
        mtsp.map = map;

    },
    
    onClickFrom: function(e){
    	mtsp.stopFrom = {
            name: "Stop",
            latitude: e.latlng.lat,
            longitude: e.latlng.lng
        };

        L.marker([e.latlng.lat, e.latlng.lng]).addTo(mtsp.map);

        $("#stopList").append("<li>" + e.latlng.lat + ", " + e.latlng.lng + "</li>");
    },
    
    onClickTo: function(e){
    	mtsp.stopTo = {
            name: "Stop",
            latitude: e.latlng.lat,
            longitude: e.latlng.lng
        };

        L.marker([e.latlng.lat, e.latlng.lng]).addTo(mtsp.map);

        $("#stopList").append("<li>" + e.latlng.lat + ", " + e.latlng.lng + "</li>");
    },
    
    onClickIssues: function(e){
    	mtsp.issues.push({
            name: "Stop",
            id: "1",
            latitude: e.latlng.lat,
            longitude: e.latlng.lng
        });

        L.marker([e.latlng.lat, e.latlng.lng]).addTo(mtsp.map);

        $("#stopList").append("<li>" + e.latlng.lat + ", " + e.latlng.lng + "</li>");
    },
    
    getStopsFrom: function(){
    	mtsp.map.off("click", this.onClickIssues);
    	mtsp.map.off("click", this.onClickTo);
    	mtsp.map.on("click", this.onClickFrom);
    },
    
    getStopsTo: function(){
    	mtsp.map.off("click", this.onClickIssues);
    	mtsp.map.off("click", this.onClickFrom);
    	mtsp.map.on("click", this.onClickTo);
    },
    
    getIssues: function(){
    	mtsp.map.off("click", this.onClickFrom);
    	mtsp.map.off("click", this.onClickTo);
    	mtsp.map.on("click", this.onClickIssues);
    },
    
    calculateRoutes: function () {
        mtsp.routesGroup.clearLayers();
        var calculateRouteThis = this;
        $.ajax({
            method: "POST",
            url: "tj/routing",
            data: JSON.stringify({origin: mtsp.stopFrom, target: mtsp.stopTo, issues: mtsp.issues}),
            success: function (data) {
                console.debug(data);
                var color = calculateRouteThis.get_random_color();
                L.geoJson(data, {"color": color}).addTo(calculateRouteThis.map);
            },
            dataType: "json",
            contentType: "application/json"
        });
    },
    
    get_random_color: function(){
        var letters = '0123456789ABCDEF'.split('');
        var color = '#';
        for (var i = 0; i < 6; i++ ){
           color += letters[Math.round(Math.random() * 15)];
        }
        return color;
    }
};


window.onload = mtsp.initMap;