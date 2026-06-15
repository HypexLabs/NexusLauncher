package com.hypexlabs.NexusLauncher.model

data class HomePageConfig(
    val pageIndex: Int = 0,
    val iconLayout: List<List<String>> = emptyList(),
)

data class DockConfig(
    val pinnedApps: List<String> = emptyList(),
    val recentApps: List<String> = emptyList(),
    val maxDockApps: Int = 4,
)

data class WidgetInfo(
    val id: String,
    val type: WidgetType,
    val size: WidgetSize,
)

enum class WidgetType { CLOCK, WEATHER, CALENDAR, MEMOS, FITNESS, BATTERY, CUSTOM }
enum class WidgetSize { SMALL, MEDIUM, LARGE }
