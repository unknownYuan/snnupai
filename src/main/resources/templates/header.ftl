<header>
    <nav>
        <div class="logo">
            <a href="">
                <img src="images/snnupai.png"/>
            </a>
        </div>

        <div class="menu">
            <div class="menu-box">
                <ul class="menu-list-content">
                    <li class="menu-list-box"><a href="/index">首页</a></li>
                    <li class="menu-list-box"><a href="/trade_ftl?pagenum=1">跳蚤市场</a></li>
                    <li class="menu-list-box"><a href="/love_list?pagenum=1">表白墙</a></li>
                    <li class="menu-list-box"><a href="">论坛</a></li>
                    <li class="menu-list-box"><a href="">老乡会</a></li>
                    <li class="menu-list-box"><a href="">新生指南</a></li>
                    <li class="menu-list-box"><a href="">师大印象</a></li>
                    <li class="menu-list-box"><a href="">就业指南</a></li>
                    <li class="menu-list-box"><a href="">社团</a></li>
                </ul>
            </div>
        </div>

        <div class="user-center user-center-unlogin">

                <#if user??>
                    <ul class="login">
                        <li><a href="snnupai_profile?offset=1&limit=10">${user.nickName!''}</a><i></i></li>
                        <li><a href="/logout">退出</a></li>
                    </ul>

                <#else>
                    <ul class="unlogin">
                        <li><a href="/reglogin">登录</a><i></i></li>
                        <li><a href="/reglogin">注册</a></li>
                    </ul>
                </#if>
        </div>
    </nav>

</header>

