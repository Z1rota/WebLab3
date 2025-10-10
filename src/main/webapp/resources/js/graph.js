
const canvas = document.getElementById('board');
const ctx = canvas.getContext('2d');


const scale = 45;
const originX = canvas.width / 2;
const originY = canvas.height / 2;


function getSelectedRs() {
    const R_VALUES = [1, 1.5, 2, 2.5, 3];
    const checkboxes = document.querySelectorAll('#controls form input[type="checkbox"]');
    const selectedRs = [];

    checkboxes.forEach((checkbox, index) => {
        if (checkbox.checked && R_VALUES[index] !== undefined) {
            selectedRs.push(R_VALUES[index]);
        }
    });
    return selectedRs;
}

function convertCanvasToSystem(xPixel, yPixel) {
    const x = (xPixel - originX) / scale;
    const y = (originY - yPixel) / scale;
    return { x, y };
}


function redrawCanvas() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    drawAxes();

    const selectedRs = getSelectedRs();
    const colors = ['rgba(25, 118, 210, 0.5)', 'rgba(210, 25, 118, 0.5)', 'rgba(118, 210, 25, 0.5)', 'rgba(210, 150, 25, 0.5)', 'rgba(150, 25, 210, 0.5)'];

    selectedRs.forEach((r, index) => {
        const color = colors[index % colors.length];
        drawShape(r, color);
    });
}


function drawShape(r, color) {
    ctx.fillStyle = color;
    ctx.strokeStyle = '#000';
    ctx.lineWidth = 1;

   //окружность
    ctx.beginPath();
    ctx.moveTo(originX, originY);
    ctx.lineTo(originX + (r / 2) * scale, originY);
    ctx.arc(originX, originY, (r / 2) * scale, 0, -Math.PI / 2, true);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    // треугольник
    ctx.beginPath();
    ctx.moveTo(originX, originY);
    ctx.lineTo(originX, originY - r * scale);
    ctx.lineTo(originX - r * scale, originY);
    ctx.closePath();
    ctx.fill();
    ctx.stroke();

    // прямоугольник
    ctx.beginPath();
    ctx.rect(originX - r * scale, originY, r * scale, (r / 2) * scale);
    ctx.fill();
    ctx.stroke();
}



//сОСИ
function drawAxes() {
    ctx.beginPath();
    ctx.strokeStyle = '#333';
    ctx.lineWidth = 1.5;


    ctx.moveTo(0, originY);
    ctx.lineTo(canvas.width, originY);
    ctx.moveTo(canvas.width - 10, originY - 5);
    ctx.lineTo(canvas.width, originY);
    ctx.lineTo(canvas.width - 10, originY + 5);


    ctx.moveTo(originX, canvas.height);
    ctx.lineTo(originX, 0);
    ctx.moveTo(originX - 5, 10);
    ctx.lineTo(originX, 0);
    ctx.lineTo(originX + 5, 10);

    ctx.stroke();

    ctx.fillStyle = '#333';
    ctx.font = '12px Arial';
    const MAX_LABEL = 5;

    for (let i = -MAX_LABEL; i <= MAX_LABEL; i++) {
        if (i === 0) continue;
        const xPixel = originX + i * scale;
        ctx.fillText(i.toString(), xPixel - 5, originY + 15);
        ctx.beginPath();
        ctx.moveTo(xPixel, originY - 4);
        ctx.lineTo(xPixel, originY + 4);
        ctx.stroke();

        const yPixel = originY - i * scale;
        ctx.fillText(i.toString(), originX + 10, yPixel + 5);
        ctx.beginPath();
        ctx.moveTo(originX - 4, yPixel);
        ctx.lineTo(originX + 4, yPixel);
        ctx.stroke();
    }
    ctx.fillText('0', originX + 5, originY + 15);
}


canvas.addEventListener('click', function (e) {
    const rect = canvas.getBoundingClientRect();
    const xPixel = e.clientX - rect.left;
    const yPixel = e.clientY - rect.top;

    const selectedRs = getSelectedRs();


    if (selectedRs.length === 0) {
        showNotification('Выберите хотя бы одно значение R.',true);
        return;
    }

    const { x, y } = convertCanvasToSystem(xPixel, yPixel);


    const clampedX = Math.max(-5, Math.min(5, x));


    const hiddenInput = document.querySelector("[id$=':sliderInput']");
    if (!hiddenInput) {
        console.error("Критическая ошибка: не найдено скрытое поле sliderInput!");
        return;
    }


    if (typeof PF('xSliderWidget') !== 'undefined') {
        PF('xSliderWidget').setValue(clampedX);
    }


    hiddenInput.value = clampedX;

    hiddenInput.dispatchEvent(new Event('change', { bubbles: true }));



    const displayElement = document.querySelector("[id$=':display']");
    if (displayElement) {

        displayElement.textContent = clampedX.toFixed(3);
    }


    const yInputField = document.querySelector("input[id$=':YChange']");
    if (yInputField) {
        yInputField.value = y.toFixed(2);
    }

    // После этого можно отправлять форму или вызывать p:remoteCommand
    // для отправки данных (X, Y и массив R) на сервер.
});


document.querySelectorAll('#controls form input[type="checkbox"]').forEach(checkbox => {
    checkbox.addEventListener('change', redrawCanvas);
});

window.addEventListener('load', redrawCanvas);