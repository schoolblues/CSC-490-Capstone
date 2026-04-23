<#-- Uses your admin layout -->
<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<div class="container-fluid">

    <h1 class="mb-4">Assets</h1>

    <!-- CREATE BUTTON -->
    <button class="btn btn-primary mb-3"
            data-bs-toggle="modal"
            data-bs-target="#assetModal"
            onclick="resetAssetForm()">
        Create Asset
    </button>

    <!-- ASSET TABLE -->
    <div class="card">
        <div class="card-body">

            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Rating</th>
                    <th>Actions</th>
                </tr>
                </thead>

                <tbody>
                <#list assets as asset>
                    <tr>
                        <td>${asset.title}</td>

                        <td>
                            <#if asset.category??>
                                ${asset.category.name}
                            <#else>
                                Unassigned
                            </#if>
                        </td>

                        <td>$${asset.price}</td>

                        <td>${asset.rating}</td>

                        <td>
                            <button class="btn btn-sm btn-warning"
                                    onclick="openEditAsset(${asset.id})">
                                Edit
                            </button>

                            <form action="/admin/assets/delete/${asset.id}"
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
<div class="modal fade" id="assetModal" tabindex="-1">
    <div class="modal-dialog modal-xl">

        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title">Asset Form</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <#include "/fragments/asset-form.ftlh">
            </div>

        </div>

    </div>
</div>

</@layout.adminLayout>