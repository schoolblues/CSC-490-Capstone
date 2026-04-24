<nav class="navbar">
    <div class="navbar-left">
        <a href="/" class="navbar-title<#if user?? && (user.admin || user.superAdmin)> navbar-title--admin</#if>"><#if user?? && (user.admin || user.superAdmin)>CM-Admin<#else>Creativity Market</#if></a>
        <#if user?? && (user.admin || user.superAdmin)>
        <ul class="navbar-menu">
            <li><a href="/admin/dashboard">Dashboard</a></li>
            <li><a href="/admin/moderation">Moderation</a></li>
            <li><a href="/admin/reviews">Reviews</a></li>
            <li><a href="/admin/users">Users</a></li>
            <li><a href="/admin/assets">Assets</a></li>
            <li><a href="/admin/bounties">Bounties</a></li>
            <li><a href="/admin/categories">Categories</a></li>
            <li><a href="/admin/logs">Logs</a></li>
        </ul>
        </#if>
    </div>
    <div class="navbar-right">
        <form method="get" action="/assets/explore" style="display:flex; flex:1; gap:8px; min-width:0;">
            <input type="text" name="keyword" class="search-bar" placeholder="Search the store" value="${(keyword)!''}">
            <button class="search-button" type="submit">
                <img src="/images/glass.png" alt="Search" class="search-icon">
            </button>
        </form>

        <#if !(user?? && (user.admin || user.superAdmin))>
            <div class="nav-divider"></div>
            <a href="/assets/explore" class="nav-text-link">Categories</a>
            <a href="/bounties" class="nav-text-link">Bounties</a>
        </#if>

        <a href="/cart" class="nav-cart-btn" aria-label="Cart">
            <i class="fa-solid fa-cart-shopping"></i>
        </a>

        <a href="/upload" class="nav-cart-btn" aria-label="Upload a Model">
            <i class="fa-solid fa-cloud-arrow-up"></i>
        </a>

        <#if user??>
            <div class="nav-dropdown-wrap">
                <button class="nav-dropdown-btn" id="navDropdownBtn" aria-label="Account menu">
                    <#if user.profileImageUrl?? && user.profileImageUrl?has_content>
                        <img src="${user.profileImageUrl}" alt="Profile" class="nav-dropdown-avatar">
                    <#else>
                        <i class="fa-solid fa-user"></i>
                    </#if>
                </button>
                <div class="nav-dropdown-menu" id="navDropdownMenu">
                    <div class="nav-dropdown-header">
                        <span class="nav-dropdown-name">${user.name!"User"}</span>
                        <span class="nav-dropdown-email">${user.email!""}</span>
                    </div>
                    <div class="nav-dropdown-divider"></div>
                    <a href="/users/home"><i class="fa-solid fa-user"></i> My Profile</a>
                    <a href="/upload"><i class="fa-solid fa-cloud-arrow-up"></i> Upload a Model</a>
                    <a href="/upload/my"><i class="fa-solid fa-cubes"></i> My Models</a>
                    <a href="/bounties/me"><i class="fa-solid fa-scroll"></i> My Bounties</a>
                    <a href="/cart"><i class="fa-solid fa-cart-shopping"></i> Cart</a>
                    <a href="/users/account"><i class="fa-solid fa-gear"></i> Account Settings</a>
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
    if (!btn || !menu) return;
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
