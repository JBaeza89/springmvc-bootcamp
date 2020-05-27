function deleteById(url, title) {
    if (window.confirm(`Confirmar borrado del juego ${title}`)) {
        window.location = url;
    }
}