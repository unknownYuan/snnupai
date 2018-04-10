<!DOCTYPE html>
<html>
<head>
    <title>陕师派-师大学生的生活论坛</title>
    <#include "include.ftl">
    <link href="css/login-reg.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-md-offset-4 panel panel-defult" id="login-panel">
            <div id="login" class="">
                <form action="/login" method="post">
                    <img class="snnupai" src="images/snnupai.png"/>
                    <h5>登录陕师派，体验方便快捷的校园生活</h5>
                    <fieldset>
                        <input type="text" name="userstr" class="form-control" placeholder="手机号/邮箱/昵称">
                        <br>
                        <input type="password" name="password" class="form-control" placeholder="密码">
                        <br>
                        <span class="rememberme">
	        					<input type="checkbox" name="rememberme">&nbsp记住密码
                        </span>
                        <br><br>
                        <button type="submit" name="" class="btn btn-lg">登录</button>
                        <span>没有账号？点击<a id="reg">注册</a></span>
                        <br>
                    </fieldset>
                </form>
            </div>

            <div id="signup" class="hidden">
                <form action="/reg/" method="post">
                    <img class="snnupai" src="images/snnupai.png"/>
                    <h5>注册陕师派，体验方便快捷的校园生活</h5>
                    <fieldset>
                        <input type="text" name="username" class="form-control" placeholder="用户名">
                        <br>
                        <input type="password" name="password" class="form-control" placeholder="密码">
                        <br>
                        <input type="password" name="confirmPassword" class="form-control" placeholder="确认密码">
                        <br>
                        <input type="text" name="phone" class="form-control" placeholder="手机号码">
                        <br>
                        <input type="text" name="email" class="form-control" placeholder="邮箱">
                        <br>
                        <span class="rememberme">
	        					<input type="checkbox" name="rememberme">&nbsp记住密码
                        </span>
                        <button type="submit" name="" class="btn btn-lg">注册</button>
                        <span>已经有账号？点击<a id="log">登录</a></span>
                        <br>
                    </fieldset>
                </form>
            </div>
        </div>



    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        $("#reg").click(function(){
            $("#signup").removeClass();
            $("#login").removeClass();
            $("#login").addClass("hidden");
        })
        $("#log").click(function(){
            $("#signup").removeClass();
            $("#signup").addClass("hidden");
            $("#login").removeClass();
        })
    });
</script>
</body>
</html>