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
    fetch('http://localhost:8000/')
        .then(response => response.json())
        .then(data => console.log(data));
}



