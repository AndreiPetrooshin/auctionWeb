function redact (f, s) {
    var email = document.getElementById("profile-" + s);
    email.removeAttribute("disabled");
    var form = document.getElementById("form-" + s);
    var btn = document.createElement('input');
    btn.type = "submit";
    form.appendChild(btn);
    f.style.display = "none";
}

function closeForm(s) {
    debugger;
    document.getElementById(s).style.display='none';
    document.getElementById('fade').style.display='none';
}

function openForm(s) {
    debugger;
    document.getElementById(s).style.display='block';
    document.getElementById('fade').style.display='block';
}



