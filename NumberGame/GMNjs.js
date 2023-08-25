let btn = document.getElementById('btn');
let output = document.getElementById('outputtext'); 
let number = [Math.round(Math.random() * 20)]

btn.addEventListener('click', function(){
    let input = Number(document.getElementById('userInput').value);

    if (input == number){
        output.innerHTML = `You guessed correct, your number was ${number} :)`
        document.body.style.backgroundImage = "url('celebration.png')"
    } else if (input < number){
        output.innerHTML = `Too low. Guess a little higher.`
    }
    else if (input > number){
        output.innerHTML = `Too high. Try a little lower.`
    }
    else{
        output.innerHTML = 'Not a Number!'
    }
});
