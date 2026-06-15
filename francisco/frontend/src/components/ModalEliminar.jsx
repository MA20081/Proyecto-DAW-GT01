// ModalEliminar.jsx — Confirmación DELETE /api/citas/:id

import { useState } from 'react';
import { eliminarCita } from '../services/api';

function ModalEliminar({ citaId, nombrePaciente, onClose, onEliminada, onToast }) {
  const [loading, setLoading] = useState(false);

  if (!citaId) return null;

  const handleConfirmar = async () => {
    setLoading(true);
    try {
      await eliminarCita(citaId);
      onToast('Cita eliminada · 204 No Content', 'error');
      onClose();
      onEliminada();
    } catch {
      onToast('Error al eliminar la cita', 'error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="modal">
      <div className="modal-contenido">
        <h3 style={{ marginBottom: '0.75rem', color: '#dc2626' }}>
          ⚠️ Confirmar eliminación
        </h3>
        <p>¿Eliminar la cita de <strong>{nombrePaciente}</strong>?</p>
        <div className="modal-acciones">
          <button className="btn-eliminar" onClick={handleConfirmar} disabled={loading}>
            {loading ? 'Eliminando...' : 'Sí, eliminar'}
          </button>
          <button className="btn-cancelar" onClick={onClose}>
            Cancelar
          </button>
        </div>
      </div>
    </div>
  );
}

export default ModalEliminar;
