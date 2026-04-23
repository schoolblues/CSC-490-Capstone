<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div class="container">

  <div class="section-title">
    <h2>My Bounties</h2>
    <p>Track bounties you created and are working on</p>
  </div>

  <!-- CREATED BOUNTIES -->
  <h3>Created Bounties</h3>

  <div class="grid">

    <#if createdBounties?? && (createdBounties?size > 0)>

      <#list createdBounties as bounty>
        <div class="card">

          <div class="card-body">

            <h3>${bounty.title}</h3>

            <div class="meta">
              <span>Status: ${bounty.status}</span>

              <#if bounty.assignedTo??>
                <span>Assigned</span>
              <#else>
                <span>Unassigned</span>
              </#if>
            </div>

            <div class="price">
              $${bounty.reward!0?string["#.##"]}
            </div>

            <br>

            <a href="/bounties/${bounty.id}" class="btn primary">
              View
            </a>

          </div>
        </div>
      </#list>

    <#else>
      <p>You haven't created any bounties yet.</p>
    </#if>

  </div>

  <br><br>

  <!-- ASSIGNED BOUNTIES -->
  <h3>Assigned Bounties</h3>

  <div class="grid">

    <#if assignedBounties?? && (assignedBounties?size > 0)>

      <#list assignedBounties as bounty>
        <div class="card">

          <div class="card-body">

            <h3>${bounty.title}</h3>

            <div class="meta">
              <span>Status: ${bounty.status}</span>
              <span>Working On</span>
            </div>

            <div class="price">
              $${bounty.reward!0?string["#.##"]}
            </div>

            <br>

            <a href="/bounties/${bounty.id}" class="btn primary">
              Continue Work
            </a>

          </div>
        </div>
      </#list>

    <#else>
      <p>You are not assigned to any bounties yet.</p>
    </#if>

  </div>

</div>

</@layout.main>