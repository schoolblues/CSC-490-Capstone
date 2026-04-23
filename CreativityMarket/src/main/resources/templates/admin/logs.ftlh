<#import "/fragments/admin-layout.ftlh" as layout>

<@layout.adminLayout>

<h1>Audit Logs</h1>

<!-- ========================= -->
<!-- FILTERS -->
<!-- ========================= -->
<div class="panel">
    <h3>Filter Logs</h3>

    <form method="get" action="/admin/logs">

        <label>Admin ID:</label>
        <input type="number" name="adminId" />

        <label>Action:</label>
        <input type="text" name="action" placeholder="e.g. DELETE_USER"/>

        <label>Entity:</label>
        <input type="text" name="entity" placeholder="e.g. USER"/>

        <label>From:</label>
        <input type="datetime-local" name="from" />

        <label>To:</label>
        <input type="datetime-local" name="to" />

        <button type="submit">Filter</button>
        
        <a href="/admin/logs">
        <button type="button">Reset</button>
        </a>
    </form>
</div>

<hr/>

<!-- ========================= -->
<!-- LOG TABLE -->
<!-- ========================= -->
<div class="panel">

    <#if logs?? && (logs?size > 0)>

        <table width="100%">
            <tr>
                <th>Action</th>
                <th>Admin ID</th>
                <th>Entity</th>
                <th>Target ID</th>
                <th>Time</th>
            </tr>

            <#list logs as log>
                <tr>

                    <!-- ACTION (colored) -->
                    <td>
                        <#if log.action?string?starts_with("DELETE")>
                            <span style="color:red;"><strong>${log.action}</strong></span>
                        <#elseif log.action?string?starts_with("CREATE")>
                            <span style="color:green;"><strong>${log.action}</strong></span>
                        <#elseif log.action?string?starts_with("UPDATE")>
                            <span style="color:orange;"><strong>${log.action}</strong></span>
                        <#elseif log.action?string?starts_with("RESOLVE") || log.action?string?starts_with("DISMISS")>
                            <span style="color:blue;"><strong>${log.action}</strong></span>
                        <#else>
                            ${log.action}
                        </#if>
                    </td>

                    <td>${log.performedBy}</td>

                    <td>
                        <strong>${log.targetEntity}</strong>
                    </td>

                    <td>
                        ${log.targetId!"-"}
                    </td>

                    <td>
                        ${log.timestamp}
                    </td>

                </tr>
            </#list>

        </table>

    <#else>
        <p>No logs found.</p>
    </#if>

</div>

</@layout.adminLayout>