<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div class="container">

    <div class="section-title">
        <h2>Your Orders</h2>
        <p>History of your purchases</p>
    </div>

    <#if orders?has_content>

        <#list orders as order>

            <div class="card" style="margin-bottom:20px;">
                <div class="card-body">

                    <h3>Order #${order.id}</h3>

                    <div class="meta">
                        <span>Status: ${order.status}</span>
                        <span>${order.createdAt}</span>
                    </div>

                    <hr>

                    <!-- ITEMS -->
                    <#list order.items as item>
                        <div class="meta">
                            <span>${item.asset.title} (x${item.quantity})</span>
                            <span>$${item.priceAtPurchase?string["#.00"]}</span>
                        </div>
                    </#list>

                    <hr>

                    <div class="meta" style="font-weight:bold;">
                        <span>Total</span>
                        <span>$${order.total?string["#.00"]}</span>
                    </div>

                </div>
            </div>

        </#list>

    <#else>
        <p>You have no orders yet.</p>
    </#if>

</div>

</@layout.main>