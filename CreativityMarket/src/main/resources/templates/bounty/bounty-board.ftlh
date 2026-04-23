<#import "/fragments/layout.ftlh" as layout>

<@layout.main>
<div class="hero">

  <h1>Available Freelance Bounties</h1>
  <p>Browse open freelance opportunities and apply to projects that match your skills.</p>

  <div class="hero-controls">

    <!-- LEFT: Post Button -->
    <a href="/bounties/new" class="btn primary">
      Post Bounty
    </a>

    <a href="/bounties/browse" class="btn primary">
      Browse All Bounties
    </a>

    <!-- RIGHT: Search -->
    <form action="/bounties/browse" method="get" class="hero-search">
      <input type="text" name="q" placeholder="Search bounties...">
    </form>

  </div>

</div>

<div class="bounty-board">

  <#list bounties[0..4] as bounty>
    <div class="bounty-poster">

      <div class="title">
        ${bounty.title}
      </div>

      <div class="reward">
        $${bounty.reward}
      </div>

      <div class="date">
        ${bounty.createdAt?substring(0, 10)}
      </div>

      <a href="/bounties/${bounty.id}" class="btn small">
        View
      </a>

    </div>
  </#list>

</div>

<footer>
  © 2026 CreativityMarket. All rights reserved.
</footer>

</@layout.main>