<#macro main extraCss="" extraScripts="">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CreativityMarket</title>
    <link rel="stylesheet" href="/css/homepage.css">
    <link rel="stylesheet" href="/css/user.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    ${extraCss}
</head>
<body>

<#include "/fragments/navbar.ftlh">

<main>
    <#nested>
</main>

<footer class="footer">
    <p>&copy; ${.now?string("yyyy")} CreativityMarket</p>
</footer>

${extraScripts}
</body>
</html>
</#macro>
