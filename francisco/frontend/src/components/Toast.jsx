// Toast.jsx — Notificación temporal de éxito o error

import { useEffect } from 'react';

function Toast({ mensaje, tipo, onClose }) {
  useEffect(() => {
    if (!mensaje) return;
    const timer = setTimeout(onClose, 3500);
    return () => clearTimeout(timer);
  }, [mensaje, onClose]);

  if (!mensaje) return null;

  return (
    <div className={`toast toast-${tipo}`}>
      {mensaje}
    </div>
  );
}

export default Toast;
