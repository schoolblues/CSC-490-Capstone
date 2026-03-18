<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>User Profile</title>
  <link rel="stylesheet" href="/css/user.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>

<body>

<header class="topbar">
  <a class="brand" href="/">CreativityMarket<span>.</span></a>

  <nav class="nav">
    <a href="/assets/explore">Explore</a>
    <a class="active" href="/users/home">Profile</a>
    <a href="/users/logout">Logout</a>
  </nav>
</header>

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
        <span class="pill"><i class="fa-solid fa-cloud-arrow-up"></i> Creator-ready</span>
      </div>
    </div>

    <div class="profile-actions">
      <a class="btn btn-primary" href="/assets/new"><i class="fa-solid fa-plus"></i> Upload Asset</a>
      <a class="btn btn-ghost" href="/users/profile/edit"><i class="fa-solid fa-gear"></i> Edit Profile</a>
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
          <div class="stat-num">${assetsCount!0}</div>
          <div class="stat-label">Assets</div>
        </div>
        <div class="stat">
          <div class="stat-num">${salesCount!0}</div>
          <div class="stat-label">Sales</div>
        </div>
        <div class="stat">
          <div class="stat-num">$<#if earnings??>${earnings?string["0.00"]}<#else>0.00</#if></div>
          <div class="stat-label">Earnings</div>
        </div>
      </div>
    </div>

  </section>

  <section class="card table-card">
    <div class="card-header">
      <h2>My Assets</h2>
      <div class="header-actions">
        <a class="btn btn-ghost" href="/assets/mine"><i class="fa-solid fa-folder-open"></i> View all</a>
      </div>
    </div>

    <div class="assets-overview">
      <div class="table-wrap">
        <table>
          <thead>
          <tr>
            <th>Title</th>
            <th>Type</th>
            <th>Price</th>
            <th>Status</th>
            <th style="text-align:right;">Actions</th>
          </tr>
          </thead>

          <tbody>
          <#if assets?? && (assets?size > 0)>
            <#list assets as a>
              <tr class="item-row" data-uid="${a.sketchfabUid!''}">
                <td class="title">${a.title!"Untitled"}</td>

                <td>${a.fileType!"—"}</td>

                <td>$<#if a.price??>${a.price?string["0.00"]}<#else>0.00</#if></td>

                <td>
                  <span class="status live">
                    <#if a.license?? && a.license.type??>
                      ${a.license.type}
                    <#else>
                      —
                    </#if>
                  </span>
                </td>

                <td class="actions">
                  <a class="icon-btn" href="/assets/${a.id}">
                    <i class="fa-regular fa-eye"></i>
                  </a>
                  <a class="icon-btn" href="/assets/${a.id}/edit">
                    <i class="fa-regular fa-pen-to-square"></i>
                  </a>
                </td>
              </tr>
            </#list>
          <#else>
            <tr>
              <td colspan="5" class="empty">
                <div class="empty-state">
                  <i class="fa-solid fa-cloud-arrow-up"></i>
                  <h3>No assets yet</h3>
                  <p>Upload your first asset to start selling.</p>
                  <a class="btn btn-primary" href="/assets/new">Upload Asset</a>
                </div>
              </td>
            </tr>
          </#if>
          </tbody>
        </table>
      </div>

      <div class="asset-preview-panel preview-panel" id="preview-panel">
        <iframe
          id="my-assets-preview-viewer"
          title="Hovered asset preview"
          allow="autoplay; fullscreen; xr-spatial-tracking"
          allowfullscreen
          mozallowfullscreen="true"
          webkitallowfullscreen="true">
        </iframe>
      </div>
    </div>
  </section>

</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

<script src="https://static.sketchfab.com/api/sketchfab-viewer-1.12.1.js"></script>
<script src="/js/sketchfabscripts.js"></script>

</body>
</html>
