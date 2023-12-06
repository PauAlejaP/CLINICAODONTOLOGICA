function validarFecha() {
    var fechaInput = document.getElementById('fecha');
    var fecha = fechaInput.value;
    
    // Expresión regular para verificar el formato YYYY-MM-DD
    var regex = /^\d{4}-\d{2}-\d{2}$/;

    if (!regex.test(fecha)) {
        alert('Ingrese una fecha válida en el formato YYYY-MM-DD.');
        fechaInput.value = '';
    }
}

function validarNombre() {
    var nombreInput = document.getElementById('nombre');
    var nombre = nombreInput.value.trim(); // Elimina espacios en blanco al principio y al final

    if (nombre.length < 3) {
        alert('Por favor, el nombre debe tener más de tres caracteres.');
        nombreInput.focus();
        return false;
    }
    return true
}

function validarApellido() {
    var nombreInput = document.getElementById('apellido');
    var nombre = nombreInput.value.trim(); 

    if (apellido.length < 3) {
        alert('Por favor, el apellido debe tener más de tres caracteres.');
        nombreInput.focus();
        return false;
    }
    return true
}

function validarDni() {
    var nombreInput = document.getElementById('dni');
    var nombre = nombreInput.value.trim(); 

    if (apellido.length < 6) {
        alert('Por favor, el dni debe tener más de seis caracteres.');
        nombreInput.focus();
        return false;
    }
    return true
}

function validarDomicilio() {
    var nombreInput = document.getElementById('domicilio');
    var nombre = nombreInput.value.trim(); 

    if (apellido.length < 10) {
        alert('Debe ser un domicilio valido');
        nombreInput.focus();
        return false;
    }
    return true
}