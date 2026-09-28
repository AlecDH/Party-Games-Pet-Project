var selection = "Meyer";

let regler = "regler";
let drukregler = "drukregler";
const reglerTekst = document.querySelector(".regelSæt")


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
        reglerTekst.innerHTML = drukregler;
    } else {
        reglerTekst.innerHTML = regler;
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










