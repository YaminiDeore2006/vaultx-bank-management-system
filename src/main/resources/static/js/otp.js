const otpInputs = document.querySelectorAll(".otp-input");

otpInputs.forEach((input, index) => {

    input.addEventListener("input", () => {

        if(input.value.length === 1 && index < otpInputs.length-1){

            otpInputs[index+1].focus();

        }

    });

    input.addEventListener("keydown", (e)=>{

        if(e.key==="Backspace" && input.value==="" && index>0){

            otpInputs[index-1].focus();

        }

    });

});

let time = 60;

const timer = document.querySelector(".otp-timer");

const countdown = setInterval(()=>{

    let seconds = time < 10 ? "0"+time : time;

    timer.innerHTML = "00 : " + seconds;

    time--;

    if(time < 0){

        clearInterval(countdown);

        timer.innerHTML = "OTP Expired";

    }

},1000);