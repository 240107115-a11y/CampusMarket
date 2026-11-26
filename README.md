# CampusMarket

Android application for university students to buy and sell second-hand study materials, electronics, and dorm items.

## Student Information
- Name: Sulaimon Qalandarov
- Student ID: 240107115
- Course: Mobile Application Development

## Features
- Browse campus items in a grid layout with search and category filters
- View item details and save favorite items
- Online Store tab powered by FakeStore REST API (Retrofit, Coroutines, Coil image loading) with Loading, Success, Error, Empty, and Retry handling
- My Listings tab backed by Room Database with full CRUD (Create, Read, Update, Delete)
- Dark/Light theme toggle saved with Preferences DataStore

## Architecture & Libraries
- MVVM (Model-View-ViewModel) with StateFlow
- UI: Jetpack Compose with Material 3 and Navigation Compose
- Networking: Retrofit, Gson, Coil
- Local Storage: Room Database and Preferences DataStore
