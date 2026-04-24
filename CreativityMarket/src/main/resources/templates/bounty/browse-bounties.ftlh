<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-magnifying-glass"></i>
        </div>
        <div class="profile-info">
            <h1>
                <#if query?? && query?has_content>
                    Results for "${query}"
                <#else>
                    Browse Bounties
                </#if>
            </h1>
            <p class="muted">Find and compare freelance opportunities.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-ghost" href="/bounties"><i class="fa-solid fa-arrow-left"></i> Bounty Board</a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Search</h2>
        </div>
        <div class="card-body">
            <form action="/bounties/browse" method="get" style="display:flex; gap:10px; flex-wrap:wrap; align-items:center;">
                <input type="text" name="q" placeholder="Search bounties..." value="${query!''}"
                       style="flex:1; min-width:200px; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
                <select name="sort" style="padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
                    <option value="newest" <#if sort == "newest">selected</#if>>Newest</option>
                    <option value="oldest" <#if sort == "oldest">selected</#if>>Oldest</option>
                    <option value="highest" <#if sort == "highest">selected</#if>>Highest Reward</option>
                    <option value="lowest" <#if sort == "lowest">selected</#if>>Lowest Reward</option>
                </select>
                <button class="btn btn-primary" type="submit">Search</button>
                <a href="/bounties/browse" class="btn btn-ghost">Show All</a>
            </form>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Results</h2>
            <span class="tag"><#if bounties??>${bounties?size}<#else>0</#if> found</span>
        </div>

        <#if bounties?? && (bounties?size > 0)>
            <div class="table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Reward</th>
                            <th>Creator</th>
                            <th>Date</th>
                            <th style="text-align:right;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list bounties as bounty>
                            <tr>
                                <td class="title">${bounty.title}</td>
                                <td>$${bounty.reward?string["0.00"]}</td>
                                <td><#if bounty.createdBy??>${bounty.createdBy.name!"Unknown"}<#else>—</#if></td>
                                <td><#if bounty.createdAt??>${bounty.createdAt?string["yyyy-MM-dd"]}</#if></td>
                                <td class="actions">
                                    <a class="icon-btn" href="/bounties/${bounty.id}" title="View">
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
                    <i class="fa-solid fa-magnifying-glass"></i>
                    <h3>No bounties found</h3>
                    <p>Try a different search or browse all open bounties.</p>
                    <a class="btn btn-primary" href="/bounties">Bounty Board</a>
                </div>
            </div>
        </#if>
    </section>

</main>

</@layout.main>
