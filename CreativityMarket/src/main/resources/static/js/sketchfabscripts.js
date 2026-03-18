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

    var previewPanel = document.getElementById('preview-panel');
    if (previewPanel) {
        var previewIframe = previewPanel.querySelector('iframe');
        var activeUid = null;

        document.querySelectorAll('.item-row[data-uid]').forEach(function (row) {
            row.addEventListener('mouseenter', function () {
                var uid = row.dataset.uid;
                if (uid === activeUid) return;
                activeUid = uid;
                previewIframe.src = '';
                initViewer(previewIframe, uid);
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