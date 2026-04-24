<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-scroll"></i>
        </div>
        <div class="profile-info">
            <h1>${bounty.title}</h1>
            <p class="muted">
                <#if bounty.createdBy??>Posted by ${bounty.createdBy.name!"Unknown"} · </#if>
                <#if bounty.createdAt??>${bounty.createdAt?string["yyyy-MM-dd"]}</#if>
            </p>
            <div class="pill-row">
                <span class="pill"><i class="fa-solid fa-circle-dollar-to-slot"></i> $${bounty.reward?string["0.00"]}</span>
                <span class="pill"><i class="fa-solid fa-circle"></i> ${bounty.status}</span>
            </div>
        </div>
        <div class="profile-actions">
            <a class="btn btn-ghost" href="/bounties"><i class="fa-solid fa-arrow-left"></i> Back</a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Description</h2>
        </div>
        <div class="card-body">
            <p style="color:#fff; line-height:1.6; white-space:pre-wrap;">${bounty.description}</p>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Details</h2>
        </div>
        <div class="card-body">
            <div class="row">
                <span class="label">Posted by</span>
                <span class="value"><#if bounty.createdBy??>${bounty.createdBy.name!"Unknown"}<#else>—</#if></span>
            </div>
            <div class="row">
                <span class="label">Posted</span>
                <span class="value"><#if bounty.createdAt??>${bounty.createdAt?string["yyyy-MM-dd"]}</#if></span>
            </div>
            <#if bounty.assignedTo??>
                <div class="row">
                    <span class="label">Assigned to</span>
                    <span class="value">${bounty.assignedTo.name!"—"}</span>
                </div>
            </#if>
            <div class="row" style="border-bottom:none;">
                <span class="label">Status</span>
                <span class="value">
                    <span class="status live">${bounty.status}</span>
                </span>
            </div>

            <#if bounty.status == "OPEN">
                <form action="/bounties/${bounty.id}/apply" method="post" style="margin-top:16px;">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-hand-point-up"></i> Apply for this Bounty
                    </button>
                </form>
            </#if>
        </div>
    </section>

</main>

</@layout.main>
