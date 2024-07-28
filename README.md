# jetpack_compose_app

## Features

- **Product Listing Page**
    - Display product logo, name, and price.

- **Product Info Page**
    - Display product logo, name, price, and description, total amount, total cart amount, total select items.
    - Add to Cart button.
    - Buy Now button

- **Cart Functionality**
    - Add items to the cart.
    - Display item count in the cart with a badge.
    - Calculate total amount and size
    - Remove items from the cart.
    - Clear Items from cart when user clicks `Buy Now`

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM (Model-View-ViewModel)
- **Dependency Injection:** Hilt

## Architecture

The project follows the MVVM (Model-View-ViewModel) architecture pattern. The app is modularized to separate concerns and improve maintainability.

## Setup and Installation

BASE_URL is set in local.properties file