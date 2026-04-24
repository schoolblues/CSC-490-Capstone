<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-scroll"></i>
        </div>
        <div class="profile-info">
            <h1>Available Freelance Bounties</h1>
            <p class="muted">Browse open freelance opportunities and apply to projects that match your skills.</p>
        </div>
        <div class="profile-actions">
            <#if user??>
                <a class="btn btn-primary" href="/bounties/new">
                    <i class="fa-solid fa-plus"></i> Post a Bounty
                </a>
            </#if>
            <a class="btn btn-ghost" href="/bounties/browse">
                <i class="fa-solid fa-magnifying-glass"></i> Advanced Search
            </a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Open Bounties</h2>
            <span class="tag"><#if bounties??>${bounties?size}<#else>0</#if> listings</span>
        </div>

        <#if bounties?? && (bounties?size > 0)>
            <div class="table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Reward</th>
                            <th>Status</th>
                            <th>Posted</th>
                            <th style="text-align:right;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list bounties as bounty>
                            <tr>
                                <td class="title">${bounty.title}</td>
                                <td>$${bounty.reward?string["0.00"]}</td>
                                <td><span class="status live">${bounty.status}</span></td>
                                <td><#if bounty.createdAt??>${bounty.createdAt?string["yyyy-MM-dd"]}</#if></td>
                                <td class="actions">
                                    <a class="btn btn-primary" href="/bounties/${bounty.id}" style="padding:6px 14px; font-size:13px;">
                                        View / Apply
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
                    <i class="fa-solid fa-scroll"></i>
                    <h3>No open bounties</h3>
                    <p>No open bounties at the moment. Check back soon.</p>
                </div>
            </div>
        </#if>
    </section>

</main>

</@layout.main>
