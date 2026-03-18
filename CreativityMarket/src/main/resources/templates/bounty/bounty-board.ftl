<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Bounty Board</title>
  <link rel="stylesheet" href="/styles.css">
</head>
<body>

  <!-- Navigation -->
  <div class="navbar">
    <div class="logo">CreativityMarket</div>
    <div class="nav">
      <a href="/">Home</a>
      <a href="/bounties">Bounties</a>
      <a href="/my-applications">My Applications</a>
      <#-- Show login/logout dynamically -->
      <#if user??>
        <a href="/logout" class="btn">Logout</a>
      <#else>
        <a href="/login" class="btn">Login</a>
      </#if>
    </div>
  </div>

  <!-- Hero Section -->
  <div class="hero">
    <h1>Available Freelance Bounties</h1>
    <p>Browse open freelance opportunities and apply to projects that match your skills.</p>

    <div class="searchbar">
      <form action="/bounties/search" method="get">
        <input type="text" name="q" placeholder="Search by title, skill, or category...">
        <button type="submit" class="btn primary">Search</button>
      </form>
    </div>
  </div>

  <!-- Main Content -->
  <div class="container">

    <div class="section-title">
      <h2>Open Bounties</h2>
      <p>Showing ${bounties?size} active listings</p>
    </div>

    <div class="grid">
      <#list bounties as bounty>
        <div class="card">
          <div class="thumb">${bounty.title?substring(0, 10)}</div>
          <div class="card-body">
            <h3>${bounty.title}</h3>
            <div class="meta">
              <span>${bounty.description?substring(0, 50)}...</span>
              <span>Status: ${bounty.status}</span>
            </div>
            <div class="price">$${bounty.reward?string["#.##"]}</div>
            <br>
            <a href="/bounty/${bounty.id}" class="btn primary">View / Apply</a>
          </div>
        </div>
      </#list>
      <#if bounties?size == 0>
        <p>No open bounties at the moment.</p>
      </#if>
    </div>

  </div>

  <!-- Footer -->
  <footer>
    © 2026 CreativityMarket. All rights reserved.
  </footer>

</body>
</html>