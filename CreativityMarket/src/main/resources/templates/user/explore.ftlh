<#import "/fragments/layout.ftlh" as layout>

<@layout.main extraCss='<link rel="stylesheet" href="/css/explore.css">'>

<main class="container">
  <div class="explore-layout">

    <aside class="filter-sidebar">
      <h3>Filters</h3>

      <form method="get" action="/assets/explore">
        <#if keyword?? && keyword?has_content>
          <input type="hidden" name="keyword" value="${keyword}">
        </#if>
        <div class="filter-group">
          <div class="filter-group-label">File Type</div>
          <#list ["STL","OBJ","FBX","GLTF","BLEND","MAX","UNITY","MA","DAE","UASSET","C4D","NIF"] as ft>
          <label class="filter-option">
            <input type="checkbox" name="fileType" value="${ft}"<#if selectedFileTypes?seq_contains(ft)> checked</#if>> <span>${ft}</span>
          </label>
          </#list>
        </div>

        <div class="filter-group">
          <div class="filter-group-label">License</div>
          <label class="filter-option">
            <input type="checkbox" name="license" value="personal"<#if selectedLicenses?seq_contains("personal")> checked</#if>> <span>Personal</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="license" value="commercial"<#if selectedLicenses?seq_contains("commercial")> checked</#if>> <span>Commercial</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="license" value="editorial"<#if selectedLicenses?seq_contains("editorial")> checked</#if>> <span>Editorial</span>
          </label>
        </div>

        <div class="filter-group">
          <div class="filter-group-label">Price</div>
          <label class="filter-option">
            <input type="checkbox" name="price" value="free"<#if selectedPrices?seq_contains("free")> checked</#if>> <span>Free</span>
          </label>
          <label class="filter-option">
            <input type="checkbox" name="price" value="paid"<#if selectedPrices?seq_contains("paid")> checked</#if>> <span>Paid</span>
          </label>
        </div>

        <button type="submit" class="filter-apply-btn">Apply Filters</button>
        <a href="/assets/explore"><button type="button" class="filter-clear-btn">Clear All</button></a>
      </form>
    </aside>

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
            <#assign wishlisted = (wishlistedIds?? && wishlistedIds?seq_contains(a.id))>
            <div class="card" style="position:relative;">
              <div class="thumb" onclick="window.location='/assets/${a.id}'" style="cursor:pointer;">
                <#if a.sketchfabUid?? && a.sketchfabUid?has_content>
                  <iframe
                    src="https://sketchfab.com/models/${a.sketchfabUid}/embed?autostart=1&ui_controls=0&ui_infos=0&ui_inspector=0&ui_stop=0&ui_watermark=1"
                    loading="lazy"
                    allow="autoplay; fullscreen; xr-spatial-tracking"
                    allowfullscreen
                    title="${a.title!'3D Model Preview'}">
                  </iframe>
                <#else>
                  <div>
                    <i class="fa-solid fa-cube" style="font-size:1.6rem; margin-bottom:8px; display:block;"></i>
                    Preview
                  </div>
                </#if>
              </div>

              <form method="post"
                    action="<#if wishlisted>/assets/${a.id}/wishlist/remove<#else>/assets/${a.id}/wishlist</#if>"
                    style="position:absolute; top:10px; right:10px; margin:0;">
                <button type="submit" class="icon-btn" title="<#if wishlisted>Remove from Wishlist<#else>Add to Wishlist</#if>"
                        style="background:rgba(31,18,41,0.85); backdrop-filter:blur(4px);">
                  <i class="<#if wishlisted>fa-solid<#else>fa-regular</#if> fa-heart"
                     <#if wishlisted>style="color:var(--accent);"</#if>></i>
                </button>
              </form>

              <div class="card-body" onclick="window.location='/assets/${a.id}'" style="cursor:pointer;">
                <h3>${a.title!"Untitled"}</h3>

                <p class="small" style="margin-bottom:12px;">
                  <#if a.description?? && a.description?has_content>
                    ${a.description}
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

                <div style="display:flex; justify-content:space-between; align-items:center; gap:8px;" onclick="event.stopPropagation()">
                  <span class="tag">
                    <#if a.license?? && a.license?has_content>
                      ${a.license}
                    <#else>
                      Standard
                    </#if>
                  </span>
                  <form action="/assets/${a.id}/cart" method="post" onclick="event.stopPropagation()">
                    <button type="submit" class="btn btn-primary" style="padding:6px 14px; font-size:13px; font-weight:500;">
                      <i class="fa-solid fa-cart-plus"></i> Add to Cart
                    </button>
                  </form>
                </div>
              </div>
            </div>
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

</@layout.main>
