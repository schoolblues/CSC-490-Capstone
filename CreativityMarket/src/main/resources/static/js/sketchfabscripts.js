(function () {
    'use strict';

    function hasSketchfabClient() {
        return typeof window.Sketchfab === 'function';
    }

    function parseSketchfabUid(value) {
        if (!value) return null;

        const trimmed = value.trim();
        const directUidMatch = trimmed.match(/^[a-zA-Z0-9]{32}$/);
        if (directUidMatch) {
            return directUidMatch[0];
        }

        const modelUrlMatch = trimmed.match(/\/models\/[a-z0-9\-]+-([a-zA-Z0-9]{32})(?:[/?#]|$)/i);
        if (modelUrlMatch) {
            return modelUrlMatch[1];
        }

        const fallbackMatch = trimmed.match(/([a-zA-Z0-9]{32})/);
        return fallbackMatch ? fallbackMatch[1] : null;
    }

    function initViewer(iframe, uid) {
        if (!hasSketchfabClient() || !iframe || !uid) return;

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
            },
            error: function () {
                // Keep pages resilient if a UID is invalid or private.
            }
        });
    }

    function setupDetailViewer() {
        const detailIframe = document.getElementById('sketchfab-viewer');
        if (!detailIframe) return;

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

    function setupHoverPreviewPanel() {
        const previewPanel = document.getElementById('preview-panel');
        if (!previewPanel) return;

        const previewIframe = previewPanel.querySelector('iframe');
        if (!previewIframe) return;

        let activeUid = null;

        document.querySelectorAll('.item-row[data-uid]').forEach(function (row) {
            row.addEventListener('mouseenter', function () {
                const uid = row.dataset.uid;
                if (!uid) return;
                if (uid === activeUid) return;
                activeUid = uid;
                previewIframe.src = '';
                initViewer(previewIframe, uid);
            });
        });

        const firstUidRow = document.querySelector('.item-row[data-uid]');
        if (firstUidRow && firstUidRow.dataset.uid) {
            activeUid = firstUidRow.dataset.uid;
            initViewer(previewIframe, activeUid);
        }
    }

    function setupCardPreviews() {
        document.querySelectorAll('iframe.sketchfab-card-viewer[data-uid]').forEach(function (iframe) {
            const uid = iframe.dataset.uid;
            initViewer(iframe, uid);
        });
    }

    function setupUploadPreview() {
        const embedInput = document.getElementById('embedUrl');
        const uploadIframe = document.getElementById('upload-sketchfab-viewer');
        const helperText = document.getElementById('upload-sketchfab-hint');

        if (!embedInput || !uploadIframe) return;

        let activeUid = null;

        function renderPreviewFromInput() {
            const uid = parseSketchfabUid(embedInput.value);
            if (!uid) {
                if (helperText) {
                    helperText.textContent = 'Paste a Sketchfab model URL or 32-character UID to preview it here.';
                }
                return;
            }

            if (uid === activeUid) return;
            activeUid = uid;
            uploadIframe.src = '';
            initViewer(uploadIframe, uid);

            if (helperText) {
                helperText.textContent = 'Preview loaded for model UID: ' + uid;
            }
        }

        embedInput.addEventListener('input', renderPreviewFromInput);
        renderPreviewFromInput();
    }

    setupDetailViewer();
    setupHoverPreviewPanel();
    setupCardPreviews();
    setupUploadPreview();

})();