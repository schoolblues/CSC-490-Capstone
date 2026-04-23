<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<div class="container-fluid">

    <h1 class="mb-4">Dashboard</h1>

    <div class="admin-charts-row">

        <div class="panel">
            <h2 class="mb-3">Sales This Week</h2>
            <canvas id="chart-sales"></canvas>
        </div>

        <div class="panel">
            <h2 class="mb-3">New Users</h2>
            <canvas id="chart-users"></canvas>
        </div>

        <div class="panel">
            <h2 class="mb-3">Storage Usage</h2>
            <canvas id="chart-storage"></canvas>
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

new Chart(document.getElementById('chart-sales'), {
    type: 'bar',
    data: {
        labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
        datasets: [{ data: [12, 19, 7, 15, 10, 22, 18], backgroundColor: '#ab68f3' }]
    },
    options: chartDefaults
});

new Chart(document.getElementById('chart-users'), {
    type: 'line',
    data: {
        labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
        datasets: [{ data: [30, 45, 28, 60, 52, 70], borderColor: '#ab68f3', tension: 0.4, fill: false }]
    },
    options: chartDefaults
});

new Chart(document.getElementById('chart-storage'), {
    type: 'doughnut',
    data: {
        labels: ['Used', 'Free'],
        datasets: [{ data: [70, 30], backgroundColor: ['#ab68f3', '#3d2a50'] }]
    },
    options: { responsive: true, maintainAspectRatio: false, plugins: { legend: { labels: { color: '#b0b8c1' } } } }
});
</script>

</@layout.adminLayout>
