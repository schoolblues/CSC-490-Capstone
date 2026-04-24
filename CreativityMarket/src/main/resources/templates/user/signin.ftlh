<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div style="flex:1; display:flex; align-items:center; justify-content:center; padding:24px;">
  <div class="panel" style="width:100%; max-width:720px; padding:80px 60px;">
    <h1 style="text-align:center; font-size:1.8rem; margin-bottom:8px; color:var(--text);">Creativity Market</h1>
    <p style="text-align:center; color:var(--muted); margin-bottom:28px;">Sign in to your account</p>

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

      <div style="margin-bottom:20px;">
        <label class="label">Password</label>
        <input name="password" type="password" placeholder="••••••••" required
               style="width:100%; padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);" />
      </div>

      <button class="btn btn-primary" type="submit" style="width:100%; justify-content:center;">
        <i class="fa-solid fa-right-to-bracket"></i> Sign In
      </button>
    </form>

    <p class="small" style="margin-top:16px; text-align:center;">
      Don't have an account? <a href="/signup">Register</a>
    </p>
  </div>
</div>

</@layout.main>
