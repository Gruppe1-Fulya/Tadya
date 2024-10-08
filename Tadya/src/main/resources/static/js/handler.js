var polygons;
var zoomLevel;
var currentA;
var currentB;
var clicked = null;

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
        getBuildings();
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
    if(zoomLevel > 15) {
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
                fillColor: Microsoft.Maps.Color.fromHex('#FF8D8D')
            })
        }
    });
}

// handling requests

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
    const building = {
        osm_id: parseInt(polygon.target.metadata.osm_id),
        fclass: polygon.target.metadata.fclass,
        mel_zeit: new Date().toJSON(),
        besteatigung: false
    }
    const requestBody = JSON.stringify(building);
    try {
        await fetch("http://localhost:8080/map/add",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: requestBody
        })
    } catch (error) {
        console.log("Error: ", error);
    }
}

async function sendBericht(polygon, tote, verletzte) {
    try {
        if(clicked == null) {
            throw new MyError("Please give a destruction level!");
        } else if (currentA == null) {
            throw new MyError("You are not authorised")
        }
        
        var date = new Date();
        date.setHours(date.getHours() + 3);

        osm_id = parseInt(polygon.target.metadata.osm_id);
        const bericht = {
            bericht_id: generateRandomNumber(),
            tote: parseInt(tote),
            verletzte: parseInt(verletzte),
            zustand: "L".concat(clicked-1),
            bericht_zeit: date.toJSON()
        }
        console.log(currentA);
        const requestBody = JSON.stringify(bericht);
        const url = "http://localhost:8080/map/bericht/" + osm_id + "/" + currentA.auto_id;
        console.log(requestBody);
        await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: requestBody
        })
        clicked = null;
    } catch (error) {
        if (error instanceof MyError) {
            alert(error.message);
        } else {
            console.log("Error: ", error);
        }
    }
}

async function getBericht(osm_id, currentBuilding, currentAuto) {
    const url = "http://localhost:8080/map/bericht/get/" + osm_id;
    try {
        const response = await fetch(url);
        const bericht = await response.json();
        console.log(bericht);
        console.log(currentBuilding);
        showBericht(bericht, currentBuilding, currentAuto);
    } catch (error) {
        console.log('Error: ', error);
    }
}
async function isValid(id, password) {
    const auto = {
        auto_id: parseInt(id),
        auto_name: null,
        password: password,
        einrichtung: null,
        nummer: null
    }
    const requestBody = JSON.stringify(auto);
    try {
        const response = await fetch("http://localhost:8080/login",{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: requestBody
        });
        const a = await response.json();
        console.log(a);
        if(a.auto_id != 1) {
            window.location.href = "tadya.html";
        } else {
            alert("There is no such a User or Id or Password is wrong!")
        }

    } catch (error) {
        console.log("Error: ", error);
    }
}
async function getCurrent() {
    const url = "http://localhost:8080/login/get";
try {
    const response = await fetch(url);
    currentA = await response.json();
    return currentA;
    console.log(currentA);
} catch (error) {
    console.log('Error: ', error);
}
}

async function getBuilding(osm_id) {
    try {
        const response = await fetch("http://localhost:8080/map/get/" + osm_id);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log('Error: ', error);
    }
}
async function getAutoWithId(osm_id) {
    try {
        const response = await fetch("http://localhost:8080/login/get/" + osm_id);
        const data = await response.json();
        return data;
    } catch (error) {
        console.log('Error: ', error);
    }
}

//
function showBericht(bericht, currentBuilding, currentAuto) {
    console.log(currentAuto);

    if(bericht.bericht_id != 0) {
        document.getElementById("destruction").innerHTML = "Zerstörungsgrad: " + bericht.zustand;
        document.getElementById("report-time").innerHTML = "Meldung Zeit: " + currentBuilding.mel_zeit;
        document.getElementById("alert-time").innerHTML = "Bericht Zeit: " + bericht.bericht_zeit;
        document.getElementById("deaths").innerHTML = "Tote: " + bericht.tote;
        document.getElementById("injured").innerHTML = "Verletzte: " + bericht.verletzte;
        document.getElementById("authorized").innerHTML = "Autorisierte: " + currentAuto.auto_name;
        document.getElementById("facility").innerHTML = "Einrichtung: " + currentAuto.einrichtung;
        document.getElementById("number").innerHTML = "Nummer: " + "+90" + currentAuto.nummer;
    } else {
        document.getElementById("destruction").innerHTML = "Zerstörungsgrad: "
        document.getElementById("report-time").innerHTML = "Meldung Zeit: "
        document.getElementById("alert-time").innerHTML = "Bericht Zeit: "
        document.getElementById("deaths").innerHTML = "Tote: " 
        document.getElementById("injured").innerHTML = "Verletzte: "
        document.getElementById("authorized").innerHTML = "Autorisierte: "
        document.getElementById("facility").innerHTML = "Einrichtung: "
        document.getElementById("number").innerHTML = "Nummer: "
    }
}

function buttonClickHandler(event) {
    clicked = event.target.innerText;
}

function deleteRed() {
    polygons.forEach(polygon => {
        polygon.setOptions({
            strokeColor: 'blue',
            fillColor: Microsoft.Maps.Color.fromHex('#FFFFFF')
        })
    });
}

function generateRandomNumber() {
    const min = 100000000; // Minimum 10-digit number (inclusive)
    const max = 999999999; // Maximum 10-digit number (inclusive)
  
    const randomNumber = Math.floor(Math.random() * (max - min + 1)) + min;
  
    return randomNumber;
  }

  class MyError extends Error {
    constructor(message) {
      super(message);
    }
}