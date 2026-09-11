var selection = "Meyer";

/* Loader alle spil, sammenligner selection med navnene og sætter info */
async function loadGames(){
    const res = await fetch("../../data/spil.json");
    const data = await res.json();
    displayInfo(data);
}

let regler = "regler";
let drukregler = "drukregler";

function displayInfo(data){
    const titel = document.querySelector(".titel");
    const antalSpillere = document.querySelector("#antalSpillere");
    const materialer = document.querySelector("#materialer");
    const varighed = document.querySelector("#varighed");
    const rating = document.querySelector("#rating");
    const reglerStart = document.querySelector(".regelSæt")

    for (let game of data){
        if (game.name === selection){
            titel.textContent = game.name;
            antalSpillere.textContent = game.minPlayers + " til " + game.maxPlayers + " spillere";
            materialer.textContent = game.materialsText;
            varighed.textContent = game.minDuration + " til " + game.maxDuration + " minutter";
            drukregler = game.drinkingRules;
            regler = game.rules;
            reglerStart.innerHTML = regler;
        }
    }
}

loadGames();

function loadImage(selection){
    const query = "../assets/" + selection + ".jpg";
    const imgElm = document.querySelector(".spilLogo")
    imgElm.src = query;
}

loadImage(selection);

/* Drukregler-knap */
const sw = document.getElementById('beerSwitch');
const label = document.getElementById('beerLabel');

function setState(state){
    sw.dataset.state = state;
    sw.setAttribute('aria-checked', state === 'full' ? 'true' : 'false');
    label.textContent = state === 'full' ? 'Drukregler: Til' : 'Drukregler: Fra';
    // Regelsæt erstattes med drukregler
    if (state === 'full') {
        document.querySelector(".regelSæt").innerHTML = drukregler;
    } else {
        document.querySelector(".regelSæt").innerHTML = regler;
    }
}

function toggle(){
    setState(sw.dataset.state === 'full' ? 'empty' : 'full');
}

sw.addEventListener('click', toggle);
sw.addEventListener('keydown', (e) => {
    if(e.key === ' ' || e.key === 'Enter'){
        e.preventDefault();
        toggle();
    }
});

setState('empty');










