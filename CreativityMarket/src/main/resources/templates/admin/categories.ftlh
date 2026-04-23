<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<div class="container-fluid">

    <h1 class="mb-4">Categories</h1>

    <!-- CREATE BUTTON -->
    <button class="btn btn-primary mb-3"
            data-bs-toggle="modal"
            data-bs-target="#categoryModal"
            onclick="resetCategoryForm()">
        Create Category
    </button>

    <!-- TABLE -->
    <div class="card">
        <div class="card-body">

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Assets</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <#list categories as category>
                    <tr>
                        <td>${category.name}</td>
                        <td>${category.description}</td>

                        <td>
                            ${category.assets?size}
                        </td>

                        <td>
                            <button class="btn btn-sm btn-warning"
                                    onclick="openEditCategory(${category.id}, '${category.name?js_string}', '${category.description?js_string}')">
                                Edit
                            </button>

                            <form action="/admin/categories/delete/${category.id}"
                                  method="post"
                                  style="display:inline;">
                                <button class="btn btn-sm btn-danger">
                                    Delete
                                </button>
                            </form>
                        </td>
                    </tr>
                </#list>
                </tbody>

            </table>

        </div>
    </div>

</div>

<!-- MODAL -->
<div class="modal fade" id="categoryModal" tabindex="-1">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Category Form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">

                <#include "/fragments/category-form.ftlh">

            </div>

        </div>

    </div>
</div>

</@layout.adminLayout>