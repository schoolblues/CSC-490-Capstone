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

<header class="navbar">
  <div class="logo"><a href="/" style="text-decoration:none; color:inherit;">CreativityMarket</a></div>
  <nav class="nav">
    <a href="/users/home">Profile</a>
    <a href="/users/home">Explore</a>
    <#-- If you have login later, change this -->
    <a href="/signin">Login</a>
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
        File type: ${asset.fileType!"—"} •
        Price:
        <b style="color:var(--accent);">
          $<#if asset.price??>${asset.price?string["0.00"]}<#else>0.00</#if>
        </b>
      </p>

      <div style="margin-top:14px;">
        <a class="btn primary" href="#">Add to Cart</a>
        <a class="btn" href="#">Save</a>
      </div>

      <hr style="border:none; height:1px; background:rgba(255,255,255,.12); margin:16px 0;">

      <h3>License</h3>
      <p class="small">
        <b>
          <#if asset.license?? && asset.license.type??>
            ${asset.license.type}
          <#else>
            —
          </#if>
        </b>
        —
        <#if asset.license?? && asset.license.summary??>
          ${asset.license.summary}
        <#else>
          No license summary available.
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
      <p>More items a user might like.</p>
    </div>
  </div>

  <div class="grid">
    <#-- Optional: show relatedAssets if controller provides it, otherwise show placeholder cards -->
    <#if relatedAssets?? && (relatedAssets?size > 0)>
      <#list relatedAssets as r>
        <a class="card" href="/assets/${r.id}">
          <div class="thumb">Preview</div>
          <div class="card-body">
            <h3>${r.title!"Untitled"}</h3>
            <div class="meta">
              <span>${r.fileType!"—"}</span>
              <span class="price">$<#if r.price??>${r.price?string["0.00"]}<#else>0.00</#if></span>
            </div>
          </div>
        </a>
      </#list>
    <#else>
      <a class="card" href="/users/home">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Kitchen Pack</h3>
          <div class="meta"><span>OBJ</span><span class="price">$14</span></div>
        </div>
      </a>

      <a class="card" href="/users/home">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Modern Sofa</h3>
          <div class="meta"><span>FBX</span><span class="price">$16</span></div>
        </div>
      </a>

      <a class="card" href="/users/home">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Room Decor Set</h3>
          <div class="meta"><span>GLB</span><span class="price">$9</span></div>
        </div>
      </a>

      <a class="card" href="/users/home">
        <div class="thumb">Preview</div>
        <div class="card-body">
          <h3>Desk Lamp</h3>
          <div class="meta"><span>FBX</span><span class="price">$6</span></div>
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