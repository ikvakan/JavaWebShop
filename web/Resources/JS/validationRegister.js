$(document).ready(function () {

    $("#registerForm").validate({
        rules: {

            name: {
                required: true
            },
            surname: {
                required: true
            },
            password: {
                required: true
            },
            email: {
                required: true

            },
            adress: {
                required: true
            },
            city: {
                required: true
            },
            userName: {
                required: true
            }

        },
        messages: {
            name: {
                required: "Ime je obavezno !"
            },
            surname: {
                required: "Prezime je obavezno !"
            },
            password: {
                required: "Lozinka je obavezna !"
            },
            email: {
                required: "Email je obavezan !",
                email: "Unesite ispravan email sa znakom @ ! "
            },
            adress: {
                required: "Adresa je obavezna !"
            },
            city: {
                required: "Grad je obavezan !"
            },
            userName: {
                required: "Korisničko ime je obavezno !"
            }
        }

    });
    
   

});