# Crypto App

## Overview
Crypto App is a comprehensive application designed to provide users with real-time cryptocurrency data using the CoinGecko API and TradingView widgets. The app supports user authentication through Firebase and stores user data in Firebase's real-time database. It uses Retrofit to handle network requests and communication with REST APIs.

## Features
- **Real-time Cryptocurrency Data:** Fetches and displays up-to-date cryptocurrency information from CoinGecko API.
- **Interactive Charts:** Utilizes TradingView widgets to provide users with detailed and interactive cryptocurrency charts.
- **User Authentication:** Implements Firebase Authentication for secure user login and registration.
- **Real-Time Database:** Stores user-specific data in Firebase's real-time database.
- **Network Communication:** Uses Retrofit to communicate with REST APIs efficiently.
- **Watchlist Management:** Allows users to add cryptocurrencies to their watchlist and save their preferences.

## Technologies Used
- **Firebase Authentication:** For user login and registration.
- **Firebase Realtime Database:** To store user data.
- **CoinGecko API:** To fetch real-time cryptocurrency data.
- **TradingView Widgets:** For interactive and detailed cryptocurrency charts.
- **Retrofit:** To handle network requests and API communication.
- **Glide:** For image loading and caching.
- **Android Navigation Component:** For seamless navigation between screens.
- **Kotlin Coroutines:** For asynchronous programming.

## Screenshots
![login,logout](https://github.com/Harsh-developer-apk/Crypto-App/assets/150172274/47193805-c623-4503-8cf8-933a9d252e95)
![cryptoImages](https://github.com/Harsh-developer-apk/Crypto-App/assets/150172274/5de72b3b-37b5-4e17-8482-cdda13f22a03)

## Installation
1. **Clone the repository:**
   ```sh
   git clone https://github.com/yourusername/cryptoapp.git
   cd cryptoapp
   ```

2. **Open the project in Android Studio.**

3. **Configure Firebase:**
   - Add your `google-services.json` file to the `app` directory.
   - Enable Firebase Authentication and Realtime Database in the Firebase Console.

4. **Build and run the app on your device or emulator.**

## Usage
- **Sign Up / Login:** Use the authentication feature to sign up or log in.
- **Browse Cryptocurrencies:** View a list of cryptocurrencies with real-time data.
- **View Details:** Click on a cryptocurrency to see detailed information and interactive charts.
- **Add to Watchlist:** Use the watchlist feature to keep track of your favorite cryptocurrencies.

## API References
- **CoinGecko API:** [https://www.coingecko.com/en/api](https://www.coingecko.com/en/api)
- **TradingView Widgets:** [https://www.tradingview.com/widget/](https://www.tradingview.com/widget/)

