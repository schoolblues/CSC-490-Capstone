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
    ${extraCss?no_esc}
</head>
<body>

<#include "/fragments/navbar.ftlh">

<main>
    <#nested>
</main>

<footer class="footer">
    <div class="footer-content">
        <div class="footer-links">
            <a href="#" class="footer-link">Contact Us</a>
            <a href="#" class="footer-link">Help</a>
            <a href="#" class="footer-link">Student Discounts</a>
            <a href="#" class="footer-link">Forums</a>
            <a href="#" class="footer-link">Terms and Conditions</a>
            <a href="#" class="footer-link">Privacy</a>
        </div>
        <p class="footer-copyright">&copy; ${.now?string("yyyy")} Creativity Market. All rights reserved.</p>
    </div>
</footer>

${extraScripts?no_esc}
</body>
</html>
</#macro>
