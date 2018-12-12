package com.debajyoti.spacexinfo.utils;

public class AppConstants {
    public static final String BASE_URL = "https://api.spacexdata.com/v3/";
    public static final String DB_NAME = "spacex.db";
    public static final String INFO_TABLE = "infoTable";
    public static final String PAST_LAUNCHES_TABLE = "pastLaunches";
    public static final String NEXT_LAUNCH_TABLE = "nextLaunch";
    public static final String LATEST_LAUNCH_TABLE = "latestLaunch";
    public static final String ROCKETS_TABLE = "rockets";
    public static final String HISTORY_TABLE = "history";
    public static final String PREF = "spacex_pref";
    public static final String LATEST_LAUNCH_ENDPOINT = "launches/latest";
    public static final String NEXT_LAUNCH_ENDPOINT = "launches/next";
    public static final String PAST_LAUNCHES_ENDPOINT = "launches/past";
    public static final String ROCKETS_ENDPOINT = "rockets";
    public static final String HISTORY_ENDPOINT = "history";
    public static final String INFO_ENDPOINT = "info";

    // Analytics
    public static final String EVENT_ROCKET_CLICK = "rocket_click";
    public static final String EVENT_ROCKET_NAME = "rocket_name";
    public static final String EVENT_MISSION_CLICK = "mission_click";
    public static final String EVENT_MISSION_NAME = "mission_name";
    public static final String EVENT_WIKI_LINK_CLICK = "wiki_link_click";
    public static final String EVENT_WIKI_LINK = "wiki_link";
    public static final String EVENT_ARTICLE_LINK_CLICK = "article_link_click";
    public static final String EVENT_ARTICLE_LINK = "article_link";

    // Api timestamps
    public static final String INFO_API_TIMESTAMP = "info_api_timestamp";
    public static final String HISTORY_API_TIMESTAMP = "history_api_timestamp";
    public static final String ROCKET_API_TIMESTAMP = "rocket_api_timestamp";
    public static final String PAST_LAUNCHES_API_TIMESTAMP = "past_launches_api_timestamp";
    public static final String LATEST_LAUNCH_API_TIMESTAMP = "latest_launches_api_timestamp";
    public static final String NEXT_LAUNCH_API_TIMESTAMP = "next_launches_api_timestamp";
    public static final String WIDGET_PREFIX = "appwidget";
    public static final int FRESH_TIMEOUT_IN_MINUTES = 60;

    public static final String DF1 = "yyyy-MM-dd";
    public static final String DF2 = "MMMM dd, yyyy";
    public static final String DF3 = "MMMM dd";
}
