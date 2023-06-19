async function isValid(id, password) {
    const auto = {
        auto_id: parseInt(id),
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