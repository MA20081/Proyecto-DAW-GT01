// TablaCitas.jsx — Listado GET /api/citas
// Renderiza la tabla de citas y los badges de estado

function formatearFecha(fechaStr) {
  const fecha = new Date(fechaStr);
  const dia  = String(fecha.getDate()).padStart(2, '0');
  const mes  = String(fecha.getMonth() + 1).padStart(2, '0');
  const anio = fecha.getFullYear();
  const hora = String(fecha.getHours()).padStart(2, '0');
  const min  = String(fecha.getMinutes()).padStart(2, '0');
  return `${dia}/${mes}/${anio} ${hora}:${min}`;
}

function BadgeEstado({ estado }) {
  const clases = {
    CONFIRMADA: 'badge-estado badge-confirmada',
    PENDIENTE:  'badge-estado badge-pendiente',
    CANCELADA:  'badge-estado badge-cancelada',
  };
  return <span className={clases[estado] || 'badge-estado badge-pendiente'}>{estado}</span>;
}

function TablaCitas({ citas, onEditar, onEliminar }) {
  return (
    <section id="listado">
      <div className="section-header">
        <h2>Citas médicas registradas</h2>
        <span className="badge-total">
          {citas.length} registro{citas.length !== 1 ? 's' : ''}
        </span>
      </div>

      <div className="table-wrapper">
        <table id="tabla-citas">
          <thead>
            <tr>
              <th>ID</th>
              <th>Paciente</th>
              <th>Doctor</th>
              <th>Especialidad</th>
              <th>Fecha y Hora</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {citas.map(cita => (
              <tr key={cita.id} data-id={cita.id}>
                <td>#{cita.id}</td>
                <td>{cita.nombrePaciente}</td>
                <td>{cita.nombreDoctor}</td>
                <td>{cita.especialidad}</td>
                <td>{formatearFecha(cita.fechaHora)}</td>
                <td><BadgeEstado estado={cita.estado} /></td>
                <td className="acciones">
                  <button className="btn-editar"
                    onClick={() => onEditar(cita.id)}>
                    ✏️ Editar
                  </button>
                  <button className="btn-eliminar-fila"
                    onClick={() => onEliminar(cita.id, cita.nombrePaciente)}>
                    🗑️ Eliminar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </section>
  );
}

export default TablaCitas;
