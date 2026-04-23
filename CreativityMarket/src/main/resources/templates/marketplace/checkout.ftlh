<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div class="container">

    <div class="section-title">
        <h2>Checkout</h2>
        <p>Review your order before confirming</p>
    </div>

    <div class="two-col">

        <!-- ITEMS -->
        <div class="panel">

            <#if items?has_content>

                <#list items as item>
                    <div class="card">
                        <div class="card-body">

                            <h3>${item.asset.title}</h3>

                            <div class="meta">
                                <span>Qty: ${item.quantity}</span>
                                <span>$${item.asset.price?string["#.00"]}</span>
                            </div>

                        </div>
                    </div>
                </#list>

            <#else>
                <p>No items in checkout.</p>
            </#if>

        </div>

        <!-- SUMMARY -->
        <div class="panel">

            <h3>Summary</h3>

            <div class="meta">
                <span>Subtotal</span>
                <span>$${subtotal?string["#.00"]}</span>
            </div>

            <hr>

            <div class="meta" style="font-weight:bold;">
                <span>Total</span>
                <span>$${subtotal?string["#.00"]}</span>
            </div>

            <form method="post" action="/checkout/create" style="margin-top:15px;">
                <button class="btn primary" style="width:100%;">
                    Confirm Order
                </button>
            </form>

            <a href="/cart" class="btn" style="margin-top:10px; display:block;">
                ← Back to Cart
            </a>

        </div>

    </div>

</div>

</@layout.main>