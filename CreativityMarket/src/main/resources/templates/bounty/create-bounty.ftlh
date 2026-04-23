<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<div class="container">

  <div class="section-title">
    <h2>Create a New Bounty</h2>
    <p>Post a freelance task for others to complete</p>
  </div>

  <!-- Create Form -->
  <div class="card">
    <div class="card-body">

      <form action="/bounties/new" method="post">

        <!-- Title -->
        <label>Title</label><br>
        <input type="text" name="title" required
               style="width:100%; padding:8px; margin-bottom:15px;"><br>

        <!-- Description -->
        <label>Description</label><br>
        <textarea name="description" rows="5" required
                  style="width:100%; padding:8px; margin-bottom:15px;"></textarea><br>

        <!-- Reward -->
        <label>Reward ($)</label><br>
        <input type="number" name="reward" step="0.01" min="0" required
               style="width:100%; padding:8px; margin-bottom:15px;"><br>

        <!-- Submit -->
        <button type="submit" class="btn primary">
          Create Bounty
        </button>

      </form>

    </div>
  </div>

  <br>

  <a href="/bounties" class="btn">← Back to Bounties</a>

</div>

</@layout.main>