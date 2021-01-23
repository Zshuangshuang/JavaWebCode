/*
$(function main() {
    $("#content").on("mouseover",function () {
        $("#content").html("双双的爱豆");
    });
    $("#content").on("mouseout",function () {
        $("#content").html("邓庄庄");
    });
});*/
$(function main() {
    let ol = $("#content");
    $("button").on("click",function () {
        $.get("test.txt",function (data) {
            console.log(data);
            ol.append("<li>"+ data +"</li>");
        });
    });

});