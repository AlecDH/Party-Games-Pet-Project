(function () {
    const TOTAL = 5;
    const TEETH = 21;          // number of points around the crown cap
    const TOOTH_DEPTH = 0.14;  // how deep the notches cut in, relative to radius
    const R = 30;               // cap radius in local SVG units
    const CENTER = 30;          // svg viewBox center (slightly larger than R for teeth tips)
    const VIEWBOX = 52;

    // Build the jagged crown-cap outline as an SVG path
    function capPath(cx, cy, radius, teeth, depth) {
        const pts = [];
        const step = (Math.PI * 2) / (teeth * 2);
        for (let i = 0; i < teeth * 2; i++) {
            const angle = i * step - Math.PI / 2;
            const r = i % 2 === 0 ? radius : radius * (1 - depth);
            pts.push([cx + r * Math.cos(angle), cy + r * Math.sin(angle)]);
        }
        return "M " + pts.map(p => p[0].toFixed(2) + " " + p[1].toFixed(2)).join(" L ") + " Z";
    }

    // A soft curved highlight suggesting the cap's rounded metal edge
    function shinePath(cx, cy, radius) {
        const r = radius * 0.58;
        const startAngle = Math.PI * 1.05;
        const endAngle = Math.PI * 1.65;
        const sx = cx + r * Math.cos(startAngle);
        const sy = cy + r * Math.sin(startAngle);
        const ex = cx + r * Math.cos(endAngle);
        const ey = cy + r * Math.sin(endAngle);
        return `M ${sx.toFixed(2)} ${sy.toFixed(2)} A ${r.toFixed(2)} ${r.toFixed(2)} 0 0 1 ${ex.toFixed(2)} ${ey.toFixed(2)}`;
    }

    // A tiny glint on every tooth tip, like light catching each ridge
    function teethShinePath(cx, cy, radius, teeth, depth) {
        const step = (Math.PI * 2) / (teeth * 2);
        const rInner = radius * (1 - depth * 0.75);
        const rOuter = radius * (1 - depth * 0.25);
        const segments = [];
        for (let i = 0; i < teeth * 2; i += 2) {
            const angle = i * step - Math.PI / 2;
            const x1 = cx + rInner * Math.cos(angle);
            const y1 = cy + rInner * Math.sin(angle);
            const x2 = cx + rOuter * Math.cos(angle);
            const y2 = cy + rOuter * Math.sin(angle);
            segments.push(`M ${x1.toFixed(2)} ${y1.toFixed(2)} L ${x2.toFixed(2)} ${y2.toFixed(2)}`);
        }
        return segments.join(" ");
    }

    const outline = capPath(CENTER, CENTER, R, TEETH, TOOTH_DEPTH);
    const shine = shinePath(CENTER, CENTER, R);
    const teethShine = teethShinePath(CENTER, CENTER, R, TEETH, TOOTH_DEPTH);

    const row = document.getElementById("capRow");
    const status = document.getElementById("status");

    let selected = 0;   // committed rating
    let preview = 0;    // hover preview

    const buttons = [];

    for (let i = 1; i <= TOTAL; i++) {
        const btn = document.createElement("button");
        btn.type = "button";
        btn.className = "cap-btn";
        btn.setAttribute("role", "radio");
        btn.setAttribute("aria-label", i + " out of " + TOTAL);
        btn.setAttribute("aria-checked", "false");
        btn.dataset.value = i;

        btn.innerHTML =
            '<svg viewBox="0 0 ' + VIEWBOX + ' ' + VIEWBOX + '" xmlns="http://www.w3.org/2000/svg">' +
            '<path class="cap-body" d="' + outline + '"></path>' +
            '<path class="cap-tooth-shine" stroke-width="0.9" d="' + teethShine + '"></path>' +
            '<path class="cap-shine" stroke-width="1.6" d="' + shine + '"></path>' +
            '</svg>';

        btn.addEventListener("mouseenter", () => render(i));
        btn.addEventListener("focus", () => render(i));
        btn.addEventListener("mouseleave", () => render(selected));
        btn.addEventListener("blur", () => render(selected));
        btn.addEventListener("click", () => {
            selected = i;
            render(selected);
            updateStatus();
        });

        row.appendChild(btn);
        buttons.push(btn);
    }

    // keyboard support: arrow keys move the rating
    row.addEventListener("keydown", (e) => {
        if (e.key === "ArrowRight" || e.key === "ArrowUp") {
            e.preventDefault();
            selected = Math.min(TOTAL, (selected || 0) + 1);
            render(selected);
            updateStatus();
            buttons[selected - 1].focus();
        } else if (e.key === "ArrowLeft" || e.key === "ArrowDown") {
            e.preventDefault();
            selected = Math.max(1, (selected || 1) - 1);
            render(selected);
            updateStatus();
            buttons[selected - 1].focus();
        }
    });

    function render(activeCount) {
        buttons.forEach((btn, idx) => {
            const filled = idx < activeCount;
            const body = btn.querySelector(".cap-body");
            body.style.fill = filled ? "var(--highlight)" : "var(--unfilled)";
            btn.setAttribute("aria-checked", (idx + 1 === selected) ? "true" : "false");
            btn.tabIndex = (idx + 1 === (selected || 1)) ? 0 : -1;
        });
    }

    function updateStatus() {
        status.innerHTML = selected
            ? "Din bedømmelse: <strong>" + selected + " / " + TOTAL + "</strong>"
            : "";
    }

    render(0);
})();