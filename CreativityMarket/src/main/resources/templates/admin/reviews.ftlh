<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Reviews Overview</h1>

<div class="panel">
    <h2>Flagged Reviews</h2>

    <#if flaggedReviews?? && (flaggedReviews?size > 0)>
        <table width="100%">
            <tr>
                <th>ID</th>
                <th>Asset</th>
                <th>Reviewer</th>
                <th>Rating</th>
                <th>Body</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <#list flaggedReviews as r>
                <tr>
                    <td>${r.id}</td>
                    <td>
                        <#if r.asset??>
                            <a href="/assets/${r.asset.id}" style="color:#ab68f3;">${r.asset.title!"(untitled)"}</a>
                        <#else>—</#if>
                    </td>
                    <td>${r.user.name!"—"}</td>
                    <td>${r.rating}/5</td>
                    <td style="max-width:300px;">${r.body!""}</td>
                    <td>${r.formattedDate}</td>
                    <td style="white-space:nowrap;">
                        <form method="post" action="/admin/reviews/${r.id}/clear-flag" style="display:inline;">
                            <button type="submit" class="btn btn-sm btn-success">Clear Flag</button>
                        </form>
                        <form method="post" action="/admin/reviews/${r.id}/delete" style="display:inline;">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete this review?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </table>
    <#else>
        <p>No flagged reviews. Everything's clean.</p>
    </#if>
</div>

<hr/>

<div class="panel">
    <h2>All Reviews</h2>

    <#if reviews?? && (reviews?size > 0)>
        <table width="100%">
            <tr>
                <th>ID</th>
                <th>Asset</th>
                <th>Reviewer</th>
                <th>Rating</th>
                <th>Body</th>
                <th>Flagged</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <#list reviews as r>
                <tr>
                    <td>${r.id}</td>
                    <td>
                        <#if r.asset??>
                            <a href="/assets/${r.asset.id}" style="color:#ab68f3;">${r.asset.title!"(untitled)"}</a>
                        <#else>—</#if>
                    </td>
                    <td>${r.user.name!"—"}</td>
                    <td>${r.rating}/5</td>
                    <td style="max-width:300px;">${r.body!""}</td>
                    <td><#if r.flagged><span style="color:#e74c3c;">Yes</span><#else>No</#if></td>
                    <td>${r.formattedDate}</td>
                    <td style="white-space:nowrap;">
                        <form method="post" action="/admin/reviews/${r.id}/delete" style="display:inline;">
                            <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Delete this review?')">Delete</button>
                        </form>
                    </td>
                </tr>
            </#list>
        </table>
    <#else>
        <p>No reviews yet.</p>
    </#if>
</div>

</@layout.adminLayout>
