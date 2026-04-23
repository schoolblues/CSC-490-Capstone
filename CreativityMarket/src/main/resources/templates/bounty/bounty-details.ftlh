<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<!-- Main Container -->
<div class="container">

  <!-- Bounty Header -->
  <div class="section-title">
    <h2>${bounty.title}</h2>
    <p>Status: ${bounty.status}</p>
  </div>

  <!-- Bounty Card -->
  <div class="card">

    <div class="card-body">

      <!-- Description -->
      <h3>Description</h3>
      <p>
        <#if bounty.description?has_content>
          ${bounty.description}
        <#else>
          No description provided.
         </#if>
      </p>

      <hr>

      <!-- Reward -->
      <div class="price">
        $${bounty.reward!0?string["#.##"]}
      </div>

      <br>

      <!-- Status Info -->
      <div class="meta">
        <span>Status: ${bounty.status}</span>

        <#if bounty.assignedTo??>
          <span>Assigned</span>
        <#else>
          <span>Unassigned</span>
        </#if>
      </div>

      <br>

      <!-- ACTIONS -->
      <div class="actions">

        <!-- OPEN -->
        <#if bounty.status == "OPEN">

          <form action="/bounties/${bounty.id}/claim" method="post">
            <button class="btn primary">Claim Bounty</button>
          </form>

        </#if>

        <!-- IN PROGRESS -->
        <#if bounty.status == "IN_PROGRESS">

          <p><strong>This bounty is currently in progress.</strong></p>

          <form action="/bounties/${bounty.id}/complete" method="post">
            <button class="btn primary">Mark as Complete</button>
          </form>

        </#if>

        <!-- COMPLETED -->
        <#if bounty.status == "COMPLETED">
          <p><strong>This bounty has been completed.</strong></p>
        </#if>

      </div>

    </div>
  </div>

  <br>

  <a href="/bounties" class="btn">← Back to Bounties</a>

</div>

</@layout.main>