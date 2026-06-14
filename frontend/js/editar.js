/**
 * Edición de Citas
 */

document.addEventListener('DOMContentLoaded', function () {
    const formEditar = document.getElementById('form-editar-cita');
    const mensajeExitoEditar = document.getElementById('mensaje-exito-editar');

    const editIdInput = document.getElementById('edit-id');
    const editPacienteInput = document.getElementById('edit-paciente');
    const editDoctorInput = document.getElementById('edit-doctor');
    const editEspecialidadSelect = document.getElementById('edit-especialidad');
    const editFechaHoraInput = document.getElementById('edit-fecha-hora');
    const editEstadoSelect = document.getElementById('edit-estado');

    // Sobrescribir la función global 
    window.abrirEdicion = function (id) {

        // Buscar la cita en el array 
        const cita = citas.find(c => c.id === id);

        if (!cita) {
            console.error(`No se encontró la cita con ID: ${id}`);
            return;
        }

        if (mensajeExitoEditar) {
            mensajeExitoEditar.classList.add('hidden');
            mensajeExitoEditar.textContent = '';
        }

        // Rellenar campos del formulario
        editIdInput.value = cita.id;
        editPacienteInput.value = cita.nombrePaciente;
        editDoctorInput.value = cita.nombreDoctor;
        editEspecialidadSelect.value = cita.especialidad;
        
        // formato de fecha
        if (cita.fechaHora) {
            editFechaHoraInput.value = cita.fechaHora.substring(0, 16);
        }

        editEstadoSelect.value = cita.estado;

        // Mostrar el modal
<<<<<<< HEAD
=======
        document.getElementById('edit-id-display').textContent = id;
>>>>>>> 50cb44a0122c66f322eb22db2a16150c5ba27950
        document.getElementById('modal-editar').classList.remove('hidden');
    };

    // guardar cambios
    formEditar.addEventListener('submit', function (e) {
        e.preventDefault();

        const idTarget = parseInt(editIdInput.value, 10);
        const index = citas.findIndex(c => c.id === idTarget);

        if (index !== -1) {
            const citaOriginal = citas[index];

            // Verificar si el usuario cambió algún dato
            const hayCambios =
                editPacienteInput.value !== citaOriginal.nombrePaciente ||
                editDoctorInput.value !== citaOriginal.nombreDoctor ||
                editEspecialidadSelect.value !== citaOriginal.especialidad ||
                editFechaHoraInput.value !== citaOriginal.fechaHora.substring(0, 16) ||
                editEstadoSelect.value !== citaOriginal.estado;

            // Si no hay cambios
            if (!hayCambios) {
                console.log("Cerrando modal de edición.");
                document.getElementById('modal-editar').classList.add('hidden');
                return;
            }

            // Actualizar el array 
            citaOriginal.nombrePaciente = editPacienteInput.value;
            citaOriginal.nombreDoctor = editDoctorInput.value;
            citaOriginal.especialidad = editEspecialidadSelect.value;
            citaOriginal.fechaHora = editFechaHoraInput.value;
            citaOriginal.estado = editEstadoSelect.value;

            if (mensajeExitoEditar) {
                mensajeExitoEditar.textContent = "Cita actualizada · 200 OK";
                mensajeExitoEditar.classList.remove('hidden');
            }

            setTimeout(() => {
                document.getElementById('modal-editar').classList.add('hidden');
                if (typeof renderTabla === 'function') {
                    renderTabla(citas);
                }
            }, 1500);
        }
    });
});