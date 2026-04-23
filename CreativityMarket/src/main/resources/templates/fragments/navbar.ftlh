<div class="navbar">
  <div class="logo">CreativityMarket</div>

  <div class="nav">

    <!-- MARKETPLACE HOME -->
    <a href="/">Home</a>

    <!-- EXPLORE MARKETPLACE -->
    <a href="/assets/explore">Explore</a>

    <!-- ARTIST SYSTEM -->
    <a href="/upload">Upload</a>

    <!-- BOUNTIES SYSTEM -->
    <a href="/bounties">Bounties</a>

    <!-- MARKETPLACE CORE -->
    <a href="/cart">Cart</a>
    <a href="/orders">Orders</a>

    <!-- USER SYSTEM -->
    <#if user??>
      <a href="/users/home">Dashboard</a>
      <a href="/users/profile/edit">Profile</a>
      <a href="/bounties/me">My Bounties</a>
    </#if>

    <!-- AUTH -->
    <#if user??>
      <a href="/users/logout" class="btn">Logout</a>
    <#else>
      <a href="/signin" class="btn">Login</a>
      <a href="/signup" class="btn">Sign Up</a>
    </#if>

  </div>
</div>