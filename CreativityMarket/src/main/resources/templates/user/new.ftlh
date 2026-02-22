<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Upload Asset</title>
  <link rel="stylesheet" href="/css/user.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<header class="topbar">
  <a class="brand" href="/">CreativityMarket<span>.</span></a>
  <nav class="nav">
    <a href="/users/home">Profile</a>
    <a href="/users/home">Explore</a>
  </nav>
</header>

<main class="container">
  <section class="card table-card">
    <div class="card-header">
      <h2>Upload Asset</h2>
      <div class="header-actions">
        <a class="btn btn-ghost" href="/users/home"><i class="fa-solid fa-arrow-left"></i> Back</a>
      </div>
    </div>

    <div class="card-body">
      <p class="muted" style="margin-bottom:14px;">
        
      </p>

      <form>
        <div style="display:grid; gap:12px; max-width:520px;">
          <input placeholder="Title" style="padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
          <input placeholder="Price (e.g., 12.00)" style="padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
          <input placeholder="File Type (FBX/OBJ/GLB)" style="padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
          <button class="btn btn-primary" type="button"><i class="fa-solid fa-cloud-arrow-up"></i> Upload</button>
        </div>
      </form>
    </div>
  </section>
</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

</body>
</html>