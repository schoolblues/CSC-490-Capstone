<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<div class="container-fluid">

    <h1 class="mb-4">Dashboard</h1>

    <!-- STAT CARDS -->
    <div style="display:grid; grid-template-columns:repeat(4,1fr); gap:16px;" class="mb-4">
        <div class="panel" style="text-align:center;">
            <div style="font-size:36px; font-weight:700; color:#ab68f3;">${userCount}</div>
            <div style="color:#b0b8c1; font-size:13px; margin-top:6px;">Total Users</div>
        </div>
        <div class="panel" style="text-align:center;">
            <div style="font-size:36px; font-weight:700; color:#ab68f3;">${bountyCount}</div>
            <div style="color:#b0b8c1; font-size:13px; margin-top:6px;">Total Bounties</div>
        </div>
        <div class="panel" style="text-align:center;">
            <div style="font-size:36px; font-weight:700; color:#ab68f3;">${categoryCount}</div>
            <div style="color:#b0b8c1; font-size:13px; margin-top:6px;">Categories</div>
        </div>
        <div class="panel" style="text-align:center;">
            <div style="font-size:36px; font-weight:700; color:#e74c3c;">${openCases}</div>
            <div style="color:#b0b8c1; font-size:13px; margin-top:6px;">Open Cases</div>
        </div>
    </div>

    <!-- CHARTS -->
    <div class="admin-charts-row">

        <div class="panel">
            <h2 class="mb-3">Admin Activity</h2>
            <canvas id="chart-activity"></canvas>
        </div>

        <div class="panel">
            <h2 class="mb-3">Cases by Status</h2>
            <canvas id="chart-cases"></canvas>
        </div>

        <div class="panel">
            <h2 class="mb-3">Top Actions</h2>
            <canvas id="chart-actions"></canvas>
        </div>

    </div>

</div>

<script>
const chartDefaults = {
    responsive: true,
    maintainAspectRatio: false,
    plugins: { legend: { display: false } },
    scales: {
        x: { ticks: { color: '#b0b8c1' }, grid: { color: '#3d2a50' } },
        y: { ticks: { color: '#b0b8c1' }, grid: { color: '#3d2a50' } }
    }
};

new Chart(document.getElementById('chart-activity'), {
    type: 'bar',
    data: {
        labels: [<#list actionsPerDay as row>"${row[0]}"<#if row?has_next>, </#if></#list>],
        datasets: [{ data: [<#list actionsPerDay as row>${row[1]}<#if row?has_next>, </#if></#list>], backgroundColor: '#ab68f3' }]
    },
    options: chartDefaults
});

new Chart(document.getElementById('chart-cases'), {
    type: 'doughnut',
    data: {
        labels: [<#list caseStatusData as row>"${row[0]}"<#if row?has_next>, </#if></#list>],
        datasets: [{
            data: [<#list caseStatusData as row>${row[1]}<#if row?has_next>, </#if></#list>],
            backgroundColor: ['#ab68f3', '#27ae60', '#e8954a', '#c0392b']
        }]
    },
    options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: { legend: { labels: { color: '#b0b8c1' } } }
    }
});

new Chart(document.getElementById('chart-actions'), {
    type: 'bar',
    data: {
        labels: [<#list topActions as row>"${row[0]}"<#if row?has_next>, </#if></#list>],
        datasets: [{ data: [<#list topActions as row>${row[1]}<#if row?has_next>, </#if></#list>], backgroundColor: '#ab68f3' }]
    },
    options: chartDefaults
});
</script>

</@layout.adminLayout>
