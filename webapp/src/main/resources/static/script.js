function togglePopup(){
    document.getElementById("popup-1").classList.toggle("active");
}

function goToGit(){
    window.open("https://github.com/JonasHenriksen123/solitaire_Solver");
}

function startGame(){
    window.location="camera.html";
}

function userAction() {
    var canvas = document.getElementById("canvas");
    var video = document.getElementById("video");

    canvas.getContext('2d').drawImage(video,0,0);
    var imageBase64 = canvas.toDataURL("image/jpeg").split(';base64,')[1]
    console.log(imageBase64)
    //imageBase64 = imageBase64.substring(21)
    //imageBase64[0] = 'b'
    //imageBase64[1] = '\''
    const data_dict = {
        "image": imageBase64
    }

    fetch("http://localhost:8000/", {
        method: "POST",
        //headers: {
        //    "Content-Type": "application/json"
        //},
        body: JSON.stringify(data_dict),
        mode: "no-cors"
    }).then(function (response) {
        alert(response.json())

        return response
    })
    //    .then(function (data) {
    //})
    //}).then(res => res.json())
    //    .then(res => console.log(res));


    //var payload = {imageBase64};
    //var data = new FormData();
    //data.append("json", JSON.stringify(payload));

    //fetch("http://localhost:8000/",{
    //    method: "POST",
    //    body: data
    //})
    //    .then(function (res){return res.json()})
    //    .then(function (data){alert(JSON.stringify(data))})
    //    .then(data => console.log(data))


}



