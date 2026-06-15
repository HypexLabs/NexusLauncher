# Nexus Launcher (formerly AOSP Launcher3)

Jetpack Compose launcher with iOS/OxygenOS inspired design.
Package: com.hypexlabs.NexusLauncher | Min SDK: 31 | Target SDK: 35

## Architecture
- **Compose-first**: Entire UI in Jetpack Compose (Material3)
- **No system/framework dependencies**: Standalone APK, works on any device
- **iOS/OxygenOS inspired**: Frosted dock, pill-shaped search, clean typography, smooth spring animations

## Source Structure
```
src/com/hypexlabs/NexusLauncher/
├── LauncherActivity.kt        # Main entry — Compose AnimatedContent (home ↔ drawer)
├── NexusLauncherApp.kt        # Application class
├── SettingsActivity.kt        # Settings entry
├── home/
│   ├── HomeScreen.kt          # Main screen with search + dock + wallpaper
│   ├── Dock.kt                # Frosted glass dock (LazyRow)
│   ├── AppDrawer.kt           # Full-screen drawer with search + grid
│   └── SearchBar.kt           # Pill-shaped search bar component
├── settings/
│   └── SettingsScreen.kt      # Settings list (placeholder)
├── theme/
│   ├── Color.kt               # Nexus color palette + light/dark schemes
│   ├── Theme.kt               # Material3 theme with dynamic color support
│   └── Type.kt                # (reserved) Typography system
├── model/
│   └── AppInfo.kt             # App data model
└── util/
    └── AppProvider.kt         # PackageManager-based app listing + launch
```

## Resources
```
res/
├── values/
│   ├── colors.xml             # Nexus color definitions
│   ├── config.xml             # Configuration values (from AOSP, minified)
│   ├── dimens.xml             # Dimension constants
│   ├── strings.xml            # String resources
│   └── themes.xml             # Base theme (transparent status/nav bars)
├── drawable/
│   ├── ic_nexus_foreground.xml # Adaptive icon foreground
│   └── ic_nexus_background.xml # Adaptive icon background (#FF6C00)
└── mipmap-{hdpi,xhdpi,xxhdpi}/
    └── ic_launcher.xml        # Adaptive icon definition
```

## Build System
- **build.gradle**: AGP 8.13.2, Kotlin 2.1.0, Compose BOM 2024.12.01
- **settings.gradle**: Single root project, no submodules
- **CI**: `.github/workflows/build.yml` — assembleDebug on push, upload APK

## Key Decisions
- No protobuf, no dagger, no AOSP module system
- No Quickstep/recents integration
- Wallpaper shown via theme (`android:windowShowWallpaper`)
- App icons loaded via standard PackageManager (no IconLoader submodule)
- Navigation: AnimatedContent (slide/fade between home and drawer)
- Settings: Separate activity (finish-based back navigation)
- Font scaling: respects system font size

## Build Commands
```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleDebug --info   # Verbose build
```

## Known Issues / TODOs
- [ ] HomeScreen needs wallpaper background support (currently plain surface)
- [ ] App icons in Dock and Drawer need proper Drawable→Compose Painter conversion
- [ ] Settings screen is placeholder — implement actual preferences
- [ ] Dock frosted glass effect needs `RenderEffect` for real blur (API 31+)
- [ ] Widget support (long-press → widget picker) not yet implemented
- [ ] Folder support not yet implemented
- [ ] Wallpaper picker integration
- [ ] Gesture navigation (swipe up for drawer, double-tap to lock)
- [ ] Themed app icon support (monochrome/adaptive)

## Converting AOSP Launcher3 Files
If re-adding AOSP components (e.g., workspace drag-and-drop, widget host):
1. Move files to `src/com/hypexlabs/NexusLauncher/`
2. Rename `com.android.launcher3` → `com.hypexlabs.NexusLauncher` in all imports
3. Replace hidden/system API calls with public equivalents
4. Update resource references to use `@color/` instead of `?android:attr/`
