<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>${bounty.title} — Bounty Details</title>
  <link rel="stylesheet" href="/styles.css">
</head>
<body>

  <div class="navbar">
    <div class="logo">CreativityMarket</div>
    <div class="nav">
      <a href="/">Home</a>
      <a href="/bounties">Bounties</a>
      <a href="/my-applications">My Applications</a>
      <#if user??>
        <a href="/users/logout" class="btn">Logout</a>
      <#else>
        <a href="/login" class="btn">Login</a>
      </#if>
    </div>
  </div>

  <div class="container" style="max-width:800px; margin:40px auto; padding:0 20px;">

    <a href="/bounties" style="display:inline-block; margin-bottom:20px;">&larr; Back to Bounties</a>

    <div class="card" style="padding:30px; border-radius:8px; border:1px solid #ddd;">

      <div style="display:flex; justify-content:space-between; align-items:flex-start;">
        <h1 style="margin:0 0 10px;">${bounty.title}</h1>
        <span class="status-badge status-${bounty.status?lower_case}">${bounty.status}</span>
      </div>

      <div class="price" style="font-size:1.4rem; margin:12px 0;">
        Reward: $${bounty.reward?string["#.##"]}
      </div>

      <hr style="margin:20px 0;">

      <h3>Description</h3>
      <p>${bounty.description}</p>

      <hr style="margin:20px 0;">

      <div style="display:grid; grid-template-columns:1fr 1fr; gap:10px; font-size:0.9rem; color:#555;">
        <div><b>Posted by:</b> User #${bounty.createdBy}</div>
        <div><b>Posted:</b> ${bounty.createdAt?string["yyyy-MM-dd"]}</div>
        <#if bounty.assignedTo??>
          <div><b>Assigned to:</b> User #${bounty.assignedTo}</div>
        </#if>
        <div><b>Status:</b> ${bounty.status}</div>
      </div>

      <#if bounty.status == "OPEN">
        <div style="margin-top:30px;">
          <form action="/bounties/${bounty.id}/apply" method="post">
            <button type="submit" class="btn primary">Apply for this Bounty</button>
          </form>
        </div>
      </#if>

    </div>

  </div>

  <footer>
    © 2026 CreativityMarket. All rights reserved.
  </footer>

</body>
</html>
