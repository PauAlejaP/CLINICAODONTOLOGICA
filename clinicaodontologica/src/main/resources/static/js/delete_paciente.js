function deleteBy(id) {
    const url = `/pacientes/eliminar/{id}`;
    const settings = {
        method: 'DELETE',
    };

    fetch(url, settings)
        .then(response => response.json())
        .then(data => {

            console.log(data);
        })
        .catch(error => {

            console.error('Error:', error);
        });

    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
}