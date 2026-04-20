<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Admin Dashboard</title>

  <link rel="stylesheet" href="/css/homepage.css">
  <link rel="stylesheet" href="/styles.css" />

<style>
* {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

body {
  margin: 0;
  background: #ffffff;
  color: #333;
}

/* Top Bar */
.topbar {
  height: 60px;
  background: #ffffff;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 30px;
}

.logo {
  font-weight: bold;
  font-size: 18px;
}

.top-nav a {
  margin-left: 20px;
  text-decoration: none;
  color: #000;
}

/* Layout */
.layout {
  display: flex;
  height: calc(100vh - 60px);
}

/* Sidebar */
.sidebar {
  width: 220px;
  background: #3982f0;
  padding: 20px;
  color: #ffffff;
}

.profile-icon {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 4px solid #000;
  margin: 0 auto 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.profile-icon img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  object-fit: cover;
}

.menu {
  list-style: none;
  padding: 0;
}

.menu li {
  padding: 10px 5px;
  border-bottom: 1px solid #1e3e6e;
  cursor: pointer;
}

.menu .active {
  font-weight: bold;
}

.submenu {
  margin-top: 10px;
  font-weight: bold;
}

/* Main Content */
.content {
  flex: 1;
  padding: 30px;
}

/* Cards */
.cards {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
}

.card {
  background: #ffffff;
  height: 160px;
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* Divider */
hr {
  margin: 30px 0;
  border: 1px solid #000;
}

/* Large Chart */
.chart-large {
  height: 300px;
}

.chart-placeholder {
  height: 100%;
  border: 1px solid #ccc;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
</head>

<body>

<nav class="navbar">
    <div class="navbar-left">
        <h1 class="navbar-title">Creativity Market</h1>
        <ul class="navbar-menu">
            <li><a href="/models">3D Models</a></li>
            <li><a href="/printing">3D Printing</a></li>
            <li><a href="/custom">Custom 3D</a></li>
            <li><a href="/designers">Designers</a></li>
            <li><a href="/users/logout">Logout</a></li>
        </ul>
    </div>
    <div class="navbar-right">
        <input type="text" class="search-bar" placeholder="Search the store">
        <button class="search-button">
            <img src="/images/glass.png" alt="Search" class="search-icon">
        </button>
    </div>
</nav>

<div class="layout">

  <!-- Sidebar -->
  <aside class="sidebar">

    <div class="profile-icon">
      <img src="https://media.istockphoto.com/id/588258370/vector/male-avatar-profile-picture-vector.jpg" />
    </div>

    <ul class="menu">
      <li class="menu-item active" data-view="overview">Overview</li>
      <li class="menu-item" data-view="accounts">Accounts</li>

      <li class="submenu">Analytics</li>

      <li class="menu-item" data-view="users">User Distribution</li>
      <li class="menu-item" data-view="traffic">Traffic</li>
      <li class="menu-item" data-view="transactions">Transactions</li>
      <li class="menu-item" data-view="logs">Activity Logs</li>
      <li class="menu-item" data-view="settings">Settings</li>
    </ul>

  </aside>

  <!-- Main Content -->
  <main class="content">

    <section class="cards">

      <div class="card">
        <canvas id="chart1"></canvas>
      </div>

      <div class="card">
        <canvas id="chart2"></canvas>
      </div>

      <div class="card">
        <canvas id="chart3"></canvas>
      </div>

      <div class="card">
        <canvas id="chart4"></canvas>
      </div>

    </section>

    <hr/>

    <section id="content-area"></section>

  </main>

</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>
const chartOptions = {
  responsive: true,
  maintainAspectRatio: false,
  plugins: {
    legend: { display: false }
  }
};

new Chart(document.getElementById('chart1'), {
  type: 'bar',
  data: {
    labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri'],
    datasets: [{
      data: [12,19,7,15,10],
      backgroundColor: '#4f46e5'
    }]
  },
  options: chartOptions
});

new Chart(document.getElementById('chart2'), {
  type: 'line',
  data: {
    labels: ['Jan','Feb','Mar','Apr'],
    datasets: [{
      data: [30,45,28,60],
      borderColor: '#22c55e',
      tension: 0.4
    }]
  },
  options: chartOptions
});

new Chart(document.getElementById('chart3'), {
  type: 'doughnut',
  data: {
    labels: ['Used','Free'],
    datasets: [{
      data: [70,30],
      backgroundColor: ['#f59e0b','#e5e7eb']
    }]
  },
  options: chartOptions
});

new Chart(document.getElementById('chart4'), {
  type: 'pie',
  data: {
    labels: ['Desktop','Mobile','Tablet'],
    datasets: [{
      data: [55,35,10],
      backgroundColor: ['#3b82f6','#10b981','#6366f1']
    }]
  },
  options: chartOptions
});
</script>

<script>
const menuItems = document.querySelectorAll(".menu-item");
const contentArea = document.getElementById("content-area");

const views = {

overview: `
<div class="chart-large">
<div class="chart-placeholder">Main Analytics Chart</div>
</div>
`,

accounts: `
<h2>Accounts</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>User</th><th>Email</th><th>Status</th></tr>
<tr><td>Alice</td><td>alice@mail.com</td><td>Active</td></tr>
<tr><td>Bob</td><td>bob@mail.com</td><td>Suspended</td></tr>
</table>
`,

users: `
<h2>User Distribution</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>Country</th><th>Users</th></tr>
<tr><td>USA</td><td>5200</td></tr>
<tr><td>UK</td><td>2100</td></tr>
</table>
`,

traffic: `
<h2>Traffic</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>Source</th><th>Visits</th></tr>
<tr><td>Organic</td><td>18000</td></tr>
<tr><td>Ads</td><td>7400</td></tr>
</table>
`,

transactions: `
<h2>Transactions</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>ID</th><th>Amount</th><th>Status</th></tr>
<tr><td>#TX01</td><td>$120</td><td>Completed</td></tr>
<tr><td>#TX02</td><td>$89</td><td>Pending</td></tr>
</table>
`,

logs: `
<h2>Activity Logs</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>User</th><th>Action</th><th>Date</th></tr>
<tr><td>Admin</td><td>Updated settings</td><td>2026-01-30</td></tr>
</table>
`,

settings: `
<h2>Settings</h2>
<table border="1" width="100%" cellpadding="8">
<tr><th>Setting</th><th>Status</th></tr>
<tr><td>2FA</td><td>Enabled</td></tr>
<tr><td>Email Alerts</td><td>Disabled</td></tr>
</table>
`

};

function loadView(view){
  contentArea.innerHTML = views[view] || "<p>No data available</p>";
}

menuItems.forEach(item=>{
  item.addEventListener("click",()=>{
    menuItems.forEach(i=>i.classList.remove("active"));
    item.classList.add("active");
    loadView(item.dataset.view);
  });
});

loadView("overview");
</script>

</body>
</html>