<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error ${status!''} — Creativity Market</title>
    <link rel="stylesheet" href="/css/homepage.css">
    <link rel="stylesheet" href="/css/error.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-left">
            <h1 class="navbar-title">Creativity Market</h1>
            <ul class="navbar-menu">
                <li><a href="/">Home</a></li>
                <li><a href="/users/home">Profile</a></li>
                <li><a href="/assets/explore">Categories</a></li>
                <li><a href="/assets/cart">Cart</a></li>
                <li><a href="/signin">Signin</a></li>
                <li><a href="/bounties">Bounties</a></li>
                <li><a href="/upload">Upload</a></li>
            </ul>
        </div>
        <div class="navbar-right">
            <input type="text" class="search-bar" placeholder="Search the store">
            <button class="search-button">
                <img src="/images/glass.png" alt="Search" class="search-icon">
            </button>
        </div>
    </nav>

    <div class="error-container">
        <p class="error-code">${status!''}</p>
        <h2 class="error-title">${error!'Something went wrong'}</h2>
        <p class="error-message">
            <#if message?? && message?has_content && message != "No message available">
                ${message}
            <#else>
                The page you were looking for could not be loaded. It may have moved or no longer exists.
            </#if>
        </p>
        <a href="/" class="error-back">Go back home</a>
    </div>

    <footer class="footer">
        <div class="footer-content">
            <div class="footer-links">
                <a href="#" class="footer-link">Contact Us</a>
                <a href="#" class="footer-link">Help</a>
                <a href="#" class="footer-link">Terms and Conditions</a>
                <a href="#" class="footer-link">Privacy</a>
            </div>
            <p class="footer-copyright">&copy; 2026 Creativity Market. All rights reserved.</p>
        </div>
    </footer>
</body>
</html>
