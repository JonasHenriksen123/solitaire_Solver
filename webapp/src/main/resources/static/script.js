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
    //fetch('http://localhost:8000/')
    //    .then(response => response.json())
    //    .then(data => console.log(data));
    var canvas = document.getElementById("canvas");
    var video = document.getElementById("video");

    canvas.getContext('2d').drawImage(video,0,0);
    var imageBase64 = canvas.toDataURL("image/jpeg")
    console.log(imageBase64)

    fetch("http://localhost:8000",{
        method: 'post',
        header:{'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
    },
        body: JSON.stringify({a: 7, str: imageBase64})
    }).then(res => res.json())
        .then(res => console.log(res));

    /*
    var payload = {imageBase64};
    var data = new FormData();
    data.append("json", JSON.stringify(payload));

    fetch("http://localhost:8000/",{
        method: "POST",
        body: data
    })
        .then(function (res){return res.json()})
        .then(function (data){alert(JSON.stringify(data))})
        .then(data => console.log(data))

     */
}



