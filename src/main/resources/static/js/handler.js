var polygons;
var zoomLevel;
function GetMap() {
    var map = new Microsoft.Maps.Map("#tadyaMap", {
      credentials:
        "AtEGXpWltd3FqxpW2NOpPSpz_YQb5XUTt1dwFLrQbtSEyvA1GwIo8TnARNadz3iS",
      center: new Microsoft.Maps.Location(41.1267716, 29.109357),
      mapTypeId: Microsoft.Maps.MapTypeId.road,
      zoom: 14,
    });

    Microsoft.Maps.Events.addHandler(map, 'viewchangeend', function () {
        zoomLevel = map.getZoom()
        hideBuildings();
        console.log(zoomLevel);
    });

    Microsoft.Maps.loadModule("Microsoft.Maps.GeoJson", function () {
      Microsoft.Maps.GeoJson.readFromUrl(
        "../../data/beykozz.geojson",
        function (beykoz) {
          polygons = beykoz;  
          //Add the shapes to the map.
          hideBuildings();
          map.entities.push(beykoz);

          // Add a click event handler to each polygon
          addClickHandlerToPolygons(beykoz);
        }
      );
    });
    window.addEventListener('load', getBuildings);
  }

function hideBuildings() {
    if(zoomLevel > 14) {
        var Option = {
            visible: true
        };
    } else {
        var Option = {
            visible: false
        };
    }
    polygons.forEach(polygon => {
        polygon.setOptions(Option);
    })
}

function showBuilding(building){
    var id = building['osm_id'];
    
    polygons.forEach(data => {
        if (parseInt(data.metadata.osm_id) == id) {
            data.setOptions({
                strokeColor: 'red',
                fillColor: 'red'    
            })
        }
    });
}

async function getBuildings() {
    try {
        const response = await fetch("http://localhost:8080/map");
        const buildings = await response.json();
        buildings.forEach(building => {
            showBuilding(building);
        });
    } catch (error) {
        console.log('Error: ', error);
    }
}

async function sendBuildings(polygon) {
    
}