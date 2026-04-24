<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

  <section class="profile-hero">
    <div class="avatar">
      <#if user.profileImageUrl?? && user.profileImageUrl?has_content>
        <img src="${user.profileImageUrl}" alt="Profile" style="width:100%; height:100%; object-fit:cover; border-radius:4px;">
      <#else>
        <i class="fa-solid fa-user"></i>
      </#if>
    </div>

    <div class="profile-info">
      <h1>${user.name!"User"}</h1>
      <p class="muted">${user.email!"No email on file"}</p>

      <#if user.location?? && user.location?has_content>
        <p class="muted"><i class="fa-solid fa-location-dot"></i> ${user.location}</p>
      </#if>

      <#if user.bio?? && user.bio?has_content>
        <p style="margin-top:8px; color:#fff;">${user.bio}</p>
      </#if>

      <div class="pill-row">
        <span class="pill"><i class="fa-solid fa-shield"></i> Verified account</span>
        <span class="pill"><i class="fa-solid fa-bag-shopping"></i> Buyer account</span>
      </div>
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
          <span class="label">Location</span>
          <span class="value">${user.location!"—"}</span>
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
          <div class="stat-num">${uploadsCount!0}</div>
          <div class="stat-label">Uploads</div>
        </div>
      </div>
    </div>

  </section>

  <section class="card table-card">
    <div class="card-header">
      <h2>My Purchases</h2>
      <div class="header-actions">
        <a class="btn btn-primary" href="/assets/explore">
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
                <#if p.order?? && p.order.formattedDate??>
                  ${p.order.formattedDate}
                <#else>
                  —
                </#if>
              </td>

              <td>
                <span class="status live">
                  <#if p.asset?? && p.asset.license??>
                    ${p.asset.license}
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
        <a class="btn btn-primary" href="/assets/explore">
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
                  <#if a.license??>
                    ${a.license}
                  <#else>
                    Standard
                  </#if>
                </span>
              </td>
              <td class="actions">
                <#if a.id??>
                  <a class="icon-btn" href="/assets/${a.id}" title="View Asset">
                    <i class="fa-regular fa-eye"></i>
                  </a>
                  <form action="/assets/${a.id}/wishlist/remove" method="post" style="display:inline;">
                    <button class="icon-btn" type="submit" title="Remove from Wishlist">
                      <i class="fa-solid fa-heart" style="color:var(--accent);"></i>
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

</@layout.main>
