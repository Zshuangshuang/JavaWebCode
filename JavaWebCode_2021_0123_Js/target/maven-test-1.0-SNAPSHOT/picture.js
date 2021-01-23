$(function () {
    setInterval(function () {
        let s1 = $(".s1");
        let s2 = $(".s2");
        s1.removeClass("s1").addClass("s2");
        s2.removeClass("s2").addClass("s1");
    },1000);
});