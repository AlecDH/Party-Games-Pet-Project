const modal = document.getElementById("modal");
const iframe = document.getElementById("modalIframe");
const closeButton = document.getElementById("closeModalKnap");
const alleSpil = document.querySelectorAll(".spil");

function closeModal() {
    modal.style.display = "none";

    iframe.src = "";
}

function openModal() {
    iframe.src = "spilPopup?game=Snyd";
    modal.style.display = "flex";
}

alleSpil.forEach(spil => { spil.addEventListener("click", openModal)})
modal.addEventListener("click", closeModal);









