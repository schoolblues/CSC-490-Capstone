<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>CreativityMarket • Cart</title>
  <link rel="stylesheet" href="/css/homepage.css">
  <link rel="stylesheet" href="/css/user.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">
</head>
<body>

<nav class="navbar">
    <div class="navbar-left">
        <h1 class="navbar-title">Creativity Market</h1>
        <ul class="navbar-menu">
            <li><a href="/assets/explore">Explore</a></li>
            <li><a class="active" href="/cart">Cart</a></li>
            <li><a href="/users/home">Profile</a></li>
            <li><a href="/users/logout">Logout</a></li>
        </ul>
    </div>
    <div class="navbar-right">
        <input type="text" class="search-bar" placeholder="Search the store">
        <button class="search-button">
            <img src="/images/glass.png" alt="Search" class="search-icon">
        </button>
    </div>
</nav>

<main class="container">
<section class="card table-card">
  <div class="card-header">
    <h2>My Cart</h2>
    <span class="tag">${cartCount!0} item<#if cartCount != 1>s</#if></span>
  </div>

  <div class="table-wrap">
    <table>
      <thead>
        <tr>
          <th>Title</th>
          <th>Type</th>
          <th>Price</th>
          <th>License</th>
          <th style="text-align:right;">Actions</th>
        </tr>
      </thead>

      <tbody>
      <#if cart?? && (cart?size > 0)>
        <#list cart as a>
          <tr>
            <td class="title">${a.title}</td>
            <td>${a.fileType}</td>
            <td>$${a.price?string["0.00"]}</td>
            <td>${(a.license)!"Standard"}</td>

            <td class="actions">
              <form action="/assets/${a.id}/cart/remove" method="post">
                <button class="icon-btn" type="submit">
                  <i class="fa-solid fa-trash"></i>
                </button>
              </form>
            </td>
          </tr>
        </#list>
      <#else>
        <tr>
          <td colspan="5" class="empty">
            <div class="empty-state">
              <i class="fa-solid fa-cart-shopping"></i>
              <h3>Your cart is empty</h3>
              <a class="btn btn-primary" href="/assets/explore">Explore Assets</a>
            </div>
          </td>
        </tr>
      </#if>
      </tbody>
    </table>
  </div>

 <#if cart?? && (cart?size > 0)>
  <div style="padding:16px; display:flex; justify-content:flex-end; gap:10px;">
    <form action="/checkout" method="post">
      <button class="btn btn-primary" type="submit">
        <i class="fa-solid fa-credit-card"></i> Proceed to Checkout
      </button>
    </form>
  </div>
</#if>
</section>
</main>

<footer class="footer">
  <p>© ${.now?string("yyyy")} CreativityMarket</p>
</footer>

</body>
</html>