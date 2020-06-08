$(function() {
    console.log(urlDetails);
    console.log(urlNews);
    fetch(`${urlDetails}`)
        .then((res) => res.json())
        .then((res) => {
            console.log(res);
            const img = $("<img/>")
            img.addClass("screenshot")
            img.attr("src", res.url);
           $("#screenshot").append(img);
        });

    fetch(`${urlNews}`)
        .then((res) => res.json())
        .then((res) => {
            console.log(res)
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