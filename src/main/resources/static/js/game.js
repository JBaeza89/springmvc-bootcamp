$(function() {
    const steamId = $("#steamId").innerHTML;
    fetch(`/game/steam/details/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            console.log(res);
            const img = $("<img/>")
            img.addClass("screenshot")
            img.attr("src", res.fullPath);
           $("#screenshot").append(img);
        });

    fetch(`/game/steam/news/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            const news = $("#news");
            res.forEach((article) => {
                let container = $("<div/>");
                let title = $("<h3/>");
                title.innerText = article.title;
                container.append(title);
                let body = $("<p/>");
                body.innerText = article.content;
                container.append(body);
                let footer = $("<h6/>");
                footer.innerText = article.author;
                container.append(footer);
                news.append(container);
            })
        });
})