<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

  <section class="card table-card">
    <div class="card-header">
      <h2>Edit Profile</h2>
      <div class="header-actions">
        <a class="btn btn-ghost" href="/users/home"><i class="fa-solid fa-arrow-left"></i> Back</a>
      </div>
    </div>

    <div class="card-body">

      <#if saved?? && saved == true>
        <div class="pill" style="margin-bottom:14px;">
          <i class="fa-solid fa-circle-check"></i> Saved!
        </div>
      </#if>

      <form method="post" action="/users/profile/edit">
        <div class="row" style="border-bottom:none; padding:12px 0;">
          <span class="label">Name</span>
          <span class="value" style="flex:1; text-align:right;">
            <input
              name="name"
              value="${user.name!""}"
              placeholder="Your name"
              style="width: min(420px, 100%); padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);"
              required
            />
          </span>
        </div>

        <div class="row" style="border-bottom:none; padding:12px 0;">
          <span class="label">Email</span>
          <span class="value" style="flex:1; text-align:right;">
            <input
              type="email"
              name="email"
              value="${user.email!""}"
              placeholder="you@example.com"
              style="width: min(420px, 100%); padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);"
              required
            />
          </span>
        </div>

        <div class="row" style="border-bottom:none; padding:12px 0;">
          <span class="label">Member Since</span>
          <span class="value" style="flex:1; text-align:right;">
            <input
              name="createdAt"
              value="${user.createdAt!""}"
              placeholder="YYYY-MM-DD"
              style="width: min(420px, 100%); padding:10px 12px; border-radius:12px; border:1px solid var(--border); background:rgba(255,255,255,0.04); color:var(--text);"
            />
          </span>
        </div>

        <div style="display:flex; gap:10px; margin-top:16px; flex-wrap:wrap;">
          <button class="btn btn-primary" type="submit">
            <i class="fa-solid fa-floppy-disk"></i> Save Changes
          </button>
          <a class="btn btn-ghost" href="/users/home">Cancel</a>
        </div>
      </form>

    </div>
  </section>

</main>

</@layout.main>
