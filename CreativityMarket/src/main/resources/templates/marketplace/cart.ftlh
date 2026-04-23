<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div class="container">

    <div class="section-title">
        <h2>Your Cart</h2>
        <p>Review your selected assets</p>
    </div>

    <div class="two-col">

        <!-- LEFT: ITEMS -->
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

                            <div style="margin-top:10px; display:flex; gap:10px;">

                                <!-- Update -->
                                <form method="post" action="/cart/update/${item.id}">
                                    <input type="number"
                                           name="quantity"
                                           min="1"
                                           value="${item.quantity}">
                                    <button class="btn">Update</button>
                                </form>

                                <!-- Remove -->
                                <form method="post" action="/cart/remove/${item.id}">
                                    <button class="btn">Remove</button>
                                </form>

                            </div>
                        </div>

                    </div>
                </#list>

            <#else>
                <p class="small">Your cart is empty.</p>
            </#if>

        </div>

        <!-- RIGHT: SUMMARY -->
        <div class="panel">

            <h3>Order Summary</h3>

            <div class="meta">
                <span>Subtotal</span>
                <span>$${subtotal?string["#.00"]}</span>
            </div>

            <hr>

            <div class="meta" style="font-weight: bold;">
                <span>Total</span>
                <span>$${subtotal?string["#.00"]}</span>
            </div>

            <#if items?has_content>
                <form method="get" action="/checkout" style="margin-top:15px;">
                    <button class="btn primary" style="width:100%;">
                        Proceed to Checkout
                    </button>
                </form>
            </#if>

        </div>

    </div>

</div>

</@layout.main>