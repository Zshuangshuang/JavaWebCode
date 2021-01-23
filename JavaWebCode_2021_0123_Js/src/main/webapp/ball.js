$(function () {
    setInterval(function () {
        let ball = $(".ball");
        let top = parseInt(ball.css("top").slice(0,-2));
        top += 10;
        ball.css("top",top+"px");
    },30);
});