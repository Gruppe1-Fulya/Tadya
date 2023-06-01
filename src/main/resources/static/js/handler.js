fetch('http://localhost:8080/map')
.then(response => response.json())
.then(data => console.log(data));