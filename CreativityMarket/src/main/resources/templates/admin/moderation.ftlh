<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Moderation Overview</h1>

<!-- ========================= -->
<!-- ACTIVE MODERATORS -->
<!-- ========================= -->
<div class="panel">
    <h2>Active Moderators</h2>

    <#if moderators?? && (moderators?size > 0)>
        <table width="100%">
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Email</th>
            </tr>

            <#list moderators as mod>
                <tr>
                    <td>${mod.id}</td>
                    <td>${mod.name}</td>
                    <td>${mod.email}</td>
                </tr>
            </#list>
        </table>
    <#else>
        <p>No moderators currently assigned.</p>
    </#if>
</div>

<hr/>

<!-- ========================= -->
<!-- MODERATION CASES -->
<!-- ========================= -->
<div class="panel">
    <h2>Moderation Cases</h2>

    <#if cases?? && (cases?size > 0)>

        <table width="100%">
            <tr>
                <th>ID</th>
                <th>Priority</th>
                <th>Type</th>
                <th>Entity ID</th>
                <th>Reported By</th>
                <th>Assigned To</th>
                <th>Reason</th>
                <th>Status</th>
                <th>Created</th>
                <th>Actions</th>
            </tr>

            <#list cases as c>
                <tr>
                    <td>${c.id}</td>

                    <td>
                        <strong>${c.priority}</strong>
                    </td>

                    <td>${c.targetType! "-"}</td>

                    <td>${c.entityId! "-"}</td>

                    <td>${c.reportedBy.name!"Unknown"}</td>

                    <!-- NEW: Assigned Moderator -->
                    <td>
                        <#if c.handledBy??>
                            ${c.handledBy.name}
                        <#else>
                            <span style="color:gray;">Unassigned</span>
                        </#if>
                    </td>

                    <td>${c.reason}</td>

                    <td>${c.status}</td>

                    <td>${c.createdAt}</td>

                    <td>

                        <!-- ========================= -->
                        <!-- ASSIGN MODERATOR -->
                        <!-- ========================= -->
                        <form method="post" style="margin-bottom:5px;">
                            <select name="userId" onchange="this.form.submit()">
                                <option value="">Assign...</option>

                                <#list moderators as mod>
                                    <option value="${mod.id}"
                                        formaction="/admin/moderation/${c.id}/assign/${mod.id}">
                                        ${mod.name}
                                    </option>
                                </#list>
                            </select>
                        </form>

                        <!-- ========================= -->
                        <!-- ACTION BUTTONS -->
                        <!-- ========================= -->

                        <#if c.status == "OPEN" || c.status == "UNDER_REVIEW">

                            <form method="post"
                                  action="/admin/moderation/${c.id}/resolve"
                                  style="display:inline;">
                                <button type="submit">Resolve</button>
                            </form>

                            <form method="post"
                                  action="/admin/moderation/${c.id}/dismiss"
                                  style="display:inline;">
                                <button type="submit">Dismiss</button>
                            </form>

                        <#else>
                            <span style="color:gray;">Closed</span>
                        </#if>

                    </td>
                </tr>
            </#list>

        </table>

    <#else>
        <p>No moderation cases found.</p>
    </#if>

</div>

</@layout.adminLayout>