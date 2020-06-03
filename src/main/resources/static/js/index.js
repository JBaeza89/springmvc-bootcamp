function deleteById(url, title) {
    if (window.confirm(`${confirmDelete} ${title}`)) {
        window.location = url;
    }
}