<form id="categoryForm"
      method="post"
      action="/admin/categories/new"
      class="row g-3">

    <input type="hidden" name="id" value="" />

    <!-- NAME -->
    <div class="col-md-12">
        <label>Name</label>
        <input class="form-control"
               name="name" required>
    </div>

    <!-- DESCRIPTION -->
    <div class="col-md-12">
        <label>Description</label>
        <textarea class="form-control"
                  name="description"></textarea>
    </div>

    <!-- SUBMIT -->
    <div class="col-md-12">
        <button class="btn btn-success">
            Save Category
        </button>
    </div>

</form>