<nav class="navbar">
    <div class="navbar-left">
        <a href="/" class="navbar-title">Creativity Market</a>
        <ul class="navbar-menu">
            <#if user??>
                <li><a href="/users/home">Profile</a></li>
            </#if>
            <li><a href="/assets/explore">Categories</a></li>
            <li><a href="/cart">Cart</a></li>
            <li><a href="/bounties">Bounties</a></li>
            <#if user??>
                <li><a href="/bounties/me">My Bounties</a></li>
                <li><a href="/users/logout">Logout</a></li>
            </#if>
        </ul>
    </div>
    <div class="navbar-right">
        <input type="text" class="search-bar" placeholder="Search the store">
        <button class="search-button">
            <img src="/images/glass.png" alt="Search" class="search-icon">
        </button>
        <#if !user??>
            <a href="/signin" class="navbar-signin">Sign In</a>
        </#if>
    </div>
</nav>
