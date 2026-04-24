<#import "/fragments/layout.ftlh" as layout>

<@layout.main>

<main class="container">

    <section class="profile-hero">
        <div class="avatar">
            <i class="fa-solid fa-plus"></i>
        </div>
        <div class="profile-info">
            <h1>Create a New Bounty</h1>
            <p class="muted">Post a freelance task for others to complete.</p>
        </div>
        <div class="profile-actions">
            <a class="btn btn-ghost" href="/bounties"><i class="fa-solid fa-arrow-left"></i> Back</a>
        </div>
    </section>

    <section class="card table-card">
        <div class="card-header">
            <h2>Bounty Details</h2>
        </div>
        <div class="card-body">
            <form action="/bounties/new" method="post">

                <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
                    <span class="label">Title</span>
                    <input type="text" name="title" required placeholder="e.g. Need a low-poly spaceship"
                           style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
                </div>

                <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
                    <span class="label">Description</span>
                    <textarea name="description" rows="6" required placeholder="Describe what you need, deadlines, style references..."
                              style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text); font-family:inherit; resize:vertical;"></textarea>
                </div>

                <div class="row" style="flex-direction:column; align-items:stretch; gap:8px; border-bottom:none;">
                    <span class="label">Reward ($)</span>
                    <input type="number" name="reward" step="0.01" min="0" required placeholder="0.00"
                           style="width:100%; padding:10px 12px; border-radius:4px; border:1px solid var(--input-border); background:var(--input-bg); color:var(--text);">
                </div>

                <div style="display:flex; gap:10px; margin-top:16px; flex-wrap:wrap;">
                    <button type="submit" class="btn btn-primary">
                        <i class="fa-solid fa-paper-plane"></i> Create Bounty
                    </button>
                    <a class="btn btn-ghost" href="/bounties">Cancel</a>
                </div>
            </form>
        </div>
    </section>

</main>

</@layout.main>
