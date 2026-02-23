(function () {
    'use strict';

    function initViewer(iframe, uid) {
        const client = new Sketchfab(iframe);

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

    const detailIframe = document.getElementById('sketchfab-viewer');
    if (detailIframe) {
        const uid = detailIframe.dataset.uid;
        initViewer(detailIframe, uid);

        if (uid) {
            const container = document.getElementById('thumbnailContainer');
            const titleEl = document.getElementById('product-title');
            const creatorNameEl = document.getElementById('creator-name');
            const creatorAvatarEl = document.getElementById('creator-avatar');

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
                        const avatarImages = data.user.avatar.images;
                        if (avatarImages && avatarImages.length > 0) {
                            creatorAvatarEl.src = avatarImages[avatarImages.length - 1].url;
                        }
                    }

                    const images = data.thumbnails && data.thumbnails.images;
                    if (!images || images.length === 0 || !container) return;

                    const sizes = [1920, 1024, 720, 256, 64];
                    sizes.forEach(function (targetWidth, i) {
                        const match = images.find(function (img) { return img.width === targetWidth; });
                        if (!match) return;

                        const thumb = document.createElement('img');
                        thumb.src = match.url;
                        thumb.alt = 'Thumbnail ' + (i + 1);
                        thumb.className = 'thumbnail' + (i === 0 ? ' active' : '');
                        thumb.dataset.index = i;
                        container.appendChild(thumb);
                    });
                });
        }
    }

    const previewPanel = document.getElementById('preview-panel');
    if (previewPanel) {
        const previewIframe = previewPanel.querySelector('iframe');
        let activeUid = null;

        document.querySelectorAll('.item-row[data-uid]').forEach(function (row) {
            row.addEventListener('mouseenter', function () {
                const uid = row.dataset.uid;
                if (uid === activeUid) return;
                activeUid = uid;
                previewIframe.src = '';
                initViewer(previewIframe, uid);
            });
        });
    }

})();