<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="webkit" name="renderer"/>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>隧道设备智能化管理平台</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="${ctx }/webjars/bootstrap/3.4.1/css/bootstrap.min.css"/>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${ctx }/webjars/fontawesome/4.7.0/css/font-awesome.min.css"/>
    <!-- Ionicons -->
    <link rel="stylesheet" href="${ctx }/webjars/ionicons/2.0.0/css/ionicons.min.css"/>

    <!-- Theme style -->
    <link rel="stylesheet" href="${ctx }/webjars/AdminLTE/2.4.10/dist/css/AdminLTE.min.css"/>
    <!-- iCheck -->
    <link rel="stylesheet" href="${ctx }/webjars/AdminLTE/2.4.10/plugins/iCheck/square/blue.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${ctx }/static/js/lib/html5shiv.min.js"></script>
    <script src="${ctx }/static/js/lib/respond.min.js"></script>
    <![endif]-->
    <!--[if lte IE 8]>
    <script src="${ctx }/static/js/lib/pie/PIE.js"></script>
    <![endif]-->
    <link rel="shortcut icon" href="${ctx }/static/images/favicon.ico"/>
    <link rel="bookmark" href="${ctx }/static/images/favicon.ico"/>
    <!-- Google Font -->
    <link rel="stylesheet"
          href="${ctx }/static/js/lib/googleapis.css"/>
    <style>
        .login-box, .register-box {
            width: 400px;
            margin: 7% auto;
        }

        .login-box-body, .register-box-body {
            width: 360px;
            margin: 7% auto;
        }
    </style>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="${ctx }/home"><b>隧道设备智能化管理平台</b></a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">

        <form id="login_form" method="post">
            <div class="form-group has-feedback">
                <input type="text" class="form-control" id="login_username" placeholder="账号" name="userName"/>
                <span class="glyphicon glyphicon-person form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="login_password" class="form-control" placeholder="密码" name="passWord"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <!--<div class="col-xs-3">-->
                <!--&lt;!&ndash;<div class="checkbox icheck">&ndash;&gt;-->
                <!--&lt;!&ndash;<label>&ndash;&gt;-->
                <!--&lt;!&ndash;<input type="checkbox" id="login_checked"/> 记住账号&ndash;&gt;-->
                <!--&lt;!&ndash;</label>&ndash;&gt;-->
                <!--&lt;!&ndash;</div>&ndash;&gt;-->
                <!--</div>-->
                <!-- /.col -->
                <div class="col-xs-6">
                    <input id="referrer" type="hidden" name="referrer" class="form-control"/>
                    <a class="btn btn-primary btn-block btn-flat" onclick="submitData('/segment')">登录</a>
                </div>
                <div class="col-xs-6">
                    <a class="btn btn-primary btn-block btn-flat" onclick="submitData('/inspect')">结构巡检</a>
                </div>
                <!-- /.col -->
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <!--<a href="#">忘记密码</a><br/>-->
                    <p class="login-box-msg" style="display: none;line-height: 40px;color:#FF0000;" id="login_errorMsg">请先登录</p>
                </div>
            </div>
        </form>
        <!--<a href="#">忘记密码</a><br/>-->

    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<script>
    var ctx = "${ctx}";
</script>
<!-- jQuery 3 -->
<script src="${ctx }/webjars/jquery/3.3.1/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="${ctx }/webjars/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="${ctx }/webjars/AdminLTE/2.4.10/plugins/iCheck/icheck.min.js"></script>
<script>
    ;!function () {
        var location = window.location + "";
        var boo = location.indexOf('login');
        if (boo == -1) {
            window.top.location.href = window.location.href;
            window.top.location.reload();
        }
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' /* optional */
        });
        $('#login_password').keyup(function (event) {
            if (event.keyCode == 13) {
                submitData("/segment");
            }
        });
    }();

    function submitData(urlKey) {
        var $loginErrorMsg = $("#login_errorMsg");
        var username = $('#login_username').val().trim();
        var password = $('#login_password').val().trim();
        if (username === '' || password === '') {
            $loginErrorMsg.html();
            $loginErrorMsg.html('用户名或密码不能为空！').show();
            return false;
        }
        //var checked = document.getElementById("login_checked").checked;
        $.ajax({
            type: "post",
            dataType: "json",
            url: ctx + "/submitLogin",
            data: {um: username, pw: password, rememberMe: $("#rememberMe").is(':checked')}, /*, checkBox: checked*/
            success: function (data) {
                if (data.status == 200) {
                    window.location.href = ctx + urlKey;
                } else {
                    $loginErrorMsg.html(data.message);
                    $loginErrorMsg.css("display", "block");
                }
            },
            error: function (data) {
                $loginErrorMsg.html(data.responseJSON.message);
                $loginErrorMsg.css("display", "block");
            }
        })
    }
</script>
</body>
</html>
