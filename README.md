# OtakuJikan - Anime Discovery Android App

## Overview
OtakuJikan is an Android application that allows users to discover and explore anime content. The app fetches data from the Jikan API (MyAnimeList API) to display top anime listings and detailed information about individual anime series.

## Project Structure
The project follows a multi-module architecture with clean separation of concerns:

- **app**: Main application module containing UI components and business logic
- **core**: Core module containing network layer, database, and shared utilities

## Architecture
The application follows **MVVM (Model-View-ViewModel)** architecture with **Clean Architecture** principles:

- **Presentation Layer**: Activities, Fragments, and ViewModels
- **Domain Layer**: Use Cases, Repositories, and Models
- **Data Layer**: Network API, Local Database, and Data Sources

## Features Implemented

### 1. Anime Discovery
- **Top Anime List**: Displays a grid of top-rated anime from MyAnimeList
- **Grid Layout**: 3-column grid layout for optimal viewing experience
- **Pull-to-Refresh**: SwipeRefreshLayout for refreshing anime data
- **Responsive Design**: Adapts to different screen sizes

### 2. Anime Details
- **Comprehensive Information**: Title, episodes, rating, type, and synopsis
- **Poster Display**: High-quality anime poster images
- **Genre Tags**: Horizontal scrolling list of anime genres
- **YouTube Trailer Integration**: Embedded YouTube player for anime trailers
- **Navigation**: Back navigation to return to the anime list

### 3. Data Management
- **Offline Support**: Local Room database caching for top anime data
- **Network State Handling**: Graceful handling of network connectivity
- **Error Handling**: Comprehensive error states and user feedback
- **Data Persistence**: Cached data survives app restarts

### 4. User Experience
- **Loading States**: Visual feedback during data fetching
- **Error Messages**: Toast notifications for network errors
- **Smooth Navigation**: Fragment-based navigation with shared ViewModels
- **Modern UI**: Material Design components and custom styling

## Technical Implementation

### Dependencies
- **Dagger Hilt**: Dependency injection framework
- **Retrofit**: HTTP client for API communication
- **Room**: Local database for data persistence
- **Coroutines**: Asynchronous programming
- **Data Binding**: View binding and data binding
- **Navigation Component**: Fragment navigation
- **Glide**: Image loading and caching
- **YouTube Player**: Embedded video playback

### API Integration
- **Base URL**: `https://api.jikan.moe/v4/`
- **Endpoints**:
  - `GET /top/anime` - Fetch top anime list
  - `GET /anime/{id}` - Fetch anime details by ID
- **Response Handling**: JSON parsing with Gson
- **Error Codes**: HTTP status code handling (401, 404, 5xx)

### Database Schema
- **AnimeEntity**: Stores top anime data as JSON
- **Table**: `top_anime`
- **Primary Key**: Auto-incrementing ID
- **Data**: JSON representation of anime data

### State Management
- **UiState**: Sealed class for UI states (Loading, Success, Error, SessionExpired)
- **SharedViewModel**: Communication between fragments
- **Repository Pattern**: Abstraction over data sources

## Assumptions Made

### 1. API Assumptions
- **Jikan API Availability**: Assumes the Jikan API is always accessible and responsive
- **Data Structure**: Assumes consistent JSON response structure from the API
- **Rate Limiting**: No explicit handling of API rate limits
- **Authentication**: No authentication required for public anime data

### 2. User Experience Assumptions
- **Internet Connectivity**: Users have reliable internet access for initial data loading
- **Device Capabilities**: Assumes devices support modern Android features (API 24+)
- **Screen Orientation**: Optimized for portrait mode
- **Memory Constraints**: Assumes sufficient device memory for image caching

### 3. Technical Assumptions
- **Android Version**: Minimum SDK 24 (Android 7.0)
- **Device Performance**: Assumes devices can handle image loading and video playback
- **Storage**: Assumes sufficient storage for app data and cached images
- **Permissions**: Internet and network state permissions are granted

## Known Limitations

### 1. Functional Limitations
- **Limited Search**: No search functionality for finding specific anime
- **No User Accounts**: No personalization or watchlist features
- **Limited Content**: Only shows top anime, no browsing by categories
- **No Offline Mode**: Anime details require internet connection
- **No Favorites**: Users cannot save or favorite anime

### 2. Technical Limitations
- **Single Data Source**: Only integrates with Jikan API (MyAnimeList)
- **Limited Caching**: Only top anime list is cached, not individual details
- **No Image Preloading**: Images load on-demand without preloading
- **Memory Usage**: No explicit memory management for large image collections
- **Network Timeouts**: Fixed 40-second timeout for all network requests

### 3. UI/UX Limitations
- **Fixed Grid**: 3-column grid layout cannot be customized
- **No Dark Mode**: Only light theme available
- **Limited Accessibility**: Basic accessibility features only
- **No Landscape Support**: Layout optimized for portrait orientation
- **Fixed Typography**: Limited font customization options

### 4. Performance Limitations
- **Synchronous Operations**: Some operations block the main thread
- **No Pagination**: Loads all top anime at once
- **Image Quality**: No quality selection for different network conditions
- **Cache Size**: No configurable cache size limits
- **Background Processing**: Limited background task handling

## Future Enhancements

### Potential Improvements
1. **Search Functionality**: Add search bar for finding specific anime
2. **User Accounts**: Implement user authentication and personalization
3. **Watchlist**: Allow users to save anime to watch later
4. **Categories**: Browse anime by genre, season, or year
5. **Offline Mode**: Cache anime details for offline viewing
6. **Dark Theme**: Implement dark mode support
7. **Pagination**: Implement infinite scrolling for large lists
8. **Image Optimization**: Add image quality selection and compression
9. **Push Notifications**: Notify users about new anime releases
10. **Social Features**: Share anime recommendations with friends

## Development Setup

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK 24+
- Java 11 or later
- Kotlin 1.8+

### Build Configuration
- **Compile SDK**: 36
- **Target SDK**: 36
- **Min SDK**: 24
- **Build Tools**: Latest stable version

### Dependencies
All dependencies are managed through the `libs.versions.toml` file in the gradle directory.

## Testing
- **Unit Tests**: Basic test structure in place
- **Instrumented Tests**: Android device testing setup
- **Test Coverage**: Limited test coverage for core functionality

## Deployment
- **Release Build**: ProGuard disabled for debugging
- **Signing**: Standard Android app signing process
- **Distribution**: Google Play Store ready

## Conclusion
OtakuJikan is a well-structured Android application that demonstrates modern Android development practices. While it has some limitations, it provides a solid foundation for an anime discovery app with room for significant enhancements and feature additions. 