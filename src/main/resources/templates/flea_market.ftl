<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>跳蚤市场</title>
    <#include "include.ftl">
    <link rel="stylesheet" type="text/css" href="css/snnupai-universal.css">
    <link rel="stylesheet" type="text/css" href="css/flea_market.css">

    <#--分页-->
    <script src="js/jqpaginator.min.js"></script>
</head>
<body>
<div id="body">
<#include "header.ftl">
<div class="lost-and-found-header snnupai-universal-header">
    <div class="lost-and-found-header-bg snnupai-universal-header-bg">
        <div class="lost-and-found-title snnupai-universal-title">
            <h1>跳蚤市场</h1>
        </div>
        <div class="lost-and-found-edit snnupai-universal-edit">
            <button type="button" class="btn " data-toggle="modal" data-target="#postProductInfo">发布二手商品</button>
        </div>
    </div>
</div>



<div class="lost-and-found-body content snnupai-universal-body">

    <div class="row flea-market-content">

        <#--搜索框-->
        <#--<div class="col-md-6">-->
            <#--<div class="input-group">-->
                <#--<input type="text" class="form-control" placeholder="Search for...">-->
                <#--<span class="input-group-btn">-->
        					<#--<button class="btn btn-default" type="button">Go!</button>-->
      					<#--</span>-->
            <#--</div>-->
        <#--</div>-->
        <#--<br><br>-->

        <ul class="snnupai-universal-ul">
            <#list trades as trade>
            <#--li用来重复-->
            <li class="col-md-3">
                <div class="lost-and-found-info snnupai-universal-info">
                    <a href="/trade?id=${ trade.id }">
                        <img src="${trade.imageUrl}"/>
                    </a>
                    <div class="lost-and-found-des snnupai-universal-des">
                        <p>标题：${trade.title}</p>
                        <p>时间： ${(trade.createdDate?string("yyyy-MM-dd HH:mm:ss"))}</p>
                        <p>描述：${trade.content}</p>
                    </div>
                    <div class="collection">
                        <#if trade.follow == "unfollow" >
                            <button type="button" class=" btn btn-default btn-sm pull-right" onclick="addcollection(this, '${ trade.id }')">
                                <span class="glyphicon glyphicon-star-empty" aria-hidden="true"></span> <span class="shoucang">收藏</span>
                            </button>
                        <#else>
                            <button type="button" class="collected btn btn-default btn-sm pull-right" onclick="addcollection(this, '${ trade.id }')">
                                <span class="glyphicon glyphicon-star" aria-hidden="true"></span> <span class="quxiao">取消</span>
                            </button>
                        </#if>

                    </div>
                </div>
            </li>
            </#list>
        </ul>


    </div>
    <div><ul class="pagination" id="pagination1"></ul></div>
</div>

<button class="btn back_to_top">返回顶部</button>
<button class="btn snnupai-feedback">意见反馈</button>



<!-- 编辑发布二手物品的模态框 -->

<div class="modal fade" id="postProductInfo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="btn-info modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <!--  关闭按钮  -->
                <h4>发布二手商品</h4>
                <!--  标题内容  -->

            </div>

            <div class="modal-body" >
                <form action="" method="post" id="addTradeForm" id="addTrade" enctype="multipart/form-data">

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">标题：</label>
                        <div class="col-sm-9">
                            <input type="text" id="uname" name = "title" class="form-control well"
                                   placeholder="添加标题（必填）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">物品描述：</label>
                        <div class="col-sm-9">
                            <textarea type="text" id="uname" name="content" class="form-control well"
                                      placeholder="物品描述（必填）"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">是否匿名：</label>
                        <div class="col-sm-9">
                            <select class="form-control well anonymous" name="anonymous">
                                <option value="1" >匿名</option>
                                <option value="0" selected>实名</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">价格：</label>
                        <div class="col-sm-8">
                            <input name="price" type="text" id="uname" class="form-control well"
                                   placeholder="添加价格（必填，单位：元）"/>

                        </div>
                        <span class="pull-right" style="margin-top:10px; margin-right: 30px; font-size: 16px;">元</span>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">QQ：</label>
                        <div class="col-sm-9">
                            <input type="text" id="uname" name="qq" class="form-control well"
                                   placeholder="QQ（可选）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">weixin：</label>
                        <div class="col-sm-9">
                            <input type="text" id="uname" name="weixin" class="form-control well"
                                   placeholder="微信（可选）"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="uname" class="col-sm-3 control-label text-right">物品照片：</label>
                        <div class="col-sm-9">
                            <input type="file" id="fileUpload" multiple="true" name="files" class="file-loading well" placeholder="物品照片"/>
                            <div id="image-holder" style="margin:5px 2px;"> </div>
                        </div>
                    </div>
                </form>
            </div>

            <div class="modal-footer">
                <button type="submit" id="addTradeButton" class="btn btn-info" form="addTrade">确定</button>
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

    <script>
        var is_first_time = true;
        $("#pagination1").jqPaginator({
            totalPages: ${ totalPages }, //总页数
            visiblePages: 10,
            currentPage: ${ currentNum } ,
            onPageChange: function (num, type) {
                //$('#p1').text(type + '：' + num);
                if(is_first_time){
                    is_first_time = false;
                }
                else if(!is_first_time){
                    window.location.href = "/trade_ftl?pagenum="+num;
                }
            }
        });
    </script>
<#--回到头部、意见反馈按钮的JS-->
<script>
    $(document).ready(function(){

        // 回到顶部
        var backButton=$('.back_to_top');
        backButton.click(function(){
            $('html,body').animate({
                scrollTop: 0
            }, 800);
        });

        $(window).on('scroll', function () {
            // 当滚动条的垂直位置大于浏览器所能看到的页面的那部分的高度时，回到顶部按钮就显示

            if ($(window).scrollTop() > $(window).height())
                backButton.fadeIn();
            else
                backButton.fadeOut();
        });
        $(window).trigger('scroll');
    //触发滚动事件，避免刷新的时候显示回到顶部按钮

</script>

<#--发布时的图片预览JS-->
<script>

    $("#fileUpload").on('change', function () {

        //获取上传文件的数量
        var countFiles = $(this)[0].files.length;

        var imgPath = $(this)[0].value;
        var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
        var image_holder = $("#image-holder");
        image_holder.empty();

        if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
            if (typeof (FileReader) != "undefined") {

                // 循环所有要上传的图片
                for (var i = 0; i < countFiles; i++) {

                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $("<img />", {
                            "src": e.target.result,
                            "class": "thumb-image",
                            "style": "width:150px;height:100px;"
                        }).appendTo(image_holder);
                    }

                    image_holder.show();
                    reader.readAsDataURL($(this)[0].files[i]);
                }

            } else {
                alert("你的浏览器不支持FileReader！");
            }
        } else {
            alert("请选择图像文件。");
        }
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

<#--反馈的模态框-->
<script>

    $("#addTradeButton").click(function () {
        $("#postProductInfo").modal("hide");
        var formData=new FormData($("#addTradeForm")[0]);
        $.ajax({
            url: "/trade/add",
            type: "POST",
            //dataType:"json",
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function(data){
                // console.log("132");
                // alert("132");
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



<#--下拉刷新-->
<#--<script>-->
    <#--var num = 1;-->
    <#--if(num != 1000000) {-->
        <#--$(window).scroll(function () {-->
            <#--var scrollPos = $(this).scrollTop();-->
            <#--var dbHiht = $(document).height();-->

            <#--if (dbHiht - scrollPos - $(this).height() < 30) {-->
                <#--num++;-->
                <#--console.log("需要开始加载");-->


                <#--//这里执行滚动条到页面底部时的操作-->
                <#--$.ajax({-->
                    <#--url: "/snnupai_user/feed?offset=" + num + "&limit=10",-->
                    <#--type: "GET",-->
                    <#--scriptCharset: 'utf-8',-->
                    <#--contentType: 'application/json',-->
                    <#--dataType: 'json',-->
                    <#--success: function (data) {-->
                        <#--//                            eval("data=" + data);-->
                        <#--//动态-->
                        <#--if (data) {-->
                            <#--var html = '';-->
                            <#--for (var i = 0; i < data.feeds.length; i++) {-->
                                <#--html += "<li class='col-md-3'><div class='lost-and-found-info snnupai-universal-info'>" +-->
                                        <#--"<a href='/reafe?id=" + tradeid + "'>" + "<img src='" + tradeImageUrl + "'/></a>" +-->
                                        <#--"<div class='lost-and-found-des snnupai-universal-des'>" +-->
                                        <#--"<p>标题：" + title + "</p><p>时间：" + time + "</p><p>描述：" + content + "</p>" +-->
                                        <#--"</div>" +-->
                                        <#--"<div class='collection'>";-->
                                <#--if (fllow == "unfollow") { //没有收藏-->
                                    <#--html += "<button type='button' class='btn btn-default btn-sm pull-right' onclick='addcollection(this, \"" + tradeId + "\")'>" +-->
                                            <#--"<span class='glyphicon glyphicon-star-empty' aria-hidden='true'></span> <span class='shoucang'>收藏</span>" +-->
                                            <#--"</button>";-->
                                <#--}-->
                                <#--else {-->
                                    <#--html += "<button type='button' class='collected btn btn-default btn-sm pull-right' onclick='addcollection(this, \"" + tradeId + "\")'>" +-->
                                            <#--"<span class='glyphicon glyphicon-star-empty' aria-hidden='true'></span> <span class='shoucang'>收藏</span>" +-->
                                            <#--"</button>";-->
                                <#--}-->
                                <#--html += "</div></div></li>";-->
                            <#--}-->
                        <#--}-->
                        <#--else {-->
                            <#--html += "没有更多信息了~~";-->
                            <#--num = 1000000;-->
                        <#--}-->

                        <#--console.log(html);-->
                        <#--$(".flea-market-content ul").append(html);-->
                        <#--console.log($(".flea-market-content ul"));-->
                    <#--}-->


                <#--})-->
            <#--}-->

        <#--});-->
    <#--}-->
<#--</script>-->

</div>
</body>
</html>