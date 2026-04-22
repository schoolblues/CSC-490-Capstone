<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>CreativityMarket • Explore</title>
  <link rel="stylesheet" href="/css/user.css">
  <link rel="stylesheet" href="/css/explore.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
  <link rel="stylesheet" href="/css/homepage.css">
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
  <div class="explore-layout">

    <!-- Filter Sidebar -->
    <aside class="filter-sidebar">
      <h3>Filters</h3>

      <form method="get" action="/assets/explore">
        <div class="filter-group">
          <div class="filter-group-label">File Type</div>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="STL"> <span>STL</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="OBJ"> <span>OBJ</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="FBX"> <span>FBX</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="GLTF"> <span>GLTF</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="BLEND"> <span>BLEND</span>
          </label>
        </div>

        <div class="filter-group">
          <div class="filter-group-label">License</div>
          <label class="filter-option">
            <input type="checkbox" name="license" value="personal"> <span>Personal</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="license" value="commercial"> <span>Commercial</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="license" value="editorial"> <span>Editorial</span>
          </label>
        </div>

        <div class="filter-group">
          <div class="filter-group-label">Price</div>
          <label class="filter-option">
            <input type="checkbox" name="price" value="free"> <span>Free</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="price" value="paid"> <span>Paid</span>
          </label>
        </div>

        <button type="submit" class="filter-apply-btn">Apply Filters</button>
        <a href="/assets/explore"><button type="button" class="filter-clear-btn">Clear All</button></a>
      </form>
    </aside>

    <!-- Main Content -->
    <div class="explore-content">
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
    </div>

  </div>
</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

</body>
</html>