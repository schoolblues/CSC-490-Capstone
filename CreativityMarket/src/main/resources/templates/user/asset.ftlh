<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>CreativityMarket • Asset</title>
  <link rel="stylesheet" href="/css/user.css">
  <link rel="stylesheet" href="/css/sketchfab.css">
</head>
<body>

<header class="topbar">
  <a class="brand" href="/">CreativityMarket<span>.</span></a>
  <nav class="nav">
    <a href="/assets/explore">Explore</a>
    <a href="/users/home">Profile</a>
     <a href="/cart">Cart</a>
    <a href="/users/logout">Logout</a>
  </nav>
</header>

<main class="container">
  <div class="two-col">
    <div class="preview sketchfab-preview">
      <#if asset.sketchfabUid?? && asset.sketchfabUid?has_content>
        <iframe
          id="sketchfab-viewer"
          data-uid="${asset.sketchfabUid}"
          src=""
          allow="autoplay; fullscreen; xr-spatial-tracking"
          allowfullscreen
          mozallowfullscreen="true"
          webkitallowfullscreen="true"
          title="3D Model Viewer">
        </iframe>
      <#else>
        <div>
          <h2>No 3D Preview Available</h2>
          <p class="small">This asset does not have a Sketchfab model linked.</p>
        </div>
      </#if>
    </div>

    <div class="panel">
      <h2 style="margin-bottom:6px;">${asset.title!"Untitled"}</h2>

      <p class="small">
        File type: ${asset.fileType!"—"}
      </p>

      <p class="small" style="margin-top:8px;">
        Price:
        <b style="color:var(--accent);">
          $<#if asset.price??>${asset.price?string["0.00"]}<#else>0.00</#if>
        </b>
      </p>

    <div style="margin-top:14px; display:flex; gap:10px; flex-wrap:wrap;">

  <#if inCart?? && inCart>
    <span class="btn btn-ghost">
      <i class="fa-solid fa-check"></i> Added to Cart
    </span>
  <#else>
    <form action="/assets/${asset.id}/cart" method="post" style="display:inline;">
      <button class="btn btn-primary" type="submit">
        <i class="fa-solid fa-cart-shopping"></i> Add to Cart
      </button>
    </form>
  </#if>

  <#if inWishlist?? && inWishlist>
    <span class="btn btn-ghost">
      <i class="fa-solid fa-heart"></i> Saved
    </span>
  <#else>
    <form action="/assets/${asset.id}/wishlist" method="post" style="display:inline;">
      <button class="btn btn-ghost" type="submit">
        <i class="fa-regular fa-heart"></i> Save
      </button>
    </form>
  </#if>

</div>

      <hr style="border:none; height:1px; background:rgba(255,255,255,.12); margin:16px 0;">

      <h3 style="margin-bottom:8px;">Description</h3>
      <p class="small">
        <#if asset.description??>
          ${asset.description}
        <#else>
          No description available for this asset yet.
        </#if>
      </p>

      <hr style="border:none; height:1px; background:rgba(255,255,255,.12); margin:16px 0;">

      <h3 style="margin-bottom:8px;">License</h3>
      <p class="small">
        <#if asset.license?? && asset.license?has_content>
          ${asset.license}
        <#else>
          Standard License
        </#if>
      </p>

      <hr style="border:none; height:1px; background:rgba(255,255,255,.12); margin:16px 0;">

      <h3>Artist</h3>
      <p class="small">
        <#if asset.creatorName??>
          ${asset.creatorName}
        <#else>
          Unknown Artist
        </#if>
      </p>
    </div>
  </div>

  <div class="section-title">
    <div>
      <h2>Related Assets</h2>
      <p class="muted">More items you might like.</p>
    </div>
  </div>

  <div class="grid">
    <#if relatedAssets?? && (relatedAssets?size > 0)>
      <#list relatedAssets as r>
        <a class="card" href="/assets/${r.id}" style="text-decoration:none; color:inherit;">
          <div class="thumb">Preview</div>
          <div class="card-body">
            <h3>${r.title!"Untitled"}</h3>
            <div class="meta">
              <span>${r.fileType!"—"}</span>
              <span class="price">
                $<#if r.price??>${r.price?string["0.00"]}<#else>0.00</#if>
              </span>
            </div>
          </div>
        </a>
      </#list>
    <#else>
      <a class="card" href="/assets/explore" style="text-decoration:none; color:inherit;">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Kitchen Pack</h3>
          <div class="meta">
            <span>OBJ</span>
            <span class="price">$14.00</span>
          </div>
        </div>
      </a>

      <a class="card" href="/assets/explore" style="text-decoration:none; color:inherit;">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Modern Sofa</h3>
          <div class="meta">
            <span>FBX</span>
            <span class="price">$16.00</span>
          </div>
        </div>
      </a>

      <a class="card" href="/assets/explore" style="text-decoration:none; color:inherit;">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Room Decor Set</h3>
          <div class="meta">
            <span>GLB</span>
            <span class="price">$9.00</span>
          </div>
        </div>
      </a>

      <a class="card" href="/assets/explore" style="text-decoration:none; color:inherit;">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Desk Lamp</h3>
          <div class="meta">
            <span>FBX</span>
            <span class="price">$6.00</span>
          </div>
        </div>
      </a>
    </#if>
  </div>
</main>

<footer>User-side prototype • CSC-490 Capstone</footer>

<script src="https://static.sketchfab.com/api/sketchfab-viewer-1.12.1.js"></script>
<script src="/js/sketchfabscripts.js"></script>
</body>
</html>