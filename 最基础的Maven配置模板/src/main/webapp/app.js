function load() {
alert('OK');
}
$(function () {
    alert('OK');
    let data = {
        username: "abc",
        password: "123"
    };
    //jquery的ajax方法，以异步方式发起http请求
    //方法的传入参数，是json数据格式
    $.ajax({
        type: "POST",
        url: "some.php",
        contentType: "application/json",//ajax默认请求类型
        data: JSON.stringify(data),//请求数据，如果数据类型是json，需要将json转化为字符串
        success: function (msg) {
            alert("Data Saved: "+msg);
        },error: function (XMLHttpRequest,textStatus,errorThrown) {
                this;
        }
    });
});