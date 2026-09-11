const sw = document.getElementById('beerSwitch');
const label = document.getElementById('beerLabel');

function setState(state){
    sw.dataset.state = state;
    sw.setAttribute('aria-checked', state === 'full' ? 'true' : 'false');
    label.textContent = state === 'full' ? 'Drukregler: Til' : 'Drukregler: Fra';
    // Regelsæt erstattes med drukregler
    if (state === 'full') {
        document.querySelector(".regelSæt").textContent = "Indsæt drukregler her - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam posuere ipsum vel tortor porttitor, sed\n" +
            "                maximus nunc accumsan.\n" +
            "                Nullam ac odio a urna sollicitudin vestibulum. Vivamus dignissim lacinia leo, nec dictum nisl sodales\n" +
            "                ac.\n" +
            "                Phasellus ut lorem in turpis congue pellentesque.";
    } else {
        document.querySelector(".regelSæt").textContent = "Indsæt normale regler her - Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam posuere ipsum vel tortor porttitor, sed\n" +
            "                maximus nunc accumsan.\n" +
            "                Nullam ac odio a urna sollicitudin vestibulum. Vivamus dignissim lacinia leo, nec dictum nisl sodales\n" +
            "                ac.\n" +
            "                Phasellus ut lorem in turpis congue pellentesque.";
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