(function () {
    'use strict';

    function initViewer(iframe, uid) {
        var client = new Sketchfab(iframe);

        client.init(uid, {
            autostart:         1,
            ui_controls:       0,
            ui_infos:          0,
            ui_watermark:      0,
            ui_watermark_link: 0,
            ui_hint:           0,
            ui_stop:           0,
            ui_theme:          'dark',
            preload:           1,
            camera:            1,

            success: function (api) {
                api.start();
            }
        });
    }

    var detailIframe = document.getElementById('sketchfab-viewer');
    if (detailIframe) {
        var uid = detailIframe.dataset.uid;
        if (uid) {
            initViewer(detailIframe, uid);

            var container = document.getElementById('thumbnailContainer');
            var titleEl = document.getElementById('product-title');
            var creatorNameEl = document.getElementById('creator-name');
            var creatorAvatarEl = document.getElementById('creator-avatar');

            fetch('https://api.sketchfab.com/v3/models/' + uid)
                .then(function (res) { return res.json(); })
                .then(function (data) {
                    if (data.name && titleEl) {
                        titleEl.textContent = data.name;
                    }

                    if (data.user && creatorNameEl) {
                        creatorNameEl.textContent = data.user.displayName || data.user.username;
                    }

                    if (data.user && data.user.avatar && creatorAvatarEl) {
                        var avatarImages = data.user.avatar.images;
                        if (avatarImages && avatarImages.length > 0) {
                            creatorAvatarEl.src = avatarImages[avatarImages.length - 1].url;
                        }
                    }

                    var images = data.thumbnails && data.thumbnails.images;
                    if (!images || images.length === 0 || !container) return;

                    var sizes = [1920, 1024, 720, 256, 64];
                    sizes.forEach(function (targetWidth, i) {
                        var match = images.find(function (img) { return img.width === targetWidth; });
                        if (!match) return;

                        var thumb = document.createElement('img');
                        thumb.src = match.url;
                        thumb.alt = 'Thumbnail ' + (i + 1);
                        thumb.className = 'thumbnail' + (i === 0 ? ' active' : '');
                        thumb.dataset.index = i;
                        container.appendChild(thumb);
                    });
                });
        }
    }

    function fetchAndSwapThumbnail(uid, imgEl, onSwap) {
        if (!uid || uid.trim() === '' || !imgEl) return;
        fetch('https://api.sketchfab.com/v3/models/' + uid)
            .then(function (res) { return res.json(); })
            .then(function (data) {
                var images = data.thumbnails && data.thumbnails.images;
                if (!images || images.length === 0) return;
                var best = images.reduce(function (a, b) { return b.width > a.width ? b : a; });
                if (best && best.url) {
                    imgEl.src = best.url;
                    if (onSwap) onSwap(best.url);
                }
            })
            .catch(function () {});
    }

    document.querySelectorAll('.item-row[data-uid]').forEach(function (row) {
        fetchAndSwapThumbnail(row.dataset.uid, row.querySelector('.item-thumbnail'), function (url) {
            row.dataset.thumbnail = url;
        });
    });

    document.querySelectorAll('.stripe-image[data-uid]').forEach(function (img) {
        fetchAndSwapThumbnail(img.dataset.uid, img, null);
    });

    document.querySelectorAll('.category-img[data-uid]').forEach(function (img) {
        fetchAndSwapThumbnail(img.dataset.uid, img, null);
    });

    var previewPanel = document.getElementById('preview-panel');
    if (previewPanel) {
        var previewIframe = previewPanel.querySelector('iframe');
        var previewThumb = document.getElementById('preview-thumbnail');
        var activeUid = null;

        document.querySelectorAll('.item-row').forEach(function (row) {
            row.addEventListener('mouseenter', function () {
                var uid = row.dataset.uid;
                var thumbnail = row.dataset.thumbnail;

                if (uid && uid.trim() !== '') {
                    if (uid === activeUid) return;
                    activeUid = uid;
                    if (previewThumb) previewThumb.style.display = 'none';
                    previewIframe.style.display = 'block';
                    previewIframe.src = '';
                    initViewer(previewIframe, uid);
                } else {
                    activeUid = null;
                    previewIframe.style.display = 'none';
                    previewIframe.src = '';
                    if (previewThumb && thumbnail) {
                        previewThumb.src = thumbnail;
                        previewThumb.style.display = 'block';
                    }
                }
            });
        });
    }

    var uidInput = document.getElementById('sketchfab-uid-input');
    if (uidInput) {
        var previewContainer = document.getElementById('upload-preview-container');
        var previewViewer = document.getElementById('upload-preview-viewer');

        function loadUploadPreview() {
            var val = uidInput.value.trim();
            if (!val || !previewContainer || !previewViewer) return;

            previewContainer.style.display = 'block';
            previewViewer.src = '';
            initViewer(previewViewer, val);
        }

        uidInput.addEventListener('change', loadUploadPreview);
        uidInput.addEventListener('blur', loadUploadPreview);
    }

})();