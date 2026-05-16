// listado.js — Vista GET /api/citas

function formatearFecha(fechaStr) {
  const fecha = new Date(fechaStr);
  const dia = fecha.getDate().toString().padStart(2, '0');
  const mes = (fecha.getMonth() + 1).toString().padStart(2, '0');
  const anio = fecha.getFullYear();
  const hora = fecha.getHours().toString().padStart(2, '0');
  const min = fecha.getMinutes().toString().padStart(2, '0');
  return `${dia}/${mes}/${anio} ${hora}:${min}`;
}

function badgeEstado(estado) {
  const clases = {
    CONFIRMADA: 'badge-confirmada',
    PENDIENTE:  'badge-pendiente',
    CANCELADA:  'badge-cancelada'
  };
  const clase = clases[estado] || 'badge-pendiente';
  return `<span class="badge-estado ${clase}">${estado}</span>`;
}

function crearFila(cita) {
  const tr = document.createElement('tr');
  tr.setAttribute('data-id', cita.id);
  tr.innerHTML = `
    <td>#${cita.id}</td>
    <td>${cita.nombrePaciente}</td>
    <td>${cita.nombreDoctor}</td>
    <td>${cita.especialidad}</td>
    <td>${formatearFecha(cita.fechaHora)}</td>
    <td>${badgeEstado(cita.estado)}</td>
    <td class="acciones">
      <button class="btn-editar" onclick="abrirEdicion(${cita.id})">✏️ Editar</button>
      <button class="btn-eliminar-fila" onclick="abrirEliminar(${cita.id}, '${cita.nombrePaciente}')">🗑️ Eliminar</button>
    </td>
  `;
  return tr;
}

function renderTabla(listaCitas) {
  const tbody = document.getElementById('cuerpo-tabla');
  const totalSpan = document.getElementById('total-citas');
  tbody.innerHTML = '';
  listaCitas.forEach(cita => tbody.appendChild(crearFila(cita)));
  totalSpan.textContent = `${listaCitas.length} registro${listaCitas.length !== 1 ? 's' : ''}`;
}

function mostrarToast(mensaje, tipo) {
  const toast = document.getElementById('toast');
  toast.textContent = mensaje;
  toast.className = `toast toast-${tipo}`;
  setTimeout(() => { toast.className = 'toast hidden'; }, 3500);
}

// Función publica para que eliminar.js pueda quitar una fila
function eliminarFilaDeLista(id) {
  const idx = citas.findIndex(c => c.id === id);
  if (idx !== -1) {
    citas.splice(idx, 1);
    renderTabla(citas);
    mostrarToast(`Cita eliminada · 204 No Content`, 'error');
  }
}

// Stubs
function abrirEdicion(id) {
  document.getElementById('modal-editar').classList.remove('hidden');
}

function abrirEliminar(id, nombrePaciente) {
  document.getElementById('texto-confirmar-eliminar').textContent =
    `¿Eliminar la cita de ${nombrePaciente}?`;
  document.getElementById('modal-eliminar').classList.remove('hidden');
  document.getElementById('btn-confirmar-eliminar').onclick = function () {
    eliminarFilaDeLista(id);
    document.getElementById('modal-eliminar').classList.add('hidden');
  };
}

// Cerrar modal editar
document.addEventListener('DOMContentLoaded', function () {
  renderTabla(citas);

  document.getElementById('btn-cerrar-modal').addEventListener('click', function () {
    document.getElementById('modal-editar').classList.add('hidden');
  });

  document.getElementById('btn-cancelar-eliminar').addEventListener('click', function () {
    document.getElementById('modal-eliminar').classList.add('hidden');
  });
});
