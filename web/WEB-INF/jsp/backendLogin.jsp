<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>后台登录</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
</head>

<body class="login">
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form id="backendLoginFrm" method="post" action="/backend/dologin.html">
                    <h1>后台登录</h1>
                    <div id="backendLoginMsg">${msg}</div>
                    <div>
                        <input type="text" id="loginUserCode" name="userCode" class="form-control" placeholder="请输入用户名..." required />
                    </div>
                    <div>
                        <input type="password" id="loginUserPassword" name="userPassword" class="form-control" placeholder="请输入密码..." required />
                    </div>
                    <div>
                        <a id="backendFrmSbmBt" class="btn btn-default submit" href="javascript:void(0);">登录</a>
                        <a class="btn btn-default" href="javascript:void(0);" onclick="history.back(-1);">返回</a>
                    </div>

                    <div class="clearfix"></div>
                    <div class="separator">
                        <div class="clearfix"></div>
                        <br/>
                        <div>
                            <h1><i class="fa fa-paw"></i>APP应用开发平台</h1>
                            <p>©2018 北大青鸟</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#backendFrmSbmBt").click(function () {
            if ($("#loginUserCode").val() == ""){
                $("#loginUserCode").attr('placeholder','用户名不能为空,请输入用户名...');
                $("#loginUserCode").focus();
                return;
            } else if ($("#loginUserPassword").val() == "") {
                $("#loginUserPassword").attr('placeholder','密码不能为空,请输入密码...');
                $("#loginUserPassword").focus();
                return;
            }else {
                $("#backendLoginFrm").submit();
            }

        });
    });
</script>
</body>
</html>
