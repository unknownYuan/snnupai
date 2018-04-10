<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>lovelist</title>
</head>
<body>
<#list loves as love>
    ${love.id}
    ${love.content}
</#list>
</body>
</html>