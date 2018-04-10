<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>

    <#include "include.ftl">

    <link rel="stylesheet" type="text/css" href="css/header-footer.css">
    <link rel="stylesheet" type="text/css" href="css/personal-center.css">
</head>
<body>
    <div id="body">
        <#include "header.ftl">
        <div class="profile-header">
            <div class="profile-header-bg">

                <div class="profile-header-avatar"> <!--头像-->
                    <a href="#"></a>
                </div>

                <div class="profile-header-ul">
                    <ul>
                        <li class="profile-header-uesrname">
                            <h3>工小匠~</h3>
                        </li>
                        <li class="profile-header-li profile-header-integral">
                            <div>
                                <h4>积分</h4>
                                <h4>111</h4>
                            </div>
                        </li>
                        <li class="profile-header-li profile-header-popularity">
                            <div>
                                <h4>人气</h4>
                                <h4>50</h4>
                            </div>
                        </li>
                        <li class="profile-header-li profile-header-fans">
                            <div>
                                <h4>粉丝</h4>
                                <h4>520</h4>
                            </div>
                        </li>
                        <li class="profile-header-li profile-header-attention">
                            <div>
                                <h4>关注</h4>
                                <h4>600</h4>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="profile-header-info">
                <div class="profile-header-identity">
                    <h3>管理员</h3>
                </div>
                <div class="profile-header-information">
                    <span class="text-muted">个人描述：</span>
                    <p>啦啦啦德玛西亚！！！我是师大的学生</p>
                </div>
                <div class="profile-header-edit">
                    <button class="btn btn-default" type="button">编辑个人资料</button>
                </div>
            </div>
        </div>

        <div class="profile-body content">
            <div class="row">
                <div class="col-md-8 profile-body-left">
                    <div class="profile-plate">
                        <ul id="profile-plate-ul">
                            <li class="li-one chosen"><a href="javascript:void(0)"><div><h4>动态</h4></div></a></li>
                            <li class="li-two"><a href="javascript:void(0)"><div><h4>私信</h4></div></a></li>
                            <li class="li-three"><a href="javascript:void(0)"><div><h4>收藏</h4></div></a></li>
                        </ul>
                    </div>

                    <div class="profile-content">
                        <!-- 动态信息栏 -->
                        <div class="profile-dynamic profile-universal" style="display: block;">
                            <ul>
                                <!-- li用来重复 -->
                                <#list feeds as feed>

                                <li>
                                    <div>

                                        <h4>${feed.title}</h4>
                                        <p>${feed.date}</p>
                                    </div>
                                </li>
                                </#list>
                            </ul>
                        </div>

                        <!-- 私信信息栏 -->
                        <div class="profile-privateletter profile-universal" style="display: none;">
                            <ul>
                                <!-- li用来重复 -->
                                <li>
                                    <div>
                                        <img src="">
                                        <h4>给我发私信的人</h4>
                                        <data>发私信的时间</data>
                                        <p>私信的内容</p>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <img src="">
                                        <h4>给我发私信的人</h4>
                                        <data>发私信的时间</data>
                                        <p>私信的内容</p>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <img src="">
                                        <h4>给我发私信的人</h4>
                                        <data>发私信的时间</data>
                                        <p>私信的内容</p>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <img src="">
                                        <h4>给我发私信的人</h4>
                                        <data>发私信的时间</data>
                                        <p>私信的内容</p>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <!-- 收藏信息栏 -->
                        <div class="profile-collection profile-universal" style="display: none;">
                            <ul>
                                <!-- li用来重复 -->
                                <li>
                                    <div>
                                        <h4>收藏的帖子</h4>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <h4>收藏的帖子</h4>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <h4>收藏的帖子</h4>
                                    </div>
                                </li>

                                <li>
                                    <div>
                                        <h4>收藏的帖子</h4>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>

                </div>


                <div class="col-md-4 profile-body-right">

                    <div class="like-unlike">
                        <div class="like">
                            <h4>获赞：120</h4>
                        </div>
                        <i></i>
                        <div class="unlike">
                            <h4>被踩：10</h4>

                        </div>
                    </div>

                    <div class="profile-sentence">
                        <p>啦啦啦，这是一句话，很无聊的话~~~</p>
                    </div>
                </div>
            </div>
        </div>

        <button class="btn back_to_top">返回顶部</button>
        <button class="btn snnupai-feedback">意见反馈</button>
        <#include "footer.ftl">
    </div>

    <script type="text/javascript">
        $(document).ready(function(){

            // 点击切换栏目---start
            $(".li-one").click(function(){
                if(!$(".li-one").hasClass("chosen")){
                    $(".li-one").addClass("chosen");
                }
                $(".li-two").removeClass("chosen");
                $(".li-three").removeClass("chosen");

                $(".profile-dynamic").css("display","block");
                $(".profile-privateletter").css("display","none");
                $(".profile-collection").css("display","none");
            });

            $(".li-two").click(function(){
                if(!$(".li-two").hasClass("chosen")){
                    $(".li-two").addClass("chosen");
                }
                $(".li-one").removeClass("chosen");
                $(".li-three").removeClass("chosen");

                $(".profile-dynamic").css("display","none");
                $(".profile-privateletter").css("display","block");
                $(".profile-collection").css("display","none");
            });

            $(".li-three").click(function(){
                if(!$(".li-three").hasClass("chosen")){
                    $(".li-three").addClass("chosen");
                }
                $(".li-one").removeClass("chosen");
                $(".li-two").removeClass("chosen");

                $(".profile-dynamic").css("display","none");
                $(".profile-privateletter").css("display","none");
                $(".profile-collection").css("display","block");
            });

            // 点击切换栏目---end

            // 回到顶部

            var backButton=$('.back_to_top');
            backButton.click(function(){
                $('html,body').animate({
                    scrollTop: 0
                }, 800);
            });

            $(window).on('scroll', function () {/*当滚动条的垂直位置大于浏览器所能看到的页面的那部分的高度时，回到顶部按钮就显示 */
                if ($(window).scrollTop() > $(window).height())
                    backButton.fadeIn();
                else
                    backButton.fadeOut();
            });
            $(window).trigger('scroll');/*触发滚动事件，避免刷新的时候显示回到顶部按钮*/



            var num = 1;
            $(window).scroll(function(){
                var scrollPos = $(this).scrollTop();
                var dbHiht = $(document).height();

                if(dbHiht - scrollPos - $(this).height() < 30) {
                    num++;
                    console.log("需要开始加载");


                    //这里执行滚动条到页面底部时的操作
                    $.ajax({
                        url: "/snnupai_user/feed?offset=" + num + "&limit=10",
                        type: "GET",
                        scriptCharset: 'utf-8',
                        contentType: 'application/json',
                        dataType: 'json',
                        success: function (data) {
//                            eval("data=" + data);
                            //动态
                            if (data) {
                                var html = '';
                                for (var i = 0; i < data.feeds.length; i++) {
                        html += '<li> <div> <img src="">' +
                                '<h4>' + data.feeds[i].userId + '</h4>' +
                                '<data>' + data.feeds[i].data + ' </data>' +
                                '</div> </li>';
                                }
                                console.log(html);
                                $(".profile-dynamic ul").append(html);
                                console.log($(".profile-dynamic ul"));
                            }


                        }
                    })
                }

            });
        });
    </script>
</body>
</html>