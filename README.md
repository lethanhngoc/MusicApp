
# MusicApp 🎵

An Android music streaming and player app built with **Java**. Users can browse songs by topic, genre, album and playlist, search for songs, stream music from a remote API, and play local music stored on the device.

## Features

- **Home feed** – banner slider, hot songs, hot albums, and daily topic/genre suggestions
- **Browse by category** – topics, genres, albums, and curated playlists
- **Music player** – play/pause, next/previous, seek bar, rotating disc UI, and lyrics view
- **Search** – find songs by keyword through the API
- **Local music** – scan and play songs stored on the device / SD card
- **Library** – quick access to your playlists and local music

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| UI | Activities, Fragments, ViewPager, RecyclerView, CardView, Material Design |
| Networking | Retrofit 2 + Gson converter |
| Image loading | Picasso |
| Media playback | Android `MediaPlayer` with `SeekBar` progress tracking |
| Backend | REST API returning JSON (songs, albums, topics, genres, playlists) |

## Architecture

```
app/src/main/java/com/android/
├── activity/    # Screens: Main, PlaySong, ListSongs, Album/Topic/Playlist lists...
├── fragment/    # Home, Search, Library, Local music, banner & section fragments
├── adapter/     # RecyclerView & ViewPager adapters for each list
├── model/       # Song, Album, Playlist, TheLoai (Genre), ChuDe (Topic)...
└── service/     # Retrofit client + API service definitions
```

The app follows a classic Activity/Fragment structure: data is fetched from the REST API via Retrofit, mapped to model classes with Gson, and rendered through RecyclerView adapters. Song playback is handled by `MediaPlayer`, streaming directly from the song URL.

## Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/lethanhngoc/MusicApp.git
   ```
2. Open the project in **Android Studio**
3. Update the API base URL in `service/APIRetrofitClient.java` to point to your backend
4. Build and run on an emulator or device (minSdk 26, targetSdk 29)

## What I Learned

- Designing a multi-screen Android app with Fragments and ViewPager
- Consuming a REST API with Retrofit 2 and mapping JSON to model classes
- Implementing audio playback with `MediaPlayer` (seek, progress, next/previous)
- Reading media files from device storage with runtime permissions
