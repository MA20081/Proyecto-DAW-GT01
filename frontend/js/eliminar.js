document.addEventListener('DOMContentLoaded', () => {

    let filaAEliminar = null;

    const modalEliminar =
        document.getElementById('modal-eliminar');

    const textoEliminar =
        document.getElementById('texto-confirmar-eliminar');

    const btnConfirmar =
        document.getElementById('btn-confirmar-eliminar');

    const btnCancelar =
        document.getElementById('btn-cancelar-eliminar');

    const toast =
        document.getElementById('toast');

    document.addEventListener('click', (e) => {

        if (e.target.classList.contains('btn-eliminar-fila')) {

            filaAEliminar =
                e.target.closest('tr');

            const nombrePaciente =
                filaAEliminar.children[1].textContent;

            textoEliminar.textContent =
                `¿Eliminar esta cita? ${nombrePaciente}`;

            modalEliminar.classList.remove('hidden');
        }

    });

    btnConfirmar.addEventListener('click', () => {

        if (filaAEliminar) {

            filaAEliminar.remove();

            modalEliminar.classList.add('hidden');

            toast.textContent =
                'Cita eliminada · 204 No Content';

            toast.className =
                'toast toast-success';

            setTimeout(() => {

                toast.className =
                    'toast hidden';

            }, 3000);

        }

    });

    btnCancelar.addEventListener('click', () => {

        modalEliminar.classList.add('hidden');

    });

});