<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <link rel="stylesheet" href="../Styles/style.css">
</head>
<style>
/* (Keep your existing CSS here or link externally) */
</style>
<body>

    <!-- Minimal header -->
    <header class="cart-header">
        <div class="logo">CreativityMarket</div>
        <div class="steps">
            <span class="active">Cart</span>
            <span>›</span>
            <span>Payment</span>
        </div>
    </header>

    <!-- Main cart layout -->
    <main class="cart-container">

        <!-- Cart items -->
        <section class="cart-items">
            <h2>Shopping cart</h2>

            <#if cartItems?size == 0>
                <p>Your cart is empty.</p>
            <#else>
                <#list cartItems as item>
                    <div class="cart-item">
                        <div class="item-info">
                            <p class="item-title">${item.asset.title}</p>
                            <p class="item-license">
                                <#if item.asset.licenses?size &gt; 0>
                                    ${item.asset.licenses[0].name}
                                <#else>
                                    Standard License
                                </#if>
                            </p>
                        </div>

                        <div class="item-actions">
                            <span class="price">${item.asset.price?string["$#.00"]}</span>
                            <form method="post" action="/cart/remove/${item.id}">
                                <button class="remove" type="submit">Remove</button>
                            </form>
                        </div>
                    </div>
                </#list>
            </#if>
        </section>

        <!-- Summary / payment -->
        <aside class="cart-summary">
            <h3>Order summary</h3>

            <div class="summary-row">
                <span>Subtotal</span>
                <span>
                    <#assign subtotal = 0>
                    <#list cartItems as item>
                        <#assign subtotal += item.asset.price * item.quantity>
                    </#list>
                    ${subtotal?string["$#.00"]}
                </span>
            </div>

            <div class="summary-row total">
                <span>Total</span>
                <strong>${subtotal?string["$#.00"]}</strong>
            </div>

            <form method="post" action="/checkout">
                <button class="checkout-btn" type="submit">
                    Proceed to payment
                </button>
            </form>

            <p class="secure-note">
                🔒 Secure payment
            </p>
        </aside>

    </main>

</body>
</html>