<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>陕师派</title>
    <#include "include.ftl">
</head>
<script>
//    for (var i=1;i<3;i++){
//        setTimeout(function () {
//            console.log("ghd "+i+" ghd");
//
//        },0);
//    };
    function Foo() {
        
    }
    Foo.outPut=function () {
        console.log(2)
    }
    Foo.prototype.outPut=function () {
        console.log(3)
    }
console.log("ghd")
    Foo.outPut()
    console.log("ghd")
</script>
<body>
    <div id="body">
        <#include "header.ftl">

        <div class="carousel">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
                        <img src="images/banner1.jpg"/>
                    </div>
                    <div class="swiper-slide">
                        <img src="images/banner2.jpg"/>
                    </div>
                    <div class="swiper-slide">
                        <img src="images/banner3.jpg"/>
                    </div>
                </div>
                <!-- 分页器 -->
                <div class="swiper-pagination"></div>

                <!-- 导航按钮 -->
                <div class="swiper-button-prev"></div>
                <div class="swiper-button-next"></div>

                <!-- 滚动条 -->
                <!-- <div class="swiper-scrollbar"></div> -->
            </div>
        </div>

        <div class="snnupai-content">
            <!-- 门户 -->
            <div class="portal">
                <div class="forum-title">
                    <h3>师大门户</h3>
                </div>
                <div class="portal-content">
                    <div class="portal-content-row">
                        <span><a href="http://219.244.71.113/login.jsp">选课系统（内网访问）</a></span><i></i>
                        <span><a href="http://sztz.snnu.edu.cn/">大学生素质拓展认证系统</a></span><i></i>
                        <span><a href="http://www.lib.snnu.edu.cn/action.do?webid=w-l-index-m">陕师大图书馆（内网访问）</a></span><i></i>
                        <span><a href="http://opac.snnu.edu.cn:8991/F">馆藏书目查询</a></span><i></i>
                        <span><a href="http://www.lib.snnu.edu.cn/action.do?webid=w-l-showchildmenu&cmid=w-a-dzsjk">电子数据库</a></span><i></i>
                        <span><a href="http://www.lib.snnu.edu.cn/action.do?webid=w-l-zwsjk-v&dbid=55&goback=w-d-zwsjk">随书光盘</a></span><i></i>
                    </div>
                    <div class="portal-content-row">
                        <span><a href="http://www.snnu.edu.cn/jgsz1/jxkydw.htm">教学科研单位</a></span><i></i>
                        <span><a href="http://service.snnu.edu.cn/">后勤服务</a></span><i></i>
                        <span><a href="http://bwg1.snnu.edu.cn/">博物馆</a></span><i></i>
                        <span><a href="http://bb.snnu.edu.cn/">陕西师范大学在线课程平台（bb平台）</a></span><i></i>
                        <span><a href="https://mail.snnu.edu.cn/">师大邮箱</a></span><i></i>
                        <span><a href="https://weibo.com/snnu1944">官方新浪微博</a></span><i></i>
                        <span><a href="http://xgxt.snnu.edu.cn/ssoserver/login.jsp">学工系统</a></span><i></i>
                    </div>
                    <div class="portal-content-row">
                        <span><a href="http://zyfw.snnu.edu.cn/">志愿服务</a></span><i></i>
                        <span><a href="http://202.117.144.241/schoolcar/jj.aspx">陕师大校车时刻表（内网访问）</a></span><i></i>
                        <span><a href="http://www.snnu.edu.cn/xysh/bgdh.htm">校内办公电话查询系统</a></span><i></i>
                        <span><a href="http://tuanwei.snnu.edu.cn/">为学网</a></span><i></i>
                        <span><a href="http://nic.snnu.edu.cn/">网络信息中心</a></span><i></i>
                    </div>
                </div>

            </div>

            <div class="banner-box">
                <img src="images/banner-box1.jpg"/>
            </div>




            <!-- 各个版块 -->
            <div class="snnupai-module section-entrance">
                <div class="forum-title">
                    <h3>版块地图</h3>
                </div>
                <div class="content">
                    <div class="row">
                        <div class="col-md-3">
                            <ul>
                                <li>
                                    <a href="">
                                        <img src="images/impression.jpg"/>
                                    </a>
                                </li>
                            </ul>

                        </div>
                        <div class="col-md-9" >
                            <ul>
                                <li class="one-row">
                                    <a href=""><img src="images/luntan.jpg"/></a>
                                </li>
                                <li class="one-row">
                                    <a href=""><img src="images/biaobai.jpg"/></a>
                                </li>
                                <li class="one-row">
                                    <a href=""><img src="images/laoxiang.jpg"/></a>
                                </li>
                                <li class="two-row">
                                    <a href=""><img src="images/lost.jpg"/></a>
                                </li>
                                <li class="two-row">
                                    <a href=""><img src="images/shichang.jpg"/></a>
                                </li>
                                <li class="two-row">
                                    <a href=""><img src="images/xinsheng.jpg"/></a>
                                </li>
                            </ul>
                        </div>

                    </div>
                </div>


            </div>

            <div class="banner-box">
                <img src="images/banner-box2.jpg"/>
            </div>

            <!-- 专题活动 -->
            <div class="snnupai-module special-events">
                <div class="forum-title">
                    <h3>专题活动</h3>
                </div>
                <div class="content">
                    <div class="row">
                        <div>
                            <ul>
                                <li>
                                    <a href="">
                                        <img src="images/xianshang.jpg"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img src="images/xianxia.jpg"/>
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img src="images/kaoyan.jpg"/>
                                    </a>
                                </li>
                                <li class="last-one">
                                    <a href="">
                                        <img src="images/jiuye.jpg"/>
                                    </a>
                                </li>
                            </ul>

                        </div>
                    </div>

                </div>

            </div>
        </div>

        <#include "footer.ftl">
    </div>

    <script>
        var mySwiper = new Swiper ('.swiper-container', {
            direction: 'horizontal',
            loop: true,

            // 如果需要分页器
            pagination: {
                el: '.swiper-pagination',
            },

            // 如果需要前进后退按钮
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },

            // 如果需要滚动条
            // scrollbar: {
            //   el: '.swiper-scrollbar',
            // },
        })
    </script>
</body>
</html>