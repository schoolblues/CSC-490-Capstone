<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Users</h1>

<table width="100%">
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Actions</th>
    </tr>

    <#list users as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.name}</td>
            <td>${u.email}</td>
            <td><strong>${u.role}</strong></td>

            <td>

                <!-- Prevent admin from acting on themselves -->
                <#if user.id != u.id && u.role != "ADMIN" && u.role != "SUPERADMIN">

                    <!-- PROMOTE -->
                    <#if u.role != "MODERATOR">
                        <form method="post"
                              action="/admin/users/${u.id}/promote"
                              style="display:inline;">
                            <button type="submit">Promote</button>
                        </form>
                    </#if>

                    <!-- BAN -->
                    <#if u.role != "BANNED">
                        <form method="post"
                              action="/admin/users/${u.id}/ban"
                              style="display:inline;">
                            <button type="submit">Ban</button>
                        </form>
                    </#if>

                    <!-- SUSPEND -->
                    <#if u.role != "SUSPENDED">
                        <form method="post"
                              action="/admin/users/${u.id}/suspend"
                              style="display:inline;">
                            <button type="submit">Suspend</button>
                        </form>
                    </#if>

                    <!-- DELETE -->
                    <form method="post"
                          action="/admin/users/${u.id}/delete"
                          style="display:inline;"
                          onsubmit="return confirm('Delete this user?');">
                        <button type="submit">Delete</button>
                    </form>

                <#else>
                    <span style="color:gray;">Protected</span>
                </#if>

            </td>
        </tr>
    </#list>

</table>

</@layout.adminLayout>