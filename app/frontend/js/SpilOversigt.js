const modal = document.getElementById("modal");
const iframe = document.getElementById("modalIframe");
const closeButton = document.getElementById("closeModalKnap");
const spil = document.getElementById("spil");

function closeModal() {

    modal.style.display = "none";

    iframe.src = "";
}

function openModal() {
    iframe.src = "spilPopup.html";
    modal.style.display = "flex";
}

modal.addEventListener("click", closeModal);
spil.addEventListener("click", openModal);








