<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<!-- Header -->
<div class="hero">
  <h1>
    <#if query?? && query?has_content>
      Results for "${query}"
    <#else>
      Browse Bounties
    </#if>
  </h1>

  <p>Find and compare freelance opportunities.</p>

  <!-- Controls -->
  <div class="searchbar" style="display:flex; justify-content:space-between; align-items:center; gap:10px;">

    <form action="/bounties/browse" method="get" style="flex:1; display:flex; gap:10px;">
      <input type="text" name="q" placeholder="Search bounties..." value="${query!''}">
      <button class="btn primary" type="submit">Search</button>
    </form>

    <form action="/bounties/browse" method="get">
      <input type="hidden" name="q" value="${query!''}">

      <select name="sort" onchange="this.form.submit()">
        <option value="newest" <#if sort == "newest">selected</#if>>Newest</option>
        <option value="oldest" <#if sort == "oldest">selected</#if>>Oldest</option>
        <option value="highest" <#if sort == "highest">selected</#if>>Highest Reward</option>
        <option value="lowest" <#if sort == "lowest">selected</#if>>Lowest Reward</option>
      </select>
    </form>

    <a href="/bounties/browse" class="btn">
        Show All
    </a>

  </div>
</div>

<!-- Content -->
<div class="container">

  <#if bounties?? && (bounties?size > 0)>

    <!-- LIST HEADER -->
    <div style="display:flex; padding:10px 12px; color:var(--muted); font-size:0.85rem;">
      <div style="flex:3;">Title</div>
      <div style="flex:1;">Reward</div>
      <div style="flex:1;">Creator</div>
      <div style="flex:1;">Date</div>
      <div style="width:80px;"></div>
    </div>

    <!-- ROWS -->
    <#list bounties as bounty>

      <div class="browse-row">

        <!-- TITLE -->
        <div style="flex:3; min-width:0;">
          <div style="font-weight:700; color:var(--text); white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
            ${bounty.title}
          </div>
        </div>

        <!-- REWARD -->
        <div style="flex:1;">
          <span style="font-weight:800; color:var(--accent);">
            ${bounty.reward}
          </span>
        </div>

        <!-- CREATOR -->
        <div style="flex:1; color:var(--muted);">
          ${bounty.createdBy.name}
        </div>

        <!-- DATE -->
        <div style="flex:1; color:var(--muted); font-size:0.9rem;">
          ${bounty.createdAt?substring(0, 10)}
        </div>

        <!-- ACTION -->
        <div style="width:80px; text-align:right;">
          <a href="/bounties/${bounty.id}" class="btn">
            View
          </a>
        </div>

      </div>

    </#list>

  <#else>

    <div class="card" style="margin-top:20px;">
      <p>No bounties found.</p>
    </div>

  </#if>

</div>

</@layout.main>