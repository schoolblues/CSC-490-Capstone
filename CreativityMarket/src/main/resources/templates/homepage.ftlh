<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Homepage</title>
    <link rel="stylesheet" href="/css/homepage.css">
    <link rel="stylesheet" href="/css/sketchfab.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-left">
            <h1 class="navbar-title">Creativity Market</h1>
            <ul class="navbar-menu">
                <li><a href="#">Profile</a></li>
            </ul>
        </div>
        <div class="navbar-right">
            <input type="text" class="search-bar" placeholder="Search the store">
            <button class="search-button">
                <img src="/images/glass.png" alt="Search" class="search-icon">
            </button>
        </div>
    </nav>

    <h2 class="featured-label">Featured Items</h2>
    <div class="bannerContainer">
        <button class="nav-button nav-left">
            <img src="/images/left.webp" alt="Previous" class="nav-button-image">
        </button>
        <div class="banner">
            <div class="diagonal-stripe">
                <img src="/images/apple.png" alt="Stripe 1" class="stripe-image">
            </div>
            <div class="diagonal-stripe">
                <img src="/images/banana.png" alt="Stripe 2" class="stripe-image">
            </div>
            <div class="diagonal-stripe">
                <img src="/images/orange.webp" alt="Stripe 3" class="stripe-image">
            </div>
            <div class="diagonal-stripe">
                <img src="/images/pear.png" alt="Stripe 4" class="stripe-image">
            </div>
            <div class="diagonal-stripe"></div>
        </div>
        <button class="nav-button nav-right">
            <img src="/images/right.webp" alt="Next" class="nav-button-image">
        </button>
    </div>

    <div class="item-section-container">
        <div class="item-section">
            <div class="tabs">
                <button class="tab active">New Models</button>
                <button class="tab">Trending Models</button>
                <button class="tab">Trending Free Models</button>
            </div>
            <div class="item-list">
                <#if newAssets?? && (newAssets?size > 0)>
                    <#list newAssets as asset>
                        <div class="item-row" data-uid="${asset.sketchfabUid!''}">
                            <img src="${asset.thumbnailUrl!'/images/apple.png'}" alt="${asset.title!'Uploaded model'}" class="item-thumbnail">
                            <div class="item-info">
                                <h3 class="item-title">
                                    <a href="/asset/${asset.id}" class="item-link">${asset.title!"Untitled Upload"}</a>
                                </h3>
                                <p class="item-creator">by ${asset.creatorName!"Marketplace Creator"}</p>
                                <div class="item-tags">
                                    <#if asset.tags?? && (asset.tags?size > 0)>
                                        <#list asset.tags as tag>
                                            <span class="tag">${tag}</span>
                                        </#list>
                                    <#else>
                                        <span class="tag">${asset.fileType!"Model"}</span>
                                    </#if>
                                </div>
                            </div>
                            <div class="item-price">$${asset.price?string["0.00"]}</div>
                        </div>
                    </#list>
                <#else>
                    <div class="item-row empty-row">
                        <div class="item-info">
                            <h3 class="item-title">No uploads yet</h3>
                            <p class="item-creator">Published customer models will appear here automatically.</p>
                        </div>
                    </div>
                </#if>
            </div>
        </div>
        <div class="preview-panel" id="preview-panel">
            <iframe
                id="preview-viewer"
                allow="autoplay; fullscreen; xr-spatial-tracking"
                allowfullscreen
                mozallowfullscreen="true"
                webkitallowfullscreen="true"
                title="3D Preview">
            </iframe>
        </div>
    </div>

    <div class="category-block-container">
        <#list categories as category>
            <div class="category-block">
                <div class="category-header">
                    <h2 class="category-title">${category.name}</h2>
                    <p class="category-subtitle"><span class="highlight">${category.subtitle}</span></p>
                </div>
                <div class="category-images">
                    <div class="category-main">
                        <img src="${category.mainImageUrl}" alt="${category.name} main" class="category-img">
                    </div>
                    <#if category.previewImageUrls??>
                        <#list category.previewImageUrls as imageUrl>
                            <img src="${imageUrl}" alt="${category.name} preview" class="category-img">
                        </#list>
                    </#if>
                </div>
            </div>
        </#list>
    </div>

    <div class="format-section">
        <div class="format-header">
            <h2 class="format-title">Browse by File Type</h2>
        </div>
        <div class="format-grid">
            <#if fileTypes??>
                <#list fileTypes as fileType>
                    <div class="format-row"><span class="format-name">${fileType[0]}</span><span class="format-ext">${fileType[1]}</span></div>
                </#list>
            </#if>
        </div>
    </div>

    <footer class="footer">
        <div class="footer-content">
            <div class="footer-links">
                <a href="#" class="footer-link">Contact Us</a>
                <a href="#" class="footer-link">Help</a>
                <a href="#" class="footer-link">Student Discounts</a>
                <a href="#" class="footer-link">Forums</a>
                <a href="#" class="footer-link">Terms and Conditions</a>
                <a href="#" class="footer-link">Privacy</a>
            </div>
            <p class="footer-copyright">&copy; 2026 Creativity Market. All rights reserved.</p>
        </div>
    </footer>

    <script src="https://static.sketchfab.com/api/sketchfab-viewer-1.12.1.js"></script>
    <script src="/js/sketchfabscripts.js"></script>
</body>
</html>
