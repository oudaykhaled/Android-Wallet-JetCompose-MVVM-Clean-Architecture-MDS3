# Jetpack Compose Essentials in the Wallet Sample Project

## Introduction

Welcome to the "Jetpack Compose Essentials in the Wallet Sample Project" guide! This document is crafted for Android developers, especially those transitioning from traditional XML-based UI development to Jetpack Compose. Jetpack Compose is Android's modern toolkit for building native UIs. It simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.

In this guide, you'll learn about various Jetpack Compose components used in the Wallet Sample project. We will cover each file, highlighting the native Jetpack Compose elements, their purpose, and where they are used in the project. For each element, a link to the official documentation is provided for deeper understanding.

## File Breakdowns

### 1. BillOptionsComposable.kt

This file defines the UI components for displaying bill options.

-   **Jetpack Compose Elements:**
    -   `Column`: Used for vertical layouts. [Documentation](https://developer.android.com/jetpack/compose/layout#column)
    -   `Text`: Displays text. [Documentation](https://developer.android.com/jetpack/compose/text)
    -   `Button`: Represents a clickable button. [Documentation](https://developer.android.com/jetpack/compose/button)

### 2. BottomNavigationBar.kt

Handles the bottom navigation bar of the app.

-   **Jetpack Compose Elements:**
    -   `BottomNavigation`: Container for the bottom navigation bar. [Documentation](https://developer.android.com/jetpack/compose/navigation#bottom-nav)
    -   `BottomNavigationItem`: Individual items in the navigation bar. [Documentation](https://developer.android.com/jetpack/compose/navigation#items)

### 3. CreditCardComposable.kt

Manages the UI for displaying credit card information.

-   **Jetpack Compose Elements:**
    -   `Box`: A container for stacking elements. [Documentation](https://developer.android.com/jetpack/compose/layout#box)
    -   `Image`: Displays images. [Documentation](https://developer.android.com/jetpack/compose/images)

### 4. HomeHeaderComposable.kt

Defines the header section of the home screen.

-   **Jetpack Compose Elements:**
    -   `Row`: Used for horizontal layouts. [Documentation](https://developer.android.com/jetpack/compose/layout#row)
    -   `Icon`: Displays icons. [Documentation](https://developer.android.com/jetpack/compose/icons)

### 5. LoanCardComposable.kt

Creates the UI components for loan card details.

-   **Jetpack Compose Elements:**
    -   `Card`: A card layout. [Documentation](https://developer.android.com/jetpack/compose/material#card)
    -   `Divider`: A horizontal dividing line. [Documentation](https://developer.android.com/jetpack/compose/material#divider)

### 6. HomeScreen.kt

The main UI for the home screen of the app.

-   **Jetpack Compose Elements:**
    -   `Scaffold`: A layout structure for Material Design. [Documentation](https://developer.android.com/jetpack/compose/layouts/material#scaffold)

### 7. MainScreen.kt

Manages the primary screen of the app.

-   **Jetpack Compose Elements:**
    -   `NavHost`: Manages navigation within the app. [Documentation](https://developer.android.com/jetpack/compose/navigation#navhost)

### 8. HomeViewModel.kt

Handles the logic and data for the home screen.

-   **Note:** This file primarily deals with ViewModel logic and may not directly involve Jetpack Compose UI components.

## Conclusion

By going through this guide, you should have gained a fundamental understanding of various Jetpack Compose elements and their applications in a real-world project. The Wallet Sample project serves as a practical example to see these components in action. Remember, the official documentation is an invaluable resource for further exploration and deepening your understanding of Jetpack Compose.