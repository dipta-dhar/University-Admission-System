//getting all input text objects
var username = document.forms["vform"]["username"];
var usernameo = document.forms["vform"]["usernameo"];
var password = document.forms["vform"]["password"];
var sscRoll = document.forms["vform"]["sscRoll"];

//getting all error display objects
var nameError = document.getElementById("nameError");
var nameErroro = document.getElementById("nameErroro");
var passwordError = document.getElementById("passwordError");
var sscRollError = document.getElementById("sscRollError");

//setting all event listeners
username.addEventListener("blur", nameVerify, true);
usernameo.addEventListener("blur", nameVerifyo, true);
password.addEventListener("blur", passwordVerify, true);
sscRoll.addEventListener("blur", sscRollVerify, true);
//validation function
function Validate(){
    //username validation
    if(username.value == ""){
        username.style.border = "1px solid red";
        nameError.textContent = "Username is required!";
        username.focus();
        return false;
    }
    if(usernameo.value == ""){
        usernameo.style.border = "1px solid red";
        nameErroro.textContent = "Username is required!";
        usernameo.focus();
        return false;
    }
    //password validation
    if(password.value == ""){
        password.style.border = "1px solid red";
        passwordError.textContent = "Password is required!";
        password.focus();
        return false;
    }
    if(sscRoll.value == ""){
        sscRoll.style.border = "1px solid red";
        sscRollError.textContent = "SSC Roll is required!";
        sscRoll.focus();
        return false;
    }
}
//event handler function
function nameVerify() {
    if(username.value!=""){
        username.style.border = "1px solid #5E6E66";
        nameError.innerHTML = "";
        return true;
    }
}
function nameVerifyo() {
    if(usernameo.value!=""){
        usernameo.style.border = "1px solid #5E6E66";
        nameErroro.innerHTML = "";
        return true;
    }
}
function passwordVerify() {
    if(password.value!=""){
        password.style.border = "1px solid #5E6E66";
        passwordError.innerHTML = "";
        return true;
    }
}

function sscRollVerify() {
    if(sscRoll.value!=""){
        sscRoll.style.border = "1px solid #5E6E66";
        sscRollError.innerHTML = "";
        return true;
    }
}
