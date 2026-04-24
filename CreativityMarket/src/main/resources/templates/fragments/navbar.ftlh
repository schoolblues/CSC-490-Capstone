<nav class="navbar">
    <div class="navbar-left">
        <a href="/" class="navbar-title<#if user?? && (user.admin || user.superAdmin)> navbar-title--admin</#if>"><#if user?? && (user.admin || user.superAdmin)>CM-Admin<#else>Creativity Market</#if></a>
        <ul class="navbar-menu">
            <#if user?? && (user.admin || user.superAdmin)>
                <li><a href="/admin/dashboard">Dashboard</a></li>
                <li><a href="/admin/moderation">Moderation</a></li>
                <li><a href="/admin/users">Users</a></li>
                <li><a href="/admin/assets">Assets</a></li>
                <li><a href="/admin/bounties">Bounties</a></li>
                <li><a href="/admin/categories">Categories</a></li>
                <li><a href="/admin/logs">Logs</a></li>
                <li><a href="/users/logout">Logout</a></li>
            <#else>
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
