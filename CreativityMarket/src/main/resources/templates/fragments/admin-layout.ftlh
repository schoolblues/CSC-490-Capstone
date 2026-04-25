<#macro adminLayout>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Panel</title>

    <!-- Global styles -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
    <link rel="stylesheet" href="/css/homepage.css">
    <link rel="stylesheet" href="/css/admin.css">
    
</head>

<body>

<!-- TOP NAVBAR -->
<#include "/fragments/navbar.ftlh">

<div class="admin-layout">

    <!-- MAIN CONTENT -->
    <main class="admin-content">
        <#nested>
    </main>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="/js/admin-charts.js" defer></script>
<script src="/js/admin.js" defer></script>

</body>
</html>
</#macro>