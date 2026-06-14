// App.jsx — Componente raíz de MediAgenda
import { useState, useEffect, useCallback } from 'react';
import { getCitas } from './services/api';

import Navbar        from './components/Navbar';
import TablaCitas    from './components/TablaCitas';
import FormNuevaCita from './components/FormNuevaCita';
import ModalEditar   from './components/ModalEditar';
import ModalEliminar from './components/ModalEliminar';
import Toast         from './components/Toast';

function App() {
  const [citas, setCitas]               = useState([]);
  const [editarId, setEditarId]         = useState(null);
  const [eliminarInfo, setEliminarInfo] = useState(null);
  const [toast, setToast]               = useState({ mensaje: '', tipo: '' });

  const cargarCitas = useCallback(async () => {
    try {
      const res = await getCitas();
      setCitas(res.data);
    } catch {
      mostrarToast('No se pudo conectar con el servidor', 'error');
    }
  }, []);

  useEffect(() => { cargarCitas(); }, [cargarCitas]);

  const mostrarToast = (mensaje, tipo) => setToast({ mensaje, tipo });
  const cerrarToast  = () => setToast({ mensaje: '', tipo: '' });

  return (
    <>
      <Navbar />
      <main>
        <TablaCitas
          citas={citas}
          onEditar={(id) => setEditarId(id)}
          onEliminar={(id, nombre) => setEliminarInfo({ id, nombre })}
        />
        <Toast mensaje={toast.mensaje} tipo={toast.tipo} onClose={cerrarToast} />
        <FormNuevaCita onCitaCreada={cargarCitas} onToast={mostrarToast} />
      </main>

      {editarId && (
        <ModalEditar
          citaId={editarId}
          onClose={() => setEditarId(null)}
          onActualizada={cargarCitas}
          onToast={mostrarToast}
        />
      )}
      {eliminarInfo && (
        <ModalEliminar
          citaId={eliminarInfo.id}
          nombrePaciente={eliminarInfo.nombre}
          onClose={() => setEliminarInfo(null)}
          onEliminada={cargarCitas}
          onToast={mostrarToast}
        />
      )}
    </>
  );
}

export default App;
