<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>CreativityMarket • Sign In</title>
  <link rel="stylesheet" href="/css/user.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<header class="topbar">
  <a class="brand" href="/">CreativityMarket<span>.</span></a>
  <nav class="nav">
    <a class="active" href="/signin">Login</a>
    <a href="/signup">Create Profile</a>
  </nav>
</header>

<main class="container" style="max-width:520px;">
  <div class="section-title">
    <div>
      <h2>Sign In</h2>
      
    </div>
  </div>

  <div class="panel">
    <#if error??>
      <div class="pill" style="margin-bottom:12px;">
        <i class="fa-solid fa-triangle-exclamation"></i> ${error}
      </div>
    </#if>

    <form method="post" action="/signin">
      <div style="margin-bottom:12px;">
        <label class="label">Email</label>
        <input name="email" type="email" placeholder="email@example.com" required
               style="width:100%; padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
      </div>

      <div style="margin-bottom:12px;">
        <label class="label">Password</label>
        <input name="password" type="password" placeholder="••••••••" required
               style="width:100%; padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
      </div>

      <button class="btn btn-primary" type="submit" style="width:100%; justify-content:center;">
        <i class="fa-solid fa-right-to-bracket"></i> Sign In
      </button>
    </form>

    <p class="small" style="margin-top:12px;">
      Don’t have an account? <a href="/signup" style="color:var(--accent); font-weight:800;">Create Profile</a>
    </p>
  </div>
</main>

<footer class="footer">User-side prototype • CSC-490 Capstone</footer>
</body>
</html>