<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>

<h3 class="form-title">用户登录</h3>
<form action="/user/login" method="post">
    <%
        String error = (String) request.getAttribute("error");
        if (error!=null){
    %>
        <div class="alert alert-error input-medium controls">
            <button class="close" data-dismiss="alert">×</button>${error}
        </div>
    <%
        }
    %>
    <div class="form-group">
        <label for="name">用户名</label>
        <input type="text" class="form-control required" id="name" name="username" placeholder="请输入用户名">
    </div>
    <div class="form-group">
        <label for="name">密码</label>
        <input type="text" class="form-control required" id="password" name="password" placeholder="请输入密码">
    </div>
    <button type="submit" class="btn btn-default">登录</button>
</form>


</body>
</html>
