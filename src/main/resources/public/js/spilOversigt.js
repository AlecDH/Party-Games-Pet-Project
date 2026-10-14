const modal = document.getElementById("game-modal");
const alleSpil = document.querySelectorAll(".spil");

function formatRange(min, max, unit) {
    return min === max ? `${min} ${unit}` : `${min}–${max} ${unit}`;
}

alleSpil.forEach(spil => {
    spil.addEventListener("click", async () => {
        const id = spil.dataset.id;
        try {
            const res = await fetch(`/games/${spil.dataset.id}`);

            if (!res.ok) throw new Error("Game not found");
            const game = await res.json();

            document.getElementById("modal-name").textContent = game.name;
            document.getElementById("modal-intro").textContent = game.introText;
            document.getElementById("modal-players").textContent =
                formatRange(game.minPlayers, game.maxPlayers, "spillere");
            document.getElementById("modal-duration").textContent =
                formatRange(game.minDuration, game.maxDuration, "minutter");
            document.getElementById("modal-readmore").href = `/games/${spil.dataset.id}/info`;
            modal.showModal();
        } catch (err) {
            console.error(err);
        }
    });
})

document.getElementById("modal-close").addEventListener("click", () => modal.close());
modal.addEventListener("click", e => { if (e.target === modal) modal.close(); });