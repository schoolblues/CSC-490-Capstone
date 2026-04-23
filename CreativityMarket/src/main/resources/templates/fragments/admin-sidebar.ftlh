<#if user?? && (user.admin || user.superAdmin)>

<aside class="admin-sidebar">

  <h3>Admin Panel</h3>

  <a href="/admin/dashboard">Dashboard</a>
  <a href="/admin/moderation">Moderation</a>
  <a href="/admin/users">Users</a>
  <a href="/admin/assets">Assets</a>
  <a href="/admin/bounties">Bounties</a>
  <a href="/admin/categories">Categories</a>
  <a href="/admin/logs">Logs</a>

</aside>

</#if>