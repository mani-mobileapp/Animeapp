Anime App
Overview

This is a simple Android application that fetches and displays anime details and top anime lists using a public API. Users can scroll through anime lists, view details for each anime, and enjoy a modern UI with loading indicators.

Assumptions Made

The API returns valid anime data (title, poster URL, episodes, rating, synopsis, trailer URL).

Users have a stable internet connection; offline mode only displays cached data.

Pagination is supported by the API for top anime lists.

No custom error dialog implemented; only a simple TextView shows errors.

Minimum SDK is 26 and target SDK is 36.

The app uses Room for caching anime data locally.

Features Implemented

Anime List Screen

Displays a paginated list of top anime.

Each anime is shown with poster, title, episodes, and rating.

Smooth scrolling using RecyclerView with DiffUtil + ListAdapter.

Clicking an anime navigates to the detail screen.

Shows a progress bar while loading the list.

Pagination supports loading next pages automatically.

Anime Detail Screen

Shows anime details including title, poster, rating, episodes, synopsis, and trailer.

Trailer plays in a WebView if available; otherwise, poster is displayed.

Shows a progress bar while fetching data.

Displays an error TextView if network/API fails.

Header text added at the top of the screen.

Architecture & Utilities

MVVM architecture using ViewModel + StateFlow.

Repository pattern with network + local cache support.

Network connectivity check with NetworkUtils.

Room database for offline caching.

Glide for image loading with caching support.

UI/UX Improvements

List items designed with CardView and modern padding.

Detail screen uses ScrollView and structured layout.

Proper visibility handling for loading and error states.

Known Limitations

Error handling is basic; no dialog popup implemented yet.

No pull-to-refresh implemented on the list screen.

No search/filter functionality in the anime list.

UI could be further enhanced with animations or Material transitions.

Trailer WebView doesn’t have a full-screen player or controls.

No unit or UI tests included yet.