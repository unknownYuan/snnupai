<!DOCTYPE html>
<html>
<head>
    <title>表白墙</title>
    <#include "include.ftl">

    <!-- 自定义CSS -->
    <link rel="stylesheet" type="text/css" href="css/header-footer.css">
    <link rel="stylesheet" type="text/css" href="css/snnupai-universal.css">
    <link rel="stylesheet" type="text/css" href="css/love_wall.css">

</head>
<body>
<div id="body">
    <#include "header.ftl">


    <div class="love-wall-header snnupai-universal-header">
        <div class="love-wall-header-bg snnupai-universal-header-bg">
            <div class="love-wall-title snnupai-universal-title">
                <h1>表白气球</h1>
            </div>
            <div class="love-wall-edit snnupai-universal-edit">
                <button type="button" class="btn " data-toggle="modal" data-target="#postLoveInfo">放一个表白气球</button>
            </div>
        </div>
    </div>



    <div class="love-wall-body content snnupai-universal-body">

        <div class="row">


            <!-- 搜索框 -->
            <!-- <div class="col-md-6">
                <div class="input-group">
                      <input type="text" class="form-control" placeholder="Search for...">
                     <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                      </span>
                </div>
                <br><br>
            </div> -->





            <ul>
                <!-- li用来重复 -->
                <#list loveInfos as love>

                    ${love["anonymous"]}




                <li class="love-wall-li">
                    <div class="love-wall-info-content">
                        <div class="love-wall-personal-info">
                            <img src="images/header.jpg"/>
                            <!-- <div id="head-img"></div> -->
                            <h4>发布昵称:${love["nickname"]}</h4>
                        </div>

                        <div class="love-wall-info">
                            <p>表白内容：${love["content"]}</p>
                        </div>

                        <div class="love-wall-more pull-right">
                            <span> 表白时间：${love["time"]}</span>&nbsp&nbsp


                            <button type="button" class="love-comment btn btn-default btn-sm" onclick="showComment(this)">
                                <span class="glyphicon glyphicon-comment"></span> 2条评论
                            </button>

                            <button type="button" class="love-comment btn btn-default btn-sm" onclick="hideComment(this)" style="display:none">
                                <span class="glyphicon glyphicon-comment"></span> 收起评论
                            </button>

                            <button type="button" class="love-like btn btn-default btn-sm" onclick="addcollection(this)">
                                <span class="glyphicon glyphicon-star" aria-hidden="true"></span> 关注
                            </button>

                        </div>

                        <div class="love-wall-comment" style="display:none">
                            <form action="">
                                <div class="input-group col-md-4">
                                    <input type="text" class="form-control" placeholder="评论这条表白信息">
                                    <span class="input-group-btn">
			        						<button class="btn btn-default" type="button">回复</button>
			      						</span>
                                </div><!-- /input-group -->
                            </form>
                            <ul>
                                <li>
                                    <span>鲁智深:</span>
                                    <span>真感动啊！！！</span>
                                    <span>2018-4-3 8:52</span>
                                    <span><a href="javascript:void(0);" onclick="reply(this)">回复</a></span>
                                </li>

                                <li>
                                    <span>林冲</span>
                                    <span>回复</span>
                                    <span>鲁智深：</span>
                                    <span>你是个花和尚！！！</span>
                                    <span>2018-4-3 8:52</span>
                                    <span><a href="javascript:void(0);" onclick="reply(this)">回复</a></span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </li>


                </#list>
            </ul>
        </div>
    </div>

    <button class="btn back_to_top">返回顶部</button>
    <button class="btn snnupai-feedback">意见反馈</button>



    <!-- 编辑表白信息的模态框 -->

    <div class="modal fade" id="postLoveInfo"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="btn-info modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <!--  关闭按钮  -->
                    <h4>勇敢地表白吧~~~~</h4>
                    <!--  标题内容  -->

                </div>

                <div class="modal-body">
                    <form action="/love/add" method="post" id="addLove">

                        <div class="form-group">
                            <label for="uname" class=" col-sm-3 control-label text-right">是否匿名：</label>
                            <div class="col-md-9">
                                <select class="love-wall-anonymous form-control well " name="anonymous">
                                    <option value="0" selected>实名</option>
                                    <option value="1">匿名</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="uname" class="col-sm-3 control-label text-right">表白内容：</label>
                            <div class="col-sm-9">
                                <textarea type="text" rows="5" cols="20" id="uname" name="content" class="form-control well love-wall-area" placeholder="在这里输入你的表白的信息吧~~"></textarea>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-info" id="addLoveInfoButton">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>

            </div>

        </div>

    </div>

    <!-- 回复模态框 -->
    <div class="modal fade" id="loveWallReplyComment" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    <form role="form" >
                        <div class="form-group">
                            <label for="name">回复：</label>
                            <textarea class="form-control" rows="3"></textarea>

                        </div>

                    </form>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                </div>
            </div>
        </div>
    </div>

    <!-- 提交成功反馈 -->
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    提交成功
                </div>
            </div>
        </div>
    </div>
    <#--提交失败的反馈-->
    <div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body">
                    提交失败
                </div>
            </div>
        </div>
    </div>

    <#include "footer.ftl">
</div>

<#--下拉刷新-->



<script type="text/javascript">
    $(document).ready(function(){

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

    //显示评论
    function showComment(obj) {
        var commentLi = $(obj).parent();
        var comment = commentLi.next();
        $(comment).css("display","block");

        $(obj).css("display","none");
        $(obj).next().css("display","inline-block");

    }

    //隐藏评论
    function hideComment(obj) {
        var commentLi = $(obj).parent();
        var comment = commentLi.next();
        $(comment).css("display","none");

        $(obj).css("display","none");
        $(obj).prev().css("display","inline-block");
    }

</script>

<script type="text/javascript">
    function addcollection(obj) {
        //关注ajax
        var loveId="";
        $.ajax({
            url: "",
            type: "",
            scriptCharset: 'utf-8',
            contentType: 'application/json',
            success: function(data) {
                if(data == "ok"){
                    var gly = $(obj).children(".glyphicon");
                    if(gly.hasClass("glyphicon-star")){
                        gly.removeClass("glyphicon-star");
                        gly.addClass("glyphicon-star-empty");
                        $(obj).addClass("collected");
                    }
                    else{
                        gly.removeClass("glyphicon-star-empty");
                        $(obj).removeClass("collected");
                        gly.addClass("glyphicon-star");
                    }
                }
                else{
                    alert("fail");
                }
            }
        });



    }
</script>

<#--回复评论JS-->
<script type="text/javascript">
    function reply(obj) {
        $("#loveWallReplyComment").modal("show");
    }
</script>

<#--提交表白信息-->
<script>
    $("#addLoveInfoButton").click(function(){
        $("#postLoveInfo").modal("hide");
        var formData=new FormData($("#addLove")[0]);
        $.ajax({
            url: "/love/add",
            type: "POST",
            //dataType:"json",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data){
                if(data=="ok"){
                    $("#myModal2").modal("show");
                }else{
                    $("#myModal3").modal("show");
                }
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    });
</script>

</body>
</html>