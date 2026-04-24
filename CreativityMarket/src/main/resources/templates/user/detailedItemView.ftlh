<#import "/fragments/layout.ftlh" as layout>

<@layout.main
    extraCss='<link rel="stylesheet" href="/css/detailedItemView.css"><link rel="stylesheet" href="/css/sketchfab.css">'
    extraScripts='<script src="https://static.sketchfab.com/api/sketchfab-viewer-1.12.1.js"></script><script src="/js/sketchfabscripts.js"></script>'>

    <div class="item-view-container">
        <div class="gallery-section">
            <div class="main-image-container">
                <iframe
                    id="sketchfab-viewer"
                    src=""
                    data-uid="${(asset.sketchfabUid)!'a5eb5c78e5a14955802e7eb64b76e1a1'}"
                    allow="autoplay; fullscreen; xr-spatial-tracking"
                    xr-spatial-tracking
                    execution-while-out-of-viewport
                    execution-while-not-rendered
                    web-share
                    allowfullscreen
                    mozallowfullscreen="true"
                    webkitallowfullscreen="true"
                    title="3D Model Viewer">
                </iframe>
            </div>

            <div class="thumbnail-row">
                <button class="thumb-nav-btn thumb-prev">
                    <img src="/images/left.webp" alt="Previous" class="thumb-nav-icon">
                </button>
                <div class="thumbnail-container" id="thumbnailContainer"></div>
                <button class="thumb-nav-btn thumb-next">
                    <img src="/images/right.webp" alt="Next" class="thumb-nav-icon">
                </button>
            </div>
        </div>

        <div class="product-info-panel">
            <div class="creator-section">
                <img id="creator-avatar" src="${(asset.creatorAvatarUrl)!'/images/apple.png'}" alt="Creator" class="creator-avatar">
                <span id="creator-name" class="creator-name">${(asset.creatorName)!'SampleCreator'}</span>
            </div>

            <h1 id="product-title" class="product-title">${(asset.title)!'Sample 3D Model Asset'}</h1>
            <div class="product-rating">
                <span class="rating-score"></span>
                <div class="rating-stars">
                    <img src="/images/star.svg" alt="star" class="star-icon">
                    <img src="/images/star.svg" alt="star" class="star-icon">
                    <img src="/images/star.svg" alt="star" class="star-icon">
                    <img src="/images/star.svg" alt="star" class="star-icon">
                    <img src="/images/star.svg" alt="star" class="star-icon">
                </div>
            </div>

            <div class="license-section">
                <label class="license-label">License</label>
                <div class="license-dropdown">
                    <span class="license-text">Select a License</span>
                    <img src="/images/arrow.svg" alt="dropdown" class="dropdown-arrow">
                </div>
            </div>

            <button class="btn-buy">Buy now</button>
            <div class="cart-wishlist-row">
                <button class="btn-cart">Add to cart</button>
                <button class="btn-wishlist">
                    <img src="/images/heart.png" alt="wishlist" class="wishlist-icon">
                </button>
            </div>

            <div class="formats-section">
                <label class="formats-label">Filetype</label>
                <div class="format-icons">
                    <span class="format-badge">${(asset.fileType)!'—'}</span>
                </div>
            </div>

            <div class="creator-actions">
                <button class="creator-action-btn">Commission</button>
                <button class="creator-action-btn">Message</button>
                <button class="creator-action-btn">Follow</button>
            </div>

            <div class="details-section">
                <h3 class="details-title">Model Information</h3>
                <div class="details-grid">
                    <span class="detail-label">Published</span>
                    <span class="detail-value">${(asset.publishedDate)!'August 18, 2018'}</span>
                    <span class="detail-label">Last update</span>
                    <div class="detail-value"><span>${(asset.lastUpdateDate)!'March 17, 2024'}</span></div>
                    <span class="detail-label">License terms</span>
                    <span class="detail-link">Standard License</span>
                    <span class="detail-label">Age rating</span>
                    <span class="detail-value">${(asset.ageRating)!'Not Mature'}</span>
                    <span class="detail-label">Allows usage with AI</span>
                    <span class="detail-value"><#if asset?? && asset.allowsAiUsage>Yes<#else>No</#if> <span class="info-icon"></span></span>
                </div>
                <div class="share-report-row">
                    <button class="share-btn">Share</button>
                    <#if asset.creator??>
                        <button class="report-btn" onclick="document.getElementById('report-modal').style.display='flex'">Report</button>
                    </#if>
                </div>
            </div>
        </div>
    </div>

<#if asset.creator??>
<div id="report-modal" style="display:none; position:fixed; inset:0; z-index:1000; background:rgba(0,0,0,0.65); align-items:center; justify-content:center;">
    <div style="background:#271738; border-radius:4px; padding:24px; width:100%; max-width:460px; box-shadow:0 8px 24px rgba(0,0,0,0.6);">
        <h3 style="color:#fff; margin-bottom:16px;">Report this asset</h3>
        <textarea id="report-reason" placeholder="Describe the issue..." style="width:100%; padding:10px 12px; background:#392d46; border:1px solid #3d2a50; border-radius:3px; color:#fff; font-size:14px; font-family:inherit; resize:vertical; min-height:100px; box-sizing:border-box;"></textarea>
        <div id="report-msg" style="font-size:13px; margin-top:8px; min-height:18px;"></div>
        <div style="display:flex; gap:10px; margin-top:16px; justify-content:flex-end;">
            <button onclick="document.getElementById('report-modal').style.display='none'" style="padding:8px 16px; background:#392d46; border:1px solid #3d2a50; border-radius:3px; color:#fff; cursor:pointer;">Cancel</button>
            <button onclick="submitReport()" style="padding:8px 16px; background:#ab68f3; border:none; border-radius:3px; color:#fff; cursor:pointer;">Submit Report</button>
        </div>
    </div>
</div>

<script>
function submitReport() {
    const reason = document.getElementById('report-reason').value.trim();
    const msg = document.getElementById('report-msg');
    if (!reason) { msg.style.color = '#e74c3c'; msg.textContent = 'Please enter a reason.'; return; }

    fetch('/api/moderation/flag', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({
            reportedUserId: '${asset.creator.id}',
            type: 'ASSET',
            entityId: '${asset.id}',
            reason: reason
        })
    })
    .then(res => {
        if (res.ok) {
            msg.style.color = '#27ae60';
            msg.textContent = 'Report submitted. Thank you.';
            setTimeout(() => { document.getElementById('report-modal').style.display = 'none'; }, 1500);
        } else {
            msg.style.color = '#e74c3c';
            msg.textContent = 'Failed to submit. Are you signed in?';
        }
    })
    .catch(() => { msg.style.color = '#e74c3c'; msg.textContent = 'Something went wrong.'; });
}
</script>
</#if>

</@layout.main>
