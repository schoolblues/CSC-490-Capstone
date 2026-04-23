// =========================
// ADMIN DASHBOARD CHARTS
// =========================

document.addEventListener("DOMContentLoaded", () => {
    const data = window.__ADMIN_DASHBOARD_DATA__;
    if (!data || typeof Chart === "undefined") return;

    // =====================
    // CASE STATUS PIE
    // =====================
    new Chart(document.getElementById('caseChart'), {
        type: 'pie',
        data: {
            labels: data.caseStatusLabels,
            datasets: [{
                data: data.caseStatusValues
            }]
        }
    });

    // =====================
    // USER ROLE PIE
    // =====================
    new Chart(document.getElementById('roleChart'), {
        type: 'pie',
        data: {
            labels: data.userRoleLabels,
            datasets: [{
                data: data.userRoleValues
            }]
        }
    });

    // =====================
    // MARKET BAR
    // =====================
    new Chart(document.getElementById('marketChart'), {
        type: 'bar',
        data: {
            labels: data.marketLabels,
            datasets: [{
                data: data.marketValues
            }]
        }
    });

    // =====================
    // ACTIVITY LINE
    // =====================
    new Chart(document.getElementById('activityChart'), {
        type: 'line',
        data: {
            labels: data.activityLabels,
            datasets: [{
                label: 'Admin Actions Per Day',
                data: data.activityData,
                borderColor: '#4f46e5',
                tension: 0.3,
                fill: false
            }]
        }
    });

    // =====================
    // TOP ACTIONS BAR
    // =====================
    new Chart(document.getElementById('actionChart'), {
        type: 'bar',
        data: {
            labels: data.actionLabels,
            datasets: [{
                label: 'Top Admin Actions',
                data: data.actionData
            }]
        }
    });
});