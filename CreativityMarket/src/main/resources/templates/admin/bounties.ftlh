<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Bounties Management</h1>

<div class="panel">

    <#if bounties?? && (bounties?size > 0)>

        <table width="100%">
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Creator</th>
                <th>Assigned To</th>
                <th>Status</th>
                <th>Reward</th>
                <th>Actions</th>
            </tr>

            <#list bounties as bounty>
                <tr>
                    <td>${bounty.id}</td>

                    <td>
                        <strong>${bounty.title}</strong>
                    </td>

                    <td>
                        ${bounty.createdBy.name!"Unknown"}
                    </td>

                    <td>
                        <#if bounty.assignedTo??>
                            ${bounty.assignedTo.name}
                        <#else>
                            <span style="color:gray;">Unassigned</span>
                        </#if>
                    </td>

                    <!-- STATUS (with simple color coding) -->
                    <td>
                        <#if bounty.status == "OPEN">
                            <span style="color:green;"><strong>${bounty.status}</strong></span>
                        <#elseif bounty.status == "IN_PROGRESS">
                            <span style="color:orange;"><strong>${bounty.status}</strong></span>
                        <#elseif bounty.status == "COMPLETED">
                            <span style="color:blue;"><strong>${bounty.status}</strong></span>
                        <#else>
                            ${bounty.status}
                        </#if>
                    </td>

                    <td>
                        <#if bounty.reward??>
                            ${bounty.reward}
                        <#else>
                            -
                        </#if>
                    </td>

                    <!-- ACTIONS -->
                    <td>

                        <!-- DELETE -->
                        <form method="post"
                              action="/admin/bounties/${bounty.id}/delete"
                              style="display:inline;"
                              onsubmit="return confirm('Delete this bounty?');">
                            <button type="submit">Delete</button>
                        </form>

                        <!-- OPTIONAL: future update -->
                        <!--
                        <form method="get"
                              action="/admin/bounties/${bounty.id}/edit"
                              style="display:inline;">
                            <button type="submit">Edit</button>
                        </form>
                        -->

                    </td>

                </tr>
            </#list>

        </table>

    <#else>
        <p>No bounties found.</p>
    </#if>

</div>

</@layout.adminLayout>