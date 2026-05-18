// crear.js — Vista POST /api/citas

document.addEventListener('DOMContentLoaded', function () {

  const formulario = document.getElementById('form-crear-cita');

  if (!formulario) return;

  formulario.addEventListener('submit', function (e) {
    e.preventDefault();

    // Capturar valores de la cita
    const nombrePaciente = document.getElementById('nombrePaciente').value.trim();
    const nombreDoctor = document.getElementById('nombreDoctor').value.trim();
    const especialidad = document.getElementById('especialidad').value;
    const fechaHora = document.getElementById('fechaHora').value;
    const estado = document.getElementById('estado').value;

    // Validación básica
    if (
      !nombrePaciente ||
      !nombreDoctor ||
      !especialidad ||
      !fechaHora ||
      !estado
    ) {
      mostrarToast('Completa todos los campos obligatorios', 'error');
      return;
    }

    // Crear nueva cita
    const nuevaCita = {
      id: citas.length > 0 ? citas[citas.length - 1].id + 1 : 1,
      nombrePaciente,
      nombreDoctor,
      especialidad,
      fechaHora,
      estado
    };

    // Agregar al arreglo mock
    citas.push(nuevaCita);

    // Re-renderizar tabla
    renderTabla(citas);

    // Mostrar mensaje éxito
    mostrarToast('Cita guardada correctamente', 'success');

    // Limpiar formulario
    formulario.reset();
  });

});