function deleteById(url, messageConfirm) {
    if (window.confirm(`${messageConfirm} `)) {
        window.location = url;
    }
}