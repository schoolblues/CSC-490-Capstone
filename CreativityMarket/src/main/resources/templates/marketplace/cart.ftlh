<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-cart-shopping"></i>
        </div>
        <div class="profile-info">
            <h1>Your Cart</h1>
            <p class="muted">Review your selected assets before checkout.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-ghost" href="/assets/explore">
                <i class="fa-solid fa-compass"></i> Keep Shopping
            </a>
        </div>
    </section>

    <div class="two-col">

        <section class="card table-card">
            <div class="card-header">
                <h2>Items</h2>
                <span class="tag"><#if items?has_content>${items?size}<#else>0</#if> item<#if !items?has_content || items?size != 1>s</#if></span>
            </div>

            <#if items?has_content>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Type</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th style="text-align:right;">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list items as item>
                                <tr>
                                    <td class="title">
                                        <a href="/assets/${item.asset.id}" style="color:inherit; text-decoration:none;">
                                            ${item.asset.title!"Untitled"}
                                        </a>
                                    </td>
                                    <td>${item.asset.fileType!"—"}</td>
                                    <td>$${item.asset.price?string["0.00"]}</td>
                                    <td>
                                        <form method="post" action="/cart/update/${item.id}" style="display:flex; gap:6px; align-items:center;">
                                            <input type="number" name="quantity" min="1" value="${item.quantity}"
                                                   style="width:70px; padding:6px 8px; border-radius:4px; border:1px solid var(--border); background:var(--card-dark); color:var(--text);">
                                            <button class="icon-btn" type="submit" title="Update">
                                                <i class="fa-solid fa-rotate"></i>
                                            </button>
                                        </form>
                                    </td>
                                    <td class="actions">
                                        <form method="post" action="/cart/remove/${item.id}" style="display:inline;">
                                            <button class="icon-btn" type="submit" title="Remove">
                                                <i class="fa-solid fa-trash"></i>
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            <#else>
                <div class="empty">
                    <div class="empty-state">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <h3>Your cart is empty</h3>
                        <p>Browse the marketplace and add some assets to your cart.</p>
                        <a class="btn btn-primary" href="/assets/explore">Explore Assets</a>
                    </div>
                </div>
            </#if>
        </section>

        <aside class="card">
            <div class="card-header">
                <h2>Order Summary</h2>
                <span class="tag">Checkout</span>
            </div>
            <div class="card-body">
                <div class="row">
                    <span class="label">Subtotal</span>
                    <span class="value">$${subtotal?string["0.00"]}</span>
                </div>
                <div class="row">
                    <span class="label">Tax</span>
                    <span class="value">$0.00</span>
                </div>
                <div class="row" style="border-bottom:none;">
                    <span class="label" style="color:var(--text);">Total</span>
                    <span class="value" style="color:var(--accent); font-size:1.1rem;">$${subtotal?string["0.00"]}</span>
                </div>

                <#if items?has_content>
                    <form method="get" action="/checkout" style="margin-top:16px;">
                        <button class="btn btn-primary" type="submit" style="width:100%; justify-content:center;">
                            <i class="fa-solid fa-credit-card"></i> Proceed to Checkout
                        </button>
                    </form>
                </#if>
            </div>
        </aside>

    </div>

</main>

</@layout.main>
