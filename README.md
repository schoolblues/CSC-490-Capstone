# CSC-490-Capstone

Team repository for CSC-490 (Capstone)
Team: Meylin Calix, Isaac Hollaway, Elisha Ricketts

## Title
> CreativityMarket

## Team Members
> Meylin Calix 

> Isaac Hollaway 

> Elisha Ricketts

## Description
> CreativityMarket is a web-based marketplace for buying and selling 3D digital assets. Artists can upload and list their 3D models for sale, while buyers can browse, wishlist, and purchase assets directly through the platform.

> The platform integrates with the Sketchfab API, allowing artists to host and display interactive 3D previews of their models directly on each asset's listing page. Assets support multiple file formats including GLB, FBX, and STL, and can be tagged, categorized, and licensed by the creator.

> Users start by creating an account with an email and password, then filling out a profile with a display name, bio, location, and profile picture. Any user can become an Artist and begin uploading assets for sale. Artists set their own prices, descriptions, categories, tags, and licensing terms per listing.

> The platform also features a Bounty Board, where users can post paid commissions for custom 3D work. Artists can browse open bounties and get assigned to fulfill them. Bounties track status through their lifecycle: open, in progress, completed, or cancelled.

> An Admin system provides full platform oversight, including user management (banning, suspending, role assignment), asset and category moderation, a review queue, audit logs of all admin actions, and a reporting dashboard with usage statistics and charts.

## App Functions

1. User & Profile System (Meylin Calix):
    1. Create and manage a user account
        - Register with a name, email, and password
        - Log in and maintain a persistent session
        - Edit profile: display name, bio, profile picture, and location
        - Update account credentials via account settings
    2. Browse and interact with assets
        - Scroll through a gallery-style explore page of all available 3D assets
        - Filter assets by category and file type
        - View a detailed listing page for each asset with its Sketchfab 3D preview
        - See asset details: price, description, tags, license, poly count, textures, and rating
    3. Wishlist
        - Add or remove assets from a personal wishlist
        - Wishlist is accessible from the user's profile

2. Artist & Asset Management (Isaac Hollaway):
    1. Upload and list 3D models for sale
        - Upload assets via the Sketchfab API (supports GLB, FBX, STL)
        - Set a title, description, price, category, tags, and license per listing
        - Provide technical details: poly count, vertices, UV mapping, rigging, animation, textures, and materials
        - Enable or disable AI usage permissions on a per-asset basis
    2. Manage existing uploads
        - View all uploaded assets from a personal "My Uploads" page
        - Edit or delete listings at any time
    3. Bounty Board
        - Browse open bounties posted by other users
        - Get assigned to a bounty and fulfill a custom 3D commission
        - Post your own bounties requesting custom work from other artists
        - Track bounty status: Open, In Progress, Completed, or Cancelled
        - Set a reward amount for each bounty

3. Marketplace & Admin (Elisha Ricketts):
    1. Cart and checkout
        - Add assets to a shopping cart from any listing page
        - View and modify cart contents before purchase
        - Complete checkout and receive an order confirmation
        - View full order history and individual order details
    2. Reviews
        - Leave a star rating (1–5) and written review on any purchased asset
        - Reviews are publicly visible on the asset's listing page
    3. Admin dashboard
        - View a reporting dashboard with platform-wide statistics and charts
        - Manage all users: assign roles (User, Artist, Moderator, Admin, SuperAdmin), ban, or suspend accounts
        - Manage all assets and categories across the platform
        - Review and moderate user-submitted reviews
        - Handle moderation cases with priority levels and status tracking
        - Browse a full audit log of all admin actions taken on the platform
