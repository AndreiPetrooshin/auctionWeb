function redact(f, s) {
    var email = document.getElementById("profile-" + s);
    email.removeAttribute("disabled");
    var form = document.getElementById("form-" + s);
    var btn = document.createElement('input');
    btn.type = "submit";
    form.appendChild(btn);
    f.style.display = "none";
}

function closeForm(s) {
    document.getElementById(s).style.display = 'none';
    document.getElementById('fade').style.display = 'none';
}

function openForm(s) {
    document.getElementById(s).style.display = 'block';
    document.getElementById('fade').style.display = 'block';
}



function checkPasswords() {
    var pass1 = document.getElementById('password');
    var pass2 = document.getElementById('password_repeat');
    if (pass1.value != pass2.value) {
        document.getElementById("submit_registration").setAttribute("disabled", "")
        document.getElementById("submit_registration").style.backgroundColor = '#868686'
    } else {
        document.getElementById("submit_registration").removeAttribute("disabled");
        document.getElementById("submit_registration").style.backgroundColor = '#4CAF50'
    }

}


