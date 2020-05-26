function deleteById(id, title) {
    if (window.confirm(`Confirmar borrado del juego ${title}`)) {
        window.location = `/game/delete/${id}`
    }
}