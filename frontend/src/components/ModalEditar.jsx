// ModalEditar.jsx — Modal PUT /api/citas/:id
// Carga los datos actuales de la cita y los muestra precargados para edición

import { useState, useEffect } from 'react';
import { getCitaPorId, actualizarCita, getPacientes, getDoctores } from '../services/api';

const ESPECIALIDADES = [
  'Medicina General', 'Pediatría', 'Cardiología',
  'Dermatología', 'Neurología', 'Traumatología',
];

function ModalEditar({ citaId, onClose, onActualizada, onToast }) {
  const [form, setForm]           = useState(null);
  const [pacientes, setPacientes] = useState([]);
  const [doctores, setDoctores]   = useState([]);
  const [exito, setExito]         = useState(false);
  const [loading, setLoading]     = useState(false);

  useEffect(() => {
    if (!citaId) return;
    setExito(false);
    Promise.all([
      getCitaPorId(citaId),
      getPacientes(),
      getDoctores(),
    ]).then(([citaRes, pacRes, docRes]) => {
      const cita = citaRes.data;
      setPacientes(pacRes.data);
      setDoctores(docRes.data);
      setForm({
        pacienteId:  String(cita.pacienteId),
        doctorId:    String(cita.doctorId),
        especialidad: cita.especialidad,
        fechaHora:   cita.fechaHora ? cita.fechaHora.substring(0, 16) : '',
        estado:      cita.estado,
      });
    }).catch(() => onToast('No se pudo cargar la cita', 'error'));
  }, [citaId]);

  const handleChange = e => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async e => {
    e.preventDefault();
    setLoading(true);
    try {
      await actualizarCita(citaId, {
        pacienteId:  Number(form.pacienteId),
        doctorId:    Number(form.doctorId),
        especialidad: form.especialidad,
        fechaHora:   form.fechaHora + ':00',
        estado:      form.estado,
      });
      setExito(true);
      setTimeout(() => {
        onClose();
        onActualizada();
      }, 1500);
    } catch {
      onToast('Error al actualizar la cita', 'error');
    } finally {
      setLoading(false);
    }
  };

  if (!citaId) return null;

  return (
    <div className="modal">
      <div className="modal-contenido">
        <div className="modal-titulo">
          <h3>Editar cita</h3>
          <span className="badge-endpoint">
            PUT /api/citas/<span>{citaId}</span>
          </span>
        </div>

        {!form ? (
          <p>Cargando...</p>
        ) : (
          <form className="formulario" onSubmit={handleSubmit}>
            <div className="form-grupo">
              <label>Paciente</label>
              <select name="pacienteId" value={form.pacienteId}
                onChange={handleChange} required>
                {pacientes.map(p => (
                  <option key={p.id} value={p.id}>{p.nombre}</option>
                ))}
              </select>
            </div>

            <div className="form-grupo">
              <label>Doctor</label>
              <select name="doctorId" value={form.doctorId}
                onChange={handleChange} required>
                {doctores.map(d => (
                  <option key={d.id} value={d.id}>{d.nombre}</option>
                ))}
              </select>
            </div>

            <div className="form-grupo">
              <label>Especialidad</label>
              <select name="especialidad" value={form.especialidad}
                onChange={handleChange} required>
                {ESPECIALIDADES.map(e => (
                  <option key={e} value={e}>{e}</option>
                ))}
              </select>
            </div>

            <div className="form-grupo">
              <label>Fecha y hora</label>
              <input type="datetime-local" name="fechaHora"
                value={form.fechaHora} onChange={handleChange} required />
            </div>

            <div className="form-grupo">
              <label>Estado</label>
              <select name="estado" value={form.estado}
                onChange={handleChange} required>
                <option value="PENDIENTE">PENDIENTE</option>
                <option value="CONFIRMADA">CONFIRMADA</option>
                <option value="CANCELADA">CANCELADA</option>
              </select>
            </div>

            {exito && (
              <p className="mensaje-exito">Cita actualizada · 200 OK</p>
            )}

            <div className="modal-acciones">
              <button type="submit" className="btn-guardar" disabled={loading}>
                {loading ? 'Guardando...' : '✅ Guardar cambios'}
              </button>
              <button type="button" className="btn-cancelar" onClick={onClose}>
                Cancelar
              </button>
            </div>
          </form>
        )}
      </div>
    </div>
  );
}

export default ModalEditar;
