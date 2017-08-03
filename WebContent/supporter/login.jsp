<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"><meta name="renderer" content="webkit">

    <title>廖志行博客管理系统 - 登录</title>

    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath }/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/css/style.css?v=2.2.0" rel="stylesheet">

</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div>
            <h3>欢迎使用博客管理系统</h3>

            <form class="m-t" role="form" action="${pageContext.request.contextPath }/admin/login" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" required="" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" required="" name="password">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
            </form>
        </div>
    </div>
    

    <!-- Mainly scripts -->
    <script src="${pageContext.request.contextPath }/js/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.4.0"></script>
</body>

</html>