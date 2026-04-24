<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-cubes"></i>
        </div>
        <div class="profile-info">
            <h1>My Uploads</h1>
            <p class="muted">Every model you've published to the marketplace.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-primary" href="/upload">
                <i class="fa-solid fa-cloud-arrow-up"></i> Upload New
            </a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Published Models</h2>
            <span class="tag"><#if uploads?? && (uploads?size > 0)>${uploads?size}<#else>0</#if> total</span>
        </div>

        <#if uploads?? && (uploads?size > 0)>
            <div class="table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>File Type</th>
                            <th>Price</th>
                            <th>License</th>
                            <th style="text-align:right;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list uploads as a>
                            <tr>
                                <td class="title">${a.title!"Untitled"}</td>
                                <td>${a.fileType!"—"}</td>
                                <td>
                                    <#if a.price?? && a.price == 0>
                                        Free
                                    <#else>
                                        $${a.price?string["0.00"]}
                                    </#if>
                                </td>
                                <td>
                                    <span class="status live">${a.license!"Standard"}</span>
                                </td>
                                <td class="actions">
                                    <a class="icon-btn" href="/assets/${a.id}" title="View Asset">
                                        <i class="fa-regular fa-eye"></i>
                                    </a>
                                </td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        <#else>
            <div class="empty">
                <div class="empty-state">
                    <i class="fa-solid fa-cloud-arrow-up"></i>
                    <h3>No uploads yet</h3>
                    <p>You haven't published any models. Share your first one with the marketplace.</p>
                    <a class="btn btn-primary" href="/upload">Upload a Model</a>
                </div>
            </div>
        </#if>
    </section>

</main>

</@layout.main>
