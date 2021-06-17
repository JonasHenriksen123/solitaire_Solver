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
    var imageBase64 = canvas.toDataURL("image/jpeg")
    console.log(imageBase64)

    fetch("http://localhost:8000/", {
        method: "POST",
        headers: {
        //    'Accept': 'application/json, text/plain, */*',
            "Content-Type": "application/json"
        },
        //body: {'image': imageBase64},
        body: {"image": imageBase64}
    }).then(function (response) {
            return response.json();
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



