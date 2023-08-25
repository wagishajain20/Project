function convertTemperature() {
    var inputTemperature = document.getElementById("input").value;
    var unit = document.getElementById("unit").value;
    var resultElement = document.getElementById("result");
    var convertedTemperature;

    if (unit === "celsius") {
        convertedTemperature = (inputTemperature * 9/5) + 32;
        resultElement.textContent = inputTemperature + " °C is " + convertedTemperature + " °F";
    } else if (unit === "fahrenheit") {
        convertedTemperature = (inputTemperature - 32) * 5/9;
        resultElement.textContent = inputTemperature + " °F is " + convertedTemperature + " °C";
    }
}
