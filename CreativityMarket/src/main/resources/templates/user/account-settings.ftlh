<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

  <section class="profile-hero">
    <div class="avatar">
      <#if user.profileImageUrl?? && user.profileImageUrl?has_content>
        <img src="${user.profileImageUrl}" alt="Profile" style="width:100%; height:100%; object-fit:cover;">
      <#else>
        <i class="fa-solid fa-gear"></i>
      </#if>
    </div>
    <div class="profile-info">
      <h1>Account Settings</h1>
      <p class="muted">Update your profile and security settings.</p>
    </div>
    <div class="profile-actions">
      <a class="btn btn-primary" href="/users/home">
        <i class="fa-solid fa-arrow-left"></i> Back to Profile
      </a>
    </div>
  </section>

  <#if saved?? && saved == true>
    <div class="pill" style="margin:16px 0; background:rgba(76, 175, 80, 0.12); border-color:#4caf50; color:#8fdf8f;">
      <i class="fa-solid fa-circle-check"></i> Changes saved successfully.
    </div>
  </#if>

  <#if error?? && error == "password">
    <div class="pill" style="margin:16px 0; background:rgba(231, 76, 60, 0.12); border-color:#e74c3c; color:#ff8888;">
      <i class="fa-solid fa-triangle-exclamation"></i> Current password does not match.
    </div>
  </#if>

  <form method="post" action="/users/account" enctype="multipart/form-data">

    <section class="card table-card">
      <div class="card-header">
        <h2>Profile Image</h2>
        <span class="tag">Avatar</span>
      </div>
      <div class="card-body">
        <div style="display:flex; gap:20px; align-items:center; flex-wrap:wrap;">
          <div class="avatar" style="width:96px; height:96px;">
            <#if user.profileImageUrl?? && user.profileImageUrl?has_content>
              <img src="${user.profileImageUrl}" alt="Current" style="width:100%; height:100%; object-fit:cover;">
            <#else>
              <i class="fa-solid fa-user" style="font-size:2.2rem;"></i>
            </#if>
          </div>
          <div style="flex:1; min-width:260px;">
            <label class="label" style="display:block; margin-bottom:8px;">Upload a new picture</label>
            <input type="file" name="profileImage" accept="image/*"
                   style="width:100%; padding:10px 12px; background:var(--input-bg); border:1px solid var(--input-border); border-radius:4px; color:var(--text);">
          </div>
        </div>
      </div>
    </section>

    <section class="card table-card">
      <div class="card-header">
        <h2>About You</h2>
        <span class="tag">Profile</span>
      </div>
      <div class="card-body">

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">Username</span>
          <input name="name" required value="${user.name!''}" placeholder="Your display name"
                 style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
        </div>

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">Email</span>
          <input type="email" name="email" required value="${user.email!''}" placeholder="you@example.com"
                 style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
        </div>

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">Location</span>
          <input name="location" value="${user.location!''}" placeholder="City, Country"
                 style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
        </div>

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">Bio</span>
          <textarea name="bio" rows="4" placeholder="Tell the community a bit about yourself..."
                    style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text); resize:vertical; font-family:inherit;">${user.bio!''}</textarea>
        </div>

      </div>
    </section>

    <section class="card table-card">
      <div class="card-header">
        <h2>Change Password</h2>
        <span class="tag">Security</span>
      </div>
      <div class="card-body">
        <p class="small" style="margin-bottom:14px;">Leave these blank to keep your current password.</p>

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">Current Password</span>
          <input type="password" name="currentPassword" placeholder="••••••••"
                 style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
        </div>

        <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
          <span class="label">New Password</span>
          <input type="password" name="newPassword" placeholder="••••••••"
                 style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
        </div>
      </div>
    </section>

    <div style="display:flex; gap:10px; margin:18px 0 40px; flex-wrap:wrap;">
      <button class="btn btn-primary" type="submit">
        <i class="fa-solid fa-floppy-disk"></i> Save Changes
      </button>
      <a class="btn btn-ghost" href="/users/home">Cancel</a>
    </div>

  </form>

</main>

</@layout.main>
