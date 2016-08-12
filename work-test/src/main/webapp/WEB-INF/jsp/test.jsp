<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/7/20
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Title</title>
    <script type="application/javascript" src="../../asserts/js/jquery.js"></script>
</head>
<body>
<input type="button" id="add" value="添加"/>
<input type="button" id="json" value="json"/>
</body>
<script>
    $("#add").click(function () {
        $.ajax({
            type: "POST",
            url: "save.htm",
            data: {"sn":"123","phone":"1373548123"},
            success: function (data) {
                console.log(data);
            }
        });
    });

    $("#json").click(function () {
        var data={"sn":"123","phone":"1373548123","name":"wen"};
        $.ajax({
            type: "POST",
            url: "json.htm",
            contentType:"application/json",
            data: data,
            success: function (data) {
                console.log(data);
            }
        });
    });
</script>
</html>
