<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Admin Dashboard</h1>

<div class="cards">
    <div class="card"><h3>Users</h3><p>${userCount!0}</p></div>
    <div class="card"><h3>Bounties</h3><p>${bountyCount!0}</p></div>
    <div class="card"><h3>Categories</h3><p>${categoryCount!0}</p></div>
</div>

<hr/>

<h2>System Health Overview</h2>

<div class="row">
    <div class="col-md-4"><canvas id="caseChart"></canvas></div>
    <div class="col-md-4"><canvas id="roleChart"></canvas></div>
    <div class="col-md-4"><canvas id="marketChart"></canvas></div>
</div>

<hr/>

<h2>Admin Activity Per Day</h2>
<canvas id="activityChart"></canvas>

<hr/>

<h2>Top Admin Actions</h2>
<canvas id="actionChart"></canvas>

<!-- ONLY THIS SCRIPT BLOCK REMAINS -->
<script>
window.__ADMIN_DASHBOARD_DATA__ = {
    caseStatusLabels: [
        <#list caseStatusData as row>"${row[0]?js_string}"<#if row?has_next>,</#if></#list>
    ],
    caseStatusValues: [
        <#list caseStatusData as row>${row[1]!0}<#if row?has_next>,</#if></#list>
    ],

    userRoleLabels: [
        <#list userRoleData as row>"${row[0]?js_string}"<#if row?has_next>,</#if></#list>
    ],
    userRoleValues: [
        <#list userRoleData as row>${row[1]!0}<#if row?has_next>,</#if></#list>
    ],

    marketLabels: [
        <#list marketData as row>"${row[0]?js_string}"<#if row?has_next>,</#if></#list>
    ],
    marketValues: [
        <#list marketData as row>${row[1]!0}<#if row?has_next>,</#if></#list>
    ],

    activityLabels: [
        <#list actionsPerDay as row>"${row[0]?js_string}"<#if row?has_next>,</#if></#list>
    ],
    activityData: [
        <#list actionsPerDay as row>${row[1]!0}<#if row?has_next>,</#if></#list>
    ],

    actionLabels: [
        <#list topActions as row>"${row[0]?js_string}"<#if row?has_next>,</#if></#list>
    ],
    actionData: [
        <#list topActions as row>${row[1]!0}<#if row?has_next>,</#if></#list>
    ]
};
</script>

</@layout.adminLayout>