<#import "/fragments/layout.ftlh" as layout>

<@layout.main
    extraCss='<link rel="stylesheet" href="/css/sketchfab.css">'
    extraScripts='<script src="https://static.sketchfab.com/api/sketchfab-viewer-1.12.1.js"></script><script src="/js/sketchfabscripts.js"></script>'>

    <h2 class="featured-label">Featured Items</h2>
    <div class="bannerContainer">
        <button class="nav-button nav-left">
            <img src="/images/left.webp" alt="Previous" class="nav-button-image">
        </button>
        <div class="banner">
            <#if featuredAssets?? && (featuredAssets?size > 0)>
                <#list featuredAssets as fa>
                    <div class="diagonal-stripe">
                        <img src="${(fa.thumbnailUrl?has_content)?then(fa.thumbnailUrl, '/images/apple.png')}" alt="Featured ${fa?index + 1}" class="stripe-image" data-uid="${(fa.sketchfabUid)!''}">
                    </div>
                </#list>
                <div class="diagonal-stripe"></div>
            <#else>
                <div class="diagonal-stripe"></div>
                <div class="diagonal-stripe"></div>
                <div class="diagonal-stripe"></div>
                <div class="diagonal-stripe"></div>
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
                        <#assign thumb = (item.thumbnailUrl?has_content)?then(item.thumbnailUrl, '/images/apple.png')>
                        <a href="/asset/${(item.id)!''}" class="item-row" data-uid="${(item.sketchfabUid)!''}" data-thumbnail="${thumb}" style="text-decoration:none;color:inherit;">
                            <img src="${thumb}" alt="Item" class="item-thumbnail">
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
                title="3D Preview"
                style="display:none;">
            </iframe>
            <img id="preview-thumbnail" src="" alt="Preview" style="display:none; width:100%; height:100%; object-fit:cover; border-radius:8px;">
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
                            <img src="${(cat.mainImageUrl?has_content)?then(cat.mainImageUrl, '/images/apple.png')}" alt="Main" class="category-img" data-uid="${(cat.mainImageUid)!''}">
                        </div>
                        <#if cat.previewImageUrls??>
                            <#list cat.previewImageUrls as pi>
                                <#assign piUid = (cat.previewImageUids?? && (cat.previewImageUids?size > pi?index))?then(cat.previewImageUids[pi?index], '')>
                                <img src="${(pi?has_content)?then(pi, '/images/apple.png')}" alt="Preview ${pi?index + 1}" class="category-img" data-uid="${piUid}">
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

</@layout.main>
