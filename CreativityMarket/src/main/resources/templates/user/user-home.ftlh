<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>User Profile</title>
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
            <li><a class="active" href="/users/home">Profile</a></li>
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

<main class="container">

  <section class="profile-hero">
    <div class="avatar">
      <i class="fa-solid fa-user"></i>
    </div>

    <div class="profile-info">
      <h1>${user.name!"User"}</h1>
      <p class="muted">${user.email!"No email on file"}</p>

      <div class="pill-row">
        <span class="pill"><i class="fa-solid fa-shield"></i> Verified account</span>
        <span class="pill"><i class="fa-solid fa-bag-shopping"></i> Buyer account</span>
      </div>
    </div>

    <div class="profile-actions">
      <a class="btn btn-ghost" href="/users/profile/edit">
        <i class="fa-solid fa-gear"></i> Edit Profile
      </a>
    </div>
  </section>

  <section class="grid">

    <div class="card">
      <div class="card-header">
        <h2>Account</h2>
        <span class="tag">Profile</span>
      </div>
      <div class="card-body">
        <div class="row">
          <span class="label">Name</span>
          <span class="value">${user.name!"—"}</span>
        </div>
        <div class="row">
          <span class="label">Email</span>
          <span class="value">${user.email!"—"}</span>
        </div>
        <div class="row">
          <span class="label">Member Since</span>
          <span class="value">${user.createdAt!"—"}</span>
        </div>
      </div>
    </div>

    <div class="card">
      <div class="card-header">
        <h2>Quick Stats</h2>
        <span class="tag">Summary</span>
      </div>
      <div class="card-body stats">
        <div class="stat">
          <div class="stat-num">${purchasesCount!0}</div>
          <div class="stat-label">Purchases</div>
        </div>
        <div class="stat">
          <div class="stat-num">${wishlistCount!0}</div>
          <div class="stat-label">Wishlist</div>
        </div>
        <div class="stat">
          <div class="stat-num">${downloadsCount!0}</div>
          <div class="stat-label">Downloads</div>
        </div>
      </div>
    </div>

  </section>

  <section class="card table-card">
    <div class="card-header">
      <h2>My Purchases</h2>
      <div class="header-actions">
        <a class="btn btn-ghost" href="/assets/explore">
          <i class="fa-solid fa-compass"></i> Explore More
        </a>
      </div>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Type</th>
            <th>Purchase Date</th>
            <th>License</th>
            <th style="text-align:right;">Actions</th>
          </tr>
        </thead>

        <tbody>
        <#if purchases?? && (purchases?size > 0)>
          <#list purchases as p>
            <tr>
              <td class="title">
                <#if p.asset??>
                  ${p.asset.title!"Untitled"}
                <#else>
                  Untitled
                </#if>
              </td>

              <td>
                <#if p.asset??>
                  ${p.asset.fileType!"—"}
                <#else>
                  —
                </#if>
              </td>

              <td>
                <#if p.purchaseDate??>
                  ${p.purchaseDate}
                <#elseif p.createdAt??>
                  ${p.createdAt}
                <#else>
                  —
                </#if>
              </td>

              <td>
                <span class="status live">
                  <#if p.asset?? && p.asset.license?? && p.asset.license.type??>
                    ${p.asset.license.type}
                  <#else>
                    Standard
                  </#if>
                </span>
              </td>

              <td class="actions">
                <#if p.asset?? && p.asset.id??>
                  <a class="icon-btn" href="/assets/${p.asset.id}" title="View Asset">
                    <i class="fa-regular fa-eye"></i>
                  </a>
                  <a class="icon-btn" href="/assets/${p.asset.id}/download" title="Download Asset">
                    <i class="fa-solid fa-download"></i>
                  </a>
                <#else>
                  <span class="muted">No actions</span>
                </#if>
              </td>
            </tr>
          </#list>
        <#else>
          <tr>
            <td colspan="5" class="empty">
              <div class="empty-state">
                <i class="fa-solid fa-bag-shopping"></i>
                <h3>No purchases yet</h3>
                <p>Browse the marketplace and purchase your first asset.</p>
                <a class="btn btn-primary" href="/assets/explore">Explore Assets</a>
              </div>
            </td>
          </tr>
        </#if>
        </tbody>
      </table>
    </div>
  </section>

  <section class="card table-card">
    <div class="card-header">
      <h2>Wishlist</h2>
      <div class="header-actions">
        <a class="btn btn-ghost" href="/assets/explore">
          <i class="fa-regular fa-heart"></i> Browse Assets
        </a>
      </div>
    </div>

    <div class="table-wrap">
      <table>
        <thead>
          <tr>
            <th>Title</th>
            <th>Type</th>
            <th>Price</th>
            <th>License</th>
            <th style="text-align:right;">Actions</th>
          </tr>
        </thead>

        <tbody>
        <#if wishlist?? && (wishlist?size > 0)>
          <#list wishlist as a>
            <tr>
              <td class="title">${a.title!"Untitled"}</td>
              <td>${a.fileType!"—"}</td>
              <td>$<#if a.price??>${a.price?string["0.00"]}<#else>0.00</#if></td>
              <td>
                <span class="status live">
                  <#if a.license?? && a.license.type??>
                    ${a.license.type}
                  <#else>
                    Standard
                  </#if>
                </span>
              </td>
              <td class="actions">
                <#if a.id??>
                  <a class="icon-btn" href="/assets/${a.id}" title="View Asset">
                    <i class="fa-regular fa-eye"></i>
                  <form action="/assets/${a.id}/wishlist/remove" method="post" style="display:inline;">
                    <button class="icon-btn" type="submit" title="Remove from Wishlist">
                    <i class="fa-regular fa-heart"></i>
                    </button>
                    </form>
                <#else>
                  <span class="muted">No actions</span>
                </#if>
              </td>
            </tr>
          </#list>
        <#else>
          <tr>
            <td colspan="5" class="empty">
              <div class="empty-state">
                <i class="fa-regular fa-heart"></i>
                <h3>Your wishlist is empty</h3>
                <p>Save assets you like so you can come back to them later.</p>
                <a class="btn btn-primary" href="/assets/explore">Start Exploring</a>
              </div>
            </td>
          </tr>
        </#if>
        </tbody>
      </table>
    </div>
  </section>

</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

</body>
</html>
