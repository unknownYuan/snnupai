<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<script type="text/javascript" src="echarts.min.js"></script>
	<title>图标</title>
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        html,body {
            width: 100%;
            height: 730px;
            color: #fff;
        }

        #body {
            height: 100%;
            width: 100%;
            padding-top: 30px;
            background: url(images/404-bg.jpg) no-repeat;
            background-size:100% 100%;
        }

        .fzf-content {
            margin-top: 70px;
            margin-left: 140px;
            line-height: 50px;
            letter-spacing: 30px;
        }

        .button-content {
            margin-top: 30px;
            margin-left: 140px;            
        }

        .button {
            background-color: #4CAF50; /* Green */
            border: none;
            color: white;
            padding: 15px 50px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            border-radius: 25px;
            margin-right: 20px;
            letter-spacing: 6px;
        }

        .button:hover {
            cursor: pointer;
        }

    </style>
</head>
<body>
    <div id="body">
        
        <div class="fzf-content">
            <h1>啊哦！</h1>
            <h2>页面找不到了!</h2>
            <h3 style="letter-spacing: 20px;">看起来你好像走丢了呢~~</h3>
        </div>
        <div class="button-content">
            <a href="index">
                <button type="button" id="returnIndex" class="button">返回首页</button>
            </a>
            <a href="">
                <button type="button" id="bug" class="button">BUG反馈</button>
            </a>
        </div>
    </div>

</body>
</html>