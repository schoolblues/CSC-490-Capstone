<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-credit-card"></i>
        </div>
        <div class="profile-info">
            <h1>Checkout</h1>
            <p class="muted">Review your order before confirming.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-primary" href="/cart">
                <i class="fa-solid fa-arrow-left"></i> Back to Cart
            </a>
        </div>
    </section>

    <div class="two-col">

        <section class="card table-card" style="margin-top:0;">
            <div class="card-header">
                <h2>Items</h2>
            </div>

            <#if items?has_content>
                <div class="table-wrap">
                    <table>
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Qty</th>
                                <th style="text-align:right;">Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <#list items as item>
                                <tr>
                                    <td class="title">${item.asset.title!"Untitled"}</td>
                                    <td>${item.quantity}</td>
                                    <td class="actions">$${item.asset.price?string["0.00"]}</td>
                                </tr>
                            </#list>
                        </tbody>
                    </table>
                </div>
            <#else>
                <div class="empty">
                    <div class="empty-state">
                        <i class="fa-solid fa-box-open"></i>
                        <h3>No items in checkout</h3>
                        <a class="btn btn-primary" href="/assets/explore">Explore Assets</a>
                    </div>
                </div>
            </#if>
        </section>

        <aside class="card" style="display:flex; flex-direction:column;">
            <div class="card-header">
                <h2>Summary</h2>
            </div>
            <div class="card-body" style="flex:1; display:flex; flex-direction:column;">
                <div class="row">
                    <span class="label">Subtotal</span>
                    <span class="value">$${subtotal?string["0.00"]}</span>
                </div>
                <div class="row" style="border-bottom:none; margin-top:auto;">
                    <span class="label" style="color:var(--text);">Total</span>
                    <span class="value" style="font-size:1.1rem;">$${subtotal?string["0.00"]}</span>
                </div>

                <#if items?has_content>
                    <form method="post" action="/checkout/create" style="margin-top:16px;">
                        <button class="btn btn-primary" type="submit" style="width:100%; justify-content:center;">
                            <i class="fa-solid fa-circle-check"></i> Confirm Order
                        </button>
                    </form>
                </#if>
            </div>
        </aside>

    </div>

</main>

</@layout.main>
