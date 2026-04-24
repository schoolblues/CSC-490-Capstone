<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-scroll"></i>
        </div>
        <div class="profile-info">
            <h1>My Bounties</h1>
            <p class="muted">Track bounties you created and are working on.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-primary" href="/bounties/new">
                <i class="fa-solid fa-plus"></i> New Bounty
            </a>
            <a class="btn btn-ghost" href="/bounties">Bounty Board</a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Created Bounties</h2>
            <span class="tag"><#if createdBounties??>${createdBounties?size}<#else>0</#if></span>
        </div>

        <#if createdBounties?? && (createdBounties?size > 0)>
            <div class="table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Reward</th>
                            <th>Status</th>
                            <th>Assigned</th>
                            <th style="text-align:right;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list createdBounties as bounty>
                            <tr>
                                <td class="title">${bounty.title}</td>
                                <td>$${bounty.reward?string["0.00"]}</td>
                                <td><span class="status live">${bounty.status}</span></td>
                                <td>
                                    <#if bounty.assignedTo??>
                                        ${bounty.assignedTo.name!"Assigned"}
                                    <#else>
                                        <span class="muted">Unassigned</span>
                                    </#if>
                                </td>
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
                    <i class="fa-solid fa-scroll"></i>
                    <h3>No bounties yet</h3>
                    <p>You haven't created any bounties.</p>
                    <a class="btn btn-primary" href="/bounties/new">Create One</a>
                </div>
            </div>
        </#if>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Assigned Bounties</h2>
            <span class="tag"><#if assignedBounties??>${assignedBounties?size}<#else>0</#if></span>
        </div>

        <#if assignedBounties?? && (assignedBounties?size > 0)>
            <div class="table-wrap">
                <table>
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Reward</th>
                            <th>Status</th>
                            <th style="text-align:right;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list assignedBounties as bounty>
                            <tr>
                                <td class="title">${bounty.title}</td>
                                <td>$${bounty.reward?string["0.00"]}</td>
                                <td><span class="status live">${bounty.status}</span></td>
                                <td class="actions">
                                    <a class="btn btn-primary" href="/bounties/${bounty.id}" style="padding:6px 14px; font-size:13px;">
                                        Continue
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
                    <i class="fa-regular fa-clipboard"></i>
                    <h3>No assigned bounties</h3>
                    <p>You aren't working on any bounties yet.</p>
                    <a class="btn btn-primary" href="/bounties">Find a Bounty</a>
                </div>
            </div>
        </#if>
    </section>

</main>

</@layout.main>
