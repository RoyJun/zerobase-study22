
(function() {
    document.getElementById("x-coordinate").value = 0.0;
    document.getElementById("y-coordinate").value = 0.0;
})();

class MainFunction {
    constructor() {
        this.locationForm = document.querySelector(".location-form")
        this.myLocationButton = document.querySelector("#myLocationButton")
    }

    getMyLocation() {
        const myLocationButton = this.myLocationButton;

        myLocationButton.addEventListener("click", () => {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(pos => {
                    document.getElementById("x-coordinate").value = pos.coords.longitude;
                    document.getElementById("y-coordinate").value = pos.coords.latitude;
                })
            } else {
                alert('Sorry, no position available.');
            }
        });
    }
}

document.addEventListener("DOMContentLoaded", () => {
    new MainFunction().getMyLocation();
})

$("#getNearBtn").click(function () {
    var x = parseInt(document.getElementById("x-coordinate").value);
    var y = parseInt(document.getElementById("y-coordinate").value);

    if (!x || !y || x < 0 || y < 0) {
        alert("x, y 좌표 값을 넣어주세요.");
        return false;
    } else {
        document.getElementById("locationForm").submit();
    }
})

