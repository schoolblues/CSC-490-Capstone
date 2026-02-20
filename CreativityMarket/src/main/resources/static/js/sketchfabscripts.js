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
        initViewer(detailIframe, detailIframe.dataset.uid);
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