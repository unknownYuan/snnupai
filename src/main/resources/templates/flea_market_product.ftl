<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>跳蚤市场</title>
    <#include "include.ftl">
    <link rel="stylesheet" type="text/css" href="css/header-footer.css">
    <link rel="stylesheet" type="text/css" href="css/snnupai-universal.css">
    <link rel="stylesheet" type="text/css" href="css/flea_market_product.css">
</head>
<body>
<div id="body">
    <#include "header.ftl">
    <div class="flea-market-product-header snnupai-universal-header">
        <div class="lost-and-found-header-bg snnupai-universal-header-bg">
            <div class="lost-and-found-title snnupai-universal-title">
                <h1>跳蚤市场</h1>
            </div>
        </div>
    </div>



    <div class="flea-market-product-body content snnupai-universal-body">
        <div class="row">
            <div class="col-md-5">
                <img src="${ images[0].url }" style="width: 100%"/>
            </div>

            <div class="flea-market-product-info col-md-7">
                <h1>物品信息</h1>
                <p>${ (annonymous==0) ? string("实名发布" , "匿名发布")}</p>
                <p>昵称：${ nickname }</p>
                <p>物品描述：${ trade.content }</p>
                <p>价格：${ trade.price }元</p>
                <p>QQ：${ trade.qq!'' }</p>
                <p>微信：${ trade.weixin!'' }</p>
                <p>发布时间：${ (trade.createdDate?string("yyyy-MM-dd HH:mm:ss")) }</p>
                <div class="collection">
                    <#if status=="unfollow">
                        <button type="button" class=" btn btn-default btn-sm" onclick="addcollection(this, '${ trade.id }')">
                            <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span> <sapn class="shoucang">收藏</sapn>
                        </button>
                    <#else>
                         <button type="button" class="collected btn btn-default btn-sm" onclick="addcollection(this, '${ trade.id }')">
                             <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span> <sapn span class="quxiao">取消</sapn>
                         </button>
                    </#if>




                    <#--<button class="btn btn-default btn-sm">未出售</button><br>-->
                    <#--<button class="btn btn-default btn-sm">已出售</button><br>-->
                    <#--&lt;#&ndash;确认出售成功的反馈&ndash;&gt;-->
                    <#--<div class="alert alert-success" style="width: 150px; display: inline-block;">-->
                        <#--<button type="button" class="close" data-dismiss="alert"-->
                                <#--aria-hidden="true">-->
                            <#--&times;-->
                        <#--</button>-->
                        <#--确认成功-->
                    <#--</div>-->
                    <#--&lt;#&ndash;确认出售失败的反馈&ndash;&gt;-->
                    <#--<div class="alert alert-danger" style="width: 150px; display: inline-block;">-->
                        <#--<button type="button" class="close" data-dismiss="alert"-->
                                <#--aria-hidden="true">-->
                            <#--&times;-->
                        <#--</button>-->
                        <#--确认失败！-->
                    <#--</div>-->

                    <span class="text-muted">(请发布者售出后请及时到个人中心确认出售)</span>
                </div>
                <br><br>
            </div>


            <div class="col-md-12 flea-market-product-content-body">
                <div class="flea-market-product-plate">
                    <ul id="flea-market-product-ul">
                        <li class="li-one chosen"><a href="javascript:void(0)"><div><h4>物品图片</h4></div></a></li>
                        <li class="li-two"><a href="javascript:void(0)"><div><h4>评论</h4></div></a></li>
                    </ul>
                </div>
                <br>
                <div class="flea-market-product-content">

                    <!-- 物品图片栏 -->
                    <div id="category-two" class="flea-market-product-img flea-market-universal" style="display: block;">
                        <ul >
                            <!-- li用来重复 -->
                            <#list images as image>
                                <li>
                                    <div>
                                        <img src="${ image.url }"/>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>

                    <!-- 评论栏 -->
                    <div class="flea-market-product-comment flea-market-universal" style="display: none;">

                        <#--发布评论成功的反馈-->
                        <div class="alert alert-success" id="commentSuccess" style="display: none;">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">
                                &times;
                            </button>
                            成功地评论啦~~
                        </div>

                        <#--发布评论失败的反馈-->
                        <div class="alert alert-danger" id="commentFail" style="display: none;">
                            <button type="button" class="close" data-dismiss="alert"
                                    aria-hidden="true">
                                &times;
                            </button>
                            评论不知道为什么失败了，嘤嘤嘤....
                        </div>

                        <form  id="commentForm"  action="/trade_comment_add" method="post">
                            <div class="form-group">
                                <label for="name">评论区</label>
                                <textarea name="content" class="form-control" rows="3"></textarea>
                            </div>
                            <input name="id" value="${ trade.id }" style="display: none;">
                            <input name="type" value="1" style="display: none;">
                            <div class="form-group">
                                <div class="col-sm-2" style="padding-left: 0;">
                                    <select class="form-control well anonymous" name="annonymous" style="padding:2px;">
                                        <option value="1" >匿名评论</option>
                                        <option value="0" selected>实名评论</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" id="commentButton" class="btn btn-default">提交</button>
                        </form>
                        <#--评论列表-->
                        <#--<ul>-->
                            <#--<#list comments as comment>-->
                                <#--<li>-->
                                    <#--<div>-->
                                        <#--<img src="">-->
                                        <#--<span>${comment.}：</span>-->
                                        <#--<span>好东西！！！</span>-->
                                        <#--<span>2018-4-5 8:00</span>-->
                                        <#--<span><a href="javascript:void(0);" onclick="reply()">回复</a></span>-->
                                    <#--</div>-->
                                <#--</li>-->
                            <#--</#list>-->
                        <#--</ul>-->

                    </div>
                </div>
            </div>

        </div>
    </div>

    <button class="btn back_to_top">返回顶部</button>
    <button class="btn snnupai-feedback">意见反馈</button>

    <#--回复评论的模态框-->
    <#--<div class="modal fade" id="replyComment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">-->
        <#--<div class="modal-dialog modal-sm" role="document">-->
            <#--<div class="modal-content">-->
                <#--<div class="modal-header">-->
                    <#--<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>-->
                <#--</div>-->
                <#--<div class="modal-body">-->
                    <#--<form role="form" >-->
                        <#--<div class="form-group">-->
                            <#--<label for="name">回复：</label>-->
                            <#--<textarea class="form-control" rows="3"></textarea>-->
                        <#--</div>-->
                    <#--</form>-->
                <#--</div>-->
                <#--<div class="modal-footer">-->
                    <#--<button type="submit" class="btn btn-info">确定</button>-->
                    <#--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>-->
                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->

    <#include "footer.ftl">

</div>


<#--切换栏目的JS和回到顶部的JS-->
<script type="text/javascript">
    $(document).ready(function(){

        // 点击切换栏目---start
        $(".li-one").click(function(){
            if(!$(".li-one").hasClass("chosen")){
                $(".li-one").addClass("chosen");
            }
            $(".li-two").removeClass("chosen");

            $(".flea-market-product-img").css("display","block");
            $(".flea-market-product-comment").css("display","none");
        });

        $(".li-two").click(function(){
            if(!$(".li-two").hasClass("chosen")){
                $(".li-two").addClass("chosen");
            }
            $(".li-one").removeClass("chosen");

            $(".flea-market-product-img").css("display","none");
            $(".flea-market-product-comment").css("display","block");
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

    });
</script>

<#--收藏的JS-->
<script>
    function addcollection(obj, id) {

        // 收藏ajax
        $.ajax({
            url: "/trade/follow?id=" + id ,
            type: "post",
            //dataType: "json",
            //data: { },
            scriptCharset: 'utf-8',
            contentType: 'application/json',
            success: function(data) {
                var gly = $(obj).children(".glyphicon");
                //已收藏
                if(data == "1"){
                    if(gly.hasClass("glyphicon-star-empty")){
                        gly.removeClass("glyphicon-star-empty");
                        gly.addClass("glyphicon-star");
                        $(obj).addClass("collected");
                        $(obj).children(".shoucang").html("取消");
                        $(obj).children(".shoucang").attr("class","quxiao");
                    }
                }
                //未收藏
                else if(data == "2") {
                    if (gly.hasClass("glyphicon-star")) {
                        gly.removeClass("glyphicon-star");
                        gly.addClass("glyphicon-star-empty");
                        $(obj).removeClass("collected");
                        $(obj).children(".quxiao").html("收藏");
                        $(obj).children(".quxiao").attr("class","shoucang");
                    }
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }


        });

    }
</script>

<#--回复评论的反馈JS-->
<script>
    function reply() {
        $("#replyComment").modal("show");
    }
</script>

<#--回复帖子的评论JS-->
<#--<script>-->


    <#--$("#commentButton").click(function(){-->
        <#--console.log($("#commentForm").serialize());-->
        <#--$.ajax({-->
            <#--url: "/trade_comment_add",-->
            <#--type: "post",-->
            <#--data: $("#commentForm").serialize(),-->
            <#--contentType: "application/json; charset=utf-8",-->
            <#--processData: false,-->
            <#--success: function(data) {-->
                <#--if(data == "ok"){-->
                    <#--$("#commentSuccess").css("display","block");-->
                <#--}-->
                <#--else if(data == "fail"){-->
                    <#--$("#commentFail").css("display","block");-->
                <#--}-->
            <#--},-->
            <#--error: function(XMLHttpRequest, textStatus, errorThrown) {-->
                <#--alert(XMLHttpRequest.status);-->
                <#--alert(XMLHttpRequest.readyState);-->
                <#--alert(textStatus);-->
            <#--}-->
        <#--});-->


    <#--});-->

<#--</script>-->

</body>
</html>