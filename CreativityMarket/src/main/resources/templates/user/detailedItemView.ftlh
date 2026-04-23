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
                    <button class="report-btn">Report</button>
                </div>
            </div>
        </div>
    </div>

</@layout.main>
