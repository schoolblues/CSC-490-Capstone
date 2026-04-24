<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-circle-check"></i>
        </div>
        <div class="profile-info">
            <h1>Order Confirmed</h1>
            <p class="muted">Order #${orderId} has been placed successfully.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-primary" href="/users/home">
                <i class="fa-solid fa-house"></i> Back to Home
            </a>
        </div>
    </section>

    <div class="card" style="margin-top:18px; text-align:center; padding:48px 24px;">
        <i class="fa-solid fa-circle-check" style="font-size:3rem; color:var(--accent); margin-bottom:16px;"></i>
        <h2 style="margin-bottom:8px;">Thank you for your purchase!</h2>
        <p class="muted" style="margin-bottom:24px;">Your order has been received. You can view your downloads from your profile.</p>
        <div style="display:flex; gap:12px; justify-content:center; flex-wrap:wrap;">
            <a class="btn btn-primary" href="/users/home">
                <i class="fa-solid fa-bag-shopping"></i> View My Purchases
            </a>
            <a class="btn btn-primary" href="/assets/explore">
                <i class="fa-solid fa-compass"></i> Keep Shopping
            </a>
        </div>
    </div>

</main>

</@layout.main>
