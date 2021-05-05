//import Webcam from 'webcam-easy';


function togglePopup(){
    document.getElementById("popup-1").classList.toggle("active");
}

function goToGit(){
    window.open("https://github.com/JonasHenriksen123/solitaire_Solver");
}

function startGame(){
    window.location="camera.html";
}

//const webcamElement = document.getElementById("webcam");
//const canvasElement = document.getElementById("canvas");
//const snapSoundElement = document.getElementById('snapsound');
//const webcam = new Webcam(webcamElement, 'user', canvasElement, snapSoundElement);

/*
webcam.start()
    .then(result =>{
        console.log("webcam started");
    })
    .catch(err =>{
        console.log(err);
    })

 */