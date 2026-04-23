<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>CreativityMarket • Explore</title>
  <link rel="stylesheet" href="/css/homepage.css">
  <link rel="stylesheet" href="/css/user.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<nav class="navbar">
    <div class="navbar-left">
        <h1 class="navbar-title">Creativity Market</h1>
        <ul class="navbar-menu">
            <li><a href="/">Home</a></li>
            <li><a href="/users/home">Profile</a></li>
            <li><a class="active" href="/assets/explore">Categories</a></li>
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

<main class="container">

  <section class="card" style="padding:18px; margin-bottom:18px;">
    <div style="display:flex; justify-content:space-between; align-items:center; gap:16px; flex-wrap:wrap;">
      <div>
        <h1 style="margin-bottom:6px;">Explore Assets</h1>
        <p class="muted">Browse 3D assets available in CreativityMarket.</p>
      </div>

      <form action="/assets/explore" method="get" style="display:flex; gap:10px; flex-wrap:wrap;">
        <input
          type="text"
          name="keyword"
          value="${keyword!""}"
          placeholder="Search by title or file type..."
          style="min-width:240px; padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);"
        />
        <button class="btn btn-primary" type="submit">
          <i class="fa-solid fa-magnifying-glass"></i> Search
        </button>
      </form>
    </div>
  </section>

  <section class="section-title">
    <div>
      <h2>Marketplace</h2>
      <p class="muted">
        <#if assets??>
          ${assets?size} asset<#if assets?size != 1>s</#if> found
        <#else>
          0 assets found
        </#if>
      </p>
    </div>
  </section>

  <section class="grid">
    <#if assets?? && (assets?size > 0)>
      <#list assets as a>
        <a class="card" href="/assets/${a.id}" style="text-decoration:none; color:inherit;">
          <div class="thumb">
            <div>
              <i class="fa-solid fa-cube" style="font-size:1.6rem; margin-bottom:8px; display:block;"></i>
              Preview
            </div>
          </div>

          <div class="card-body">
            <h3>${a.title!"Untitled"}</h3>

            <p class="small" style="margin-bottom:12px;">
              <#if a.license?? && a.license?has_content>
                ${a.license}
              <#else>
                High-quality 3D asset ready for your next project.
              </#if>
            </p>

            <div class="meta" style="margin-bottom:12px;">
              <span>${a.fileType!"—"}</span>
              <span class="price">
                $<#if a.price??>${a.price?string["0.00"]}<#else>0.00</#if>
              </span>
            </div>

            <div style="display:flex; justify-content:space-between; align-items:center; gap:10px; flex-wrap:wrap;">
              <span class="tag">
                <#if a.license?? && a.license?has_content>
                  ${a.license}
                <#else>
                  Standard
                </#if>
              </span>

              <span class="btn btn-ghost" style="pointer-events:none;">
                View Asset
              </span>
            </div>
          </div>
        </a>
      </#list>
    <#else>
      <div class="card" style="grid-column:1 / -1;">
        <div class="empty">
          <div class="empty-state">
            <i class="fa-solid fa-magnifying-glass"></i>
            <h3>No matching assets found</h3>
            <p>Try searching for a different title or file type.</p>
            <a class="btn btn-primary" href="/assets/explore">Clear Search</a>
          </div>
        </div>
      </div>
    </#if>
  </section>

</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

</body>
</html>