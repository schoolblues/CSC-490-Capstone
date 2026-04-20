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
                <li><a href="/">Home</a></li>
                <li><a href="/users/home">Profile</a></li>
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

    <h2 class="featured-label">Featured Items</h2>
    <div class="bannerContainer">
        <button class="nav-button nav-left">
            <img src="/images/left.webp" alt="Previous" class="nav-button-image">
        </button>
        <div class="banner">
            <#if featuredAssets?? && (featuredAssets?size > 0)>
                <#list featuredAssets as fa>
                    <div class="diagonal-stripe">
                        <img src="${(fa.thumbnailUrl)!'/images/apple.png'}" alt="Featured ${fa?index + 1}" class="stripe-image">
                    </div>
                </#list>
                <div class="diagonal-stripe"></div>
            <#else>
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
            </#if>
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
                    <#list newAssets as item>
                        <a href="/asset/${(item.id)!''}" class="item-row" data-uid="${(item.sketchfabUid)!''}" style="text-decoration:none;color:inherit;">
                            <img src="${(item.thumbnailUrl)!'/images/apple.png'}" alt="Item" class="item-thumbnail">
                            <div class="item-info">
                                <h3 class="item-title">${(item.title)!'Untitled'}</h3>
                                <div class="item-tags">
                                    <#if item.tags?? && item.tags?has_content>
                                        <#list item.tags?split(",") as t>
                                            <span class="tag">${t?trim}</span>
                                        </#list>
                                    </#if>
                                </div>
                            </div>
                            <div class="item-price">${(item.formattedPrice)!'$0.00'}</div>
                        </a>
                    </#list>
                <#else>
                    <div class="item-row" data-uid="dGUVoNVSMYpGGkpuCo5jFMnGqTI">
                        <img src="/images/apple.png" alt="Item" class="item-thumbnail">
                        <div class="item-info">
                            <h3 class="item-title">Sample Item One</h3>
                            <div class="item-tags">
                                <span class="tag">Art</span>
                                <span class="tag">Digital</span>
                                <span class="tag">Creative</span>
                            </div>
                        </div>
                        <div class="item-price">$9.99</div>
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
        <#if categories?? && (categories?size > 0)>
            <#list categories as cat>
                <div class="category-block">
                    <div class="category-header">
                        <h2 class="category-title">${(cat.name)!'CATEGORY'}</h2>
                        <p class="category-subtitle"><span class="highlight">${(cat.subtitle)!'Digital Art Assets'}</span></p>
                    </div>
                    <div class="category-images">
                        <div class="category-main">
                            <img src="${(cat.mainImageUrl)!'/images/apple.png'}" alt="Main" class="category-img">
                        </div>
                        <#if cat.previewImageUrls??>
                            <#list cat.previewImageUrls as pi>
                                <img src="${pi}" alt="Preview ${pi?index + 1}" class="category-img">
                            </#list>
                        </#if>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="category-block">
                <div class="category-header">
                    <h2 class="category-title">SAMPLE CATEGORY</h2>
                    <p class="category-subtitle"><span class="highlight">Digital Art Assets</span></p>
                </div>
                <div class="category-images">
                    <div class="category-main">
                        <img src="/images/apple.png" alt="Main" class="category-img">
                    </div>
                    <img src="/images/banana.png" alt="Preview 1" class="category-img">
                    <img src="/images/orange.webp" alt="Preview 2" class="category-img">
                    <img src="/images/pear.png" alt="Preview 3" class="category-img">
                </div>
            </div>
        </#if>
    </div>

    <div class="format-section">
        <div class="format-header">
            <h2 class="format-title">Browse by File Type</h2>
        </div>
        <div class="format-grid">
            <#if fileTypes?? && (fileTypes?size > 0)>
                <#list fileTypes as ft>
                    <div class="format-row"><span class="format-name">${ft[0]}</span><span class="format-ext">${ft[1]}</span></div>
                </#list>
            <#else>
                <div class="format-row"><span class="format-name">Blender</span><span class="format-ext">.blend</span></div>
                <div class="format-row"><span class="format-name">OBJ</span><span class="format-ext">.obj, .mtl</span></div>
                <div class="format-row"><span class="format-name">FBX</span><span class="format-ext">.fbx</span></div>
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
