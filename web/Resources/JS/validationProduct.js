$(document).ready(function () {
    
    $("#productForm").validate({
        rules: {
            productName: {
                required: true
            },
            productPrice: {
                required: true,
                digits: true
            },
            productDesc: {
                required: true
            }

        },
        messages: {
            productName: {
                required: "Obavezan unos !"
            },
            productPrice: {
                required: "Obavezan unos !",
                digits: "Unesite broj !"

            },
            productDesc: {
                required: "Obavezan unos !"
            }
        }
    });


});