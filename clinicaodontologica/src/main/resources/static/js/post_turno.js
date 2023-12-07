window.addEventListener('load', function () {


    const formulario = document.querySelector('#add_new_turno');
    const fechaYHora = document.querySelector('#fechaTurno');
    const odontologoId = document.querySelector('#odontologoId');
    const pacienteId = document.querySelector( '#pacienteId');
    const url = "http://localhost:8080";

    formulario.addEventListener('submit', function (event) {
        event.preventDefault()

        const payload = {
            paciente: pacienteId.value,
            odontologo: odontologoId.value,
            fechaYHora: fechaYHora.value.replace('T',' ')+":00",
        };

        console.log(payload);

        const settings = {
            method: 'POST',
            body: JSON.stringify(payload),
            headers: {
                'Content-Type': 'application/json',
            }
        }

        console.log("Lanzar la consulta a la API...");


        fetch(`${url}/turnos/registrar`, settings)
            .then(response => response.json())
            .then(data => {

                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong></strong> Turno agregado </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();
                 console.log(JSON.stringify(payload));

            })
            .catch(error => {

                if (error.response && error.response.status === 400) {

                    const errorData = error.response.data;
                    if (errorData.odontologoId) {
                        displayError("odontologoId", errorData.odontologoId);
                    }
                    if (errorData.pacienteId) {
                        displayError("pacienteId", errorData.pacienteId);
                    }
                } else {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        '<strong> Error intente nuevamente</strong> </div>';

                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";

                    resetUploadForm();
                }
    });
});


    function resetUploadForm(){
        document.querySelector('#fechaTurno').value = "";
        document.querySelector('#pacienteId').value = "";
        document.querySelector('#odontologoId').value = "";

    }

});