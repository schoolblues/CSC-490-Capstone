(function () {
    'use strict';

    const iframe = document.getElementById('sketchfab-viewer');

    function initViewer(uid) {
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

    const uid = document.getElementById('sketchfab-viewer').dataset.uid;
    if (uid) initViewer(uid);

})();