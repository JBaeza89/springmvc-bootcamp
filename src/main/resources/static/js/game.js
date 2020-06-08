$(function() {
    const steamId = $("#steamId").html();
    fetch(`/game/steam/details/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            const img = $("<img/>")
            img.addClass("screenshot")
            img.attr("src", res.url);
           $("#screenshot").append(img);
        });

    fetch(`/game/steam/news/${steamId}`)
        .then((res) => res.json())
        .then((res) => {
            const news = $("#news");
            res.forEach((article) => {
                let container = $("<div/>");
                let title = $("<h3/>");
                title.html(`<a href='${article.url}'>${article.title}</a>`);
                container.append(title);
                let body = $("<p/>");
                body.text(article.content);
                container.append(body);
                let footer = $("<h6/>");
                footer.text(article.author);
                container.append(footer);
                news.append(container);
            })
        });
})