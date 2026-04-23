// =========================
// CATEGORY SYSTEM
// =========================

function resetCategoryForm() {
    const form = document.getElementById('categoryForm');
    if (!form) return;

    form.action = '/admin/categories/new';
    form.reset();

    const idField = form.querySelector('input[name="id"]');
    if (idField) idField.value = "";
}

function openEditCategory(id, name, description) {
    const form = document.getElementById('categoryForm');
    if (!form) return;

    form.action = `/admin/categories/edit/${id}`;

    form.querySelector('input[name="id"]').value = id;
    form.querySelector('input[name="name"]').value = name || "";
    form.querySelector('textarea[name="description"]').value = description || "";

    const modalEl = document.getElementById('categoryModal');
    if (modalEl && window.bootstrap) {
        new bootstrap.Modal(modalEl).show();
    }
}


// =========================
// ASSET SYSTEM
// =========================

async function openEditAsset(id) {
    try {
        const res = await fetch(`/api/admin/assets/${id}`);
        if (!res.ok) throw new Error("Failed fetch asset");

        const asset = await res.json();

        const form = document.getElementById('assetForm');
        if (!form) return;

        form.action = `/admin/assets/edit/${id}`;

        const set = (name, value) => {
            const el = form.querySelector(`[name="${name}"]`);
            if (el) el.value = value ?? "";
        };

        set("id", asset.id);
        set("title", asset.title);
        set("description", asset.description);
        set("price", asset.price);
        set("rating", asset.rating);

        set("fileType", asset.fileType);
        set("tags", asset.tags);
        set("license", asset.license);

        set("thumbnailUrl", asset.thumbnailUrl);
        set("sketchfabUid", asset.sketchfabUid);

        set("creatorName", asset.creatorName);
        set("creatorAvatarUrl", asset.creatorAvatarUrl);

        set("polyCount", asset.polyCount);
        set("vertices", asset.vertices);
        set("polygons", asset.polygons);

        set("geometry", asset.geometry);
        set("uvMapping", asset.uvMapping);

        set("texturesIncluded", asset.texturesIncluded);
        set("textureResolution", asset.textureResolution);
        set("materials", asset.materials);

        form.querySelector('[name="rigged"]').checked = !!asset.rigged;
        form.querySelector('[name="animated"]').checked = !!asset.animated;
        form.querySelector('[name="allowsAiUsage"]').checked = !!asset.allowsAiUsage;

        const cat = form.querySelector('select[name="category.id"]');
        if (cat && asset.category) {
            cat.value = asset.category.id;
        }

        const modalEl = document.getElementById('assetModal');
        if (modalEl && window.bootstrap) {
            new bootstrap.Modal(modalEl).show();
        }

    } catch (err) {
        console.error(err);
        alert("Failed to load asset");
    }
}

function resetAssetForm() {
    const form = document.getElementById('assetForm');
    if (!form) return;

    form.action = '/admin/assets/new';
    form.reset();

    const idField = form.querySelector('input[name="id"]');
    if (idField) idField.value = "";
}