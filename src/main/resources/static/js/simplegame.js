addEventListener("load", () => {
    const steamId = document.getElementById("steamId").innerHTML;
    fetch(`/game/steam/details/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            const img = document.createElement("img");
            img.setAttribute("src", res.fullPath);
            img.setAttribute("class", "screenshot");
            document.getElementById("screenshot").append(img);
        });

    fetch(`/game/steam/news/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            const news = document.getElementById("news");
            res.forEach((article) => {
                let container = document.createElement("div");
                let title = document.createElement("h3");
                title.innerText = article.title;
                container.append(title);
                let body = document.createElement("p");
                body.innerText = article.content;
                container.append(body);
                let footer = document.createElement("h6");
                footer.innerText = article.author;
                container.append(footer);
                news.append(container);
            })
        });
})