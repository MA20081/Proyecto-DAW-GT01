import axios from 'axios';

const api = axios.create({
  baseURL: '/api',
  headers: { 'Content-Type': 'application/json' },
});

export const getCitas           = ()         => api.get('/citas');
export const getCitaPorId       = (id)       => api.get(`/citas/${id}`);
export const crearCita          = (data)     => api.post('/citas', data);
export const actualizarCita     = (id, data) => api.put(`/citas/${id}`, data);
export const eliminarCita       = (id)       => api.delete(`/citas/${id}`);

export const getPacientes       = ()         => api.get('/pacientes');
export const getDoctores        = ()         => api.get('/doctores');
export const getEspecialidades  = ()         => api.get('/especialidades');
