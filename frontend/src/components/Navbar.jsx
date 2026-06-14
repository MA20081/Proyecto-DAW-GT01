// Navbar.jsx — Barra de navegación principal

function Navbar() {
  return (
    <nav className="navbar">
      <span className="logo">🏥 MediAgenda</span>
      <ul className="nav-links">
        <li><a href="#listado">Inicio</a></li>
        <li><a href="#nueva-cita">Nueva Cita</a></li>
      </ul>
    </nav>
  );
}

export default Navbar;
