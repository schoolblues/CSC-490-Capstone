<form id="assetForm"
      method="post"
      action="/admin/assets/new"
      class="row g-3">

    <input type="hidden" name="id" value="" />

    <!-- TITLE -->
    <div class="col-md-6">
        <label>Title</label>
        <input class="form-control"
               name="title" required>
    </div>

    <!-- DESCRIPTION -->
    <div class="col-md-12">
        <label>Description</label>
        <textarea class="form-control"
                  name="description"></textarea>
    </div>

    <!-- PRICE -->
    <div class="col-md-4">
        <label>Price</label>
        <input type="number"
               class="form-control"
               name="price">
    </div>

    <!-- CATEGORY -->
    <div class="col-md-4">
        <label>Category</label>
        <select class="form-control" name="categoryId">

            <#list categories as category>
                <option value="${category.id}">
                    ${category.name}
                </option>
            </#list>

        </select>
    </div>

    <!-- RATING -->
    <div class="col-md-4">
        <label>Rating</label>
        <input type="number"
               step="0.1"
               class="form-control"
               name="rating">
    </div>

    <!-- SKETCHFAB -->
    <div class="col-md-6">
        <label>Sketchfab UID</label>
        <input class="form-control"
               name="sketchfabUid">
    </div>

    <!-- THUMBNAIL -->
    <div class="col-md-6">
        <label>Thumbnail URL</label>
        <input class="form-control"
               name="thumbnailUrl">
    </div>

    <!-- TEXTURE INFO (example editable fields) -->
    <div class="col-md-6">
        <label>Texture Resolution</label>
        <input class="form-control"
               name="textureResolution">
    </div>

    <div class="col-md-6">
        <label>File Type</label>
        <input class="form-control"
               name="fileType">
    </div>

    <!-- FLAGS -->
    <div class="col-md-12">
        <label>
            <input type="checkbox"
                   name="allowsAiUsage">
            Allows AI Usage
        </label>
    </div>

    <!-- SUBMIT -->
    <div class="col-md-12">
        <button class="btn btn-success">
            Save Asset
        </button>
    </div>

</form>