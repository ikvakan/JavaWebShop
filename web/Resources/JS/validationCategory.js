$(document).ready(function (){
      $('#categoryForm').validate({
        rules: {
            category: {
                required: true
            }
        },
        messages: {
            category: {
                required: "Obavezan unos !"
            }
        }
    });
    
    
});