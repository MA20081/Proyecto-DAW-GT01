// FormNuevaCita.jsx — Formulario POST /api/citas
// Carga pacientes y doctores desde la API y envía una nueva cita

import { useState, useEffect } from 'react';
import { crearCita, getPacientes, getDoctores } from '../services/api';

const ESPECIALIDADES = [
  'Medicina General', 'Pediatría', 'Cardiología',
  'Dermatología', 'Neurología', 'Traumatología',
];

const ESTADO_INICIAL = {
  pacienteId: '',
  doctorId: '',
  especialidad: '',
  fechaHora: '',
  estado: 'PENDIENTE',
};

function FormNuevaCita({ onCitaCreada, onToast }) {
  const [form, setForm]       = useState(ESTADO_INICIAL);
  const [pacientes, setPacientes] = useState([]);
  const [doctores, setDoctores]   = useState([]);
  const [loading, setLoading]     = useState(false);

  useEffect(() => {
    getPacientes().then(r => setPacientes(r.data)).catch(() => {});
    getDoctores().then(r => setDoctores(r.data)).catch(() => {});
  }, []);

  const handleChange = e => {
    setForm(prev => ({ ...prev, [e.target.name]: e.target.value }));
  };

  const handleSubmit = async e => {
    e.preventDefault();
    const { pacienteId, doctorId, especialidad, fechaHora, estado } = form;
    if (!pacienteId || !doctorId || !especialidad || !fechaHora || !estado) {
      onToast('Completa todos los campos obligatorios', 'error');
      return;
    }
    setLoading(true);
    try {
      await crearCita({
        pacienteId:  Number(pacienteId),
        doctorId:    Number(doctorId),
        especialidad,
        fechaHora:   fechaHora + ':00',
        estado,
      });
      onToast('Cita guardada correctamente · 201 Created', 'success');
      setForm(ESTADO_INICIAL);
      onCitaCreada();
    } catch {
      onToast('Error al guardar la cita', 'error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <section id="nueva-cita">
      <div className="section-header">
        <h2>Nueva cita</h2>
        <span className="badge-endpoint">POST /api/citas</span>
      </div>

      <form className="formulario" onSubmit={handleSubmit}>
        <div className="form-grupo">
          <label htmlFor="pacienteId">Paciente</label>
          <select id="pacienteId" name="pacienteId"
            value={form.pacienteId} onChange={handleChange} required>
            <option value="">-- Seleccionar paciente --</option>
            {pacientes.map(p => (
              <option key={p.id} value={p.id}>{p.nombre}</option>
            ))}
          </select>
        </div>

        <div className="form-grupo">
          <label htmlFor="doctorId">Doctor</label>
          <select id="doctorId" name="doctorId"
            value={form.doctorId} onChange={handleChange} required>
            <option value="">-- Seleccionar doctor --</option>
            {doctores.map(d => (
              <option key={d.id} value={d.id}>{d.nombre}</option>
            ))}
          </select>
        </div>

        <div className="form-grupo">
          <label htmlFor="especialidad">Especialidad</label>
          <select id="especialidad" name="especialidad"
            value={form.especialidad} onChange={handleChange} required>
            <option value="">-- Seleccionar --</option>
            {ESPECIALIDADES.map(e => (
              <option key={e} value={e}>{e}</option>
            ))}
          </select>
        </div>

        <div className="form-grupo">
          <label htmlFor="fechaHora">Fecha y hora</label>
          <input type="datetime-local" id="fechaHora" name="fechaHora"
            value={form.fechaHora} onChange={handleChange} required />
        </div>

        <div className="form-grupo">
          <label htmlFor="estado">Estado</label>
          <select id="estado" name="estado"
            value={form.estado} onChange={handleChange} required>
            <option value="PENDIENTE">PENDIENTE</option>
            <option value="CONFIRMADA">CONFIRMADA</option>
            <option value="CANCELADA">CANCELADA</option>
          </select>
        </div>

        <button type="submit" className="btn-guardar" disabled={loading}>
          {loading ? 'Guardando...' : '💾 Guardar cita'}
        </button>
      </form>
    </section>
  );
}

export default FormNuevaCita;
