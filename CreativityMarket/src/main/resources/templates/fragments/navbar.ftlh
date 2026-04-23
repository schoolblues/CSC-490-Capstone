<nav class="navbar">
    <div class="navbar-left">
        <h1 class="navbar-title">Creativity Market</h1>
        <ul class="navbar-menu">
            <li><a href="/">Home</a></li>
            <#if user??>
                <li><a href="/users/home">Profile</a></li>
            </#if>
            <li><a href="/assets/explore">Categories</a></li>
            <li><a href="/cart">Cart</a></li>
            <li><a href="/bounties">Bounties</a></li>
<#if user??>
                <li><a href="/bounties/me">My Bounties</a></li>
                <li><a href="/users/logout">Logout</a></li>
            <#else>
                <li><a href="/signin">Sign In</a></li>
            </#if>
        </ul>
    </div>
    <div class="navbar-right">
        <input type="text" class="search-bar" placeholder="Search the store">
        <button class="search-button">
            <img src="/images/glass.png" alt="Search" class="search-icon">
        </button>
    </div>
</nav>
