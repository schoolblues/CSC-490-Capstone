<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<!-- Hero Section -->
<div class="hero">
  <h1>Available Freelance Bounties</h1>
  <p>Browse open freelance opportunities and apply to projects that match your skills.</p>

  <div class="searchbar">
    <form action="/bounties/browse" method="get">
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
          <a href="/bounties/${bounty.id}" class="btn primary">View / Apply</a>
        </div>
      </div>
    </#list>
    <#if bounties?size == 0>
      <p>No open bounties at the moment.</p>
    </#if>
  </div>

</div>

</@layout.main>
