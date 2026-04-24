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

        <#if user??>
            <div class="nav-dropdown-wrap">
                <button class="nav-dropdown-btn" id="navDropdownBtn" aria-label="Account menu">
                    <i class="fa-solid fa-user"></i>
                </button>
                <div class="nav-dropdown-menu" id="navDropdownMenu">
                    <div class="nav-dropdown-header">
                        <span class="nav-dropdown-name">${user.name!"User"}</span>
                        <span class="nav-dropdown-email">${user.email!""}</span>
                    </div>
                    <div class="nav-dropdown-divider"></div>
                    <a href="/users/home"><i class="fa-solid fa-user"></i> My Profile</a>
                    <a href="/cart"><i class="fa-solid fa-cart-shopping"></i> Cart</a>
                    <a href="/bounties/me"><i class="fa-solid fa-scroll"></i> My Bounties</a>
                    <div class="nav-dropdown-divider"></div>
                    <a href="/users/logout" class="nav-dropdown-danger"><i class="fa-solid fa-right-from-bracket"></i> Logout</a>
                </div>
            </div>
        <#else>
            <a href="/signin" class="nav-login-btn">Login</a>
        </#if>
    </div>
</nav>

<script>
(function () {
    var btn = document.getElementById('navDropdownBtn');
    var menu = document.getElementById('navDropdownMenu');
    btn.addEventListener('click', function (e) {
        e.stopPropagation();
        menu.classList.toggle('open');
        btn.classList.toggle('active');
    });
    document.addEventListener('click', function () {
        menu.classList.remove('open');
        btn.classList.remove('active');
    });
}());
</script>
