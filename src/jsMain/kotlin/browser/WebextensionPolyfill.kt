package browser

import kotlin.js.Promise

external interface QueryInfo {

    /**
     * Whether the tabs are active in their windows.
     * */
    @JsName("active")
    var active: Boolean?

    /**
     * Whether the tabs are pinned.
     * */
    @JsName("pinned")
    var pinned: Boolean?

    /**
     * Whether the tabs are audible.
     *
     * Since Chrome 45.
     * */
    @JsName("audible")
    var audible: Boolean?

    /**
     * Whether the tabs are muted.
     *
     * Since Chrome 45.
     * */
    @JsName("muted")
    var muted: Boolean?

    /**
     * Whether the tabs are highlighted.
     * */
    @JsName("highlighted")
    var highlighted: Boolean?

    /**
     * Whether the tabs are discarded.
     * A discarded tab is one whose content has been unloaded from memory,
     * but is still visible in the tab strip.
     * Its content gets reloaded the next time it's activated.
     *
     * Since Chrome 54.
     */
    @JsName("discarded")
    var discarded: Boolean?

    /**
     * Whether the tabs can be discarded automatically by the browser when resources are low.
     *
     * Since Chrome 54.
     */
    @JsName("autoDiscardable")
    var autoDiscardable: Boolean?

    /**
     * Whether the tabs are in the current window.
     * */
    @JsName("currentWindow")
    var currentWindow: Boolean?

    /**
     * Whether the tabs are in the last focused window.
     * */
    @JsName("lastFocusedWindow")
    var lastFocusedWindow: Boolean?

    /**
     * Whether the tabs have completed loading.
     * */
    @JsName("status")
    var status: String?

    /**
     * Match page titles against a pattern.
     * Note that this property is ignored if the extension doesn't have the "tabs" permission.
     */
    @JsName("title")
    var title: String?

    /**
     * Match tabs against one or more URL patterns.
     * Note that fragment identifiers are not matched.
     * Note that this property is ignored if the extension doesn't have the "tabs" permission.
     */
    @JsName("url")
    var url: String? //TODO string or array of string (optional)

    /** The ID of the parent window, or windows.WINDOW_ID_CURRENT for the current window. */
    @JsName("windowId")
    var windowId: Int?

    /** The type of window the tabs are in. */
    @JsName("windowType")
    var windowType: String?

    /** The position of the tabs within their windows. */
    @JsName("index")
    var index: Int?

}

external interface Tab {

    /**
     * The ID of the tab. Tab IDs are unique within a browser session.
     * Under some circumstances a Tab may not be assigned an ID, for example when querying foreign tabs using the sessions API, in which case a session ID may be present.
     * Tab ID can also be set to chrome.tabs.TAB_ID_NONE for apps and devtools windows.
     */
    @JsName("id")
    val id: Int?

    /**
     * The zero-based index of the tab within its window.
     */
    @JsName("index")
    val index: Int

    /**
     * The ID of the window the tab is contained within.
     */
    @JsName("windowId")
    val windowId: Int

    /**
     * The ID of the tab that opened this tab, if any. This property is only present if the opener tab still exists.
     */
    @JsName("openerTabId")
    val openerTabId: Int?

    /**
     * Whether the tab is highlighted.
     */
    @JsName("highlighted")
    val highlighted: Boolean

    /**
     * Whether the tab is active in its window. (Does not necessarily mean the window is focused.)
     */
    @JsName("active")
    val active: Boolean

    /**
     * Whether the tab is pinned.
     */
    @JsName("pinned")
    val pinned: Boolean

    /**
     *Whether the tab has produced sound over the past couple of seconds (but it might not be heard if also muted).
     * Equivalent to whether the speaker audio indicator is showing.
     *
     * Since Chrome 45.
     */
    @JsName("audible")
    val audible: Boolean?

    /**
     * Whether the tab is discarded.
     * A discarded tab is one whose content has been unloaded from memory, but is still visible in the tab strip.
     * Its content gets reloaded the next time it's activated.
     *
     * Since Chrome 54.
     */
    @JsName("discarded")
    val discarded: Boolean

    /**
     * Whether the tab can be discarded automatically by the browser when resources are low.
     *
     * Since Chrome 54.
     */
    @JsName("autoDiscardable")
    val autoDiscardable: Boolean

    /**
     * Current tab muted state and the reason for the last state change.
     *
     * Since Chrome 46.
     */
    @JsName("mutedInfo")
    val mutedInfo: MutedInfo?

    /**
     * The URL the tab is displaying.
     * This property is only present if the extension's manifest includes the "tabs" permission.
     */
    @JsName("url")
    val url: String?

    /**
     * The title of the tab.
     * This property is only present if the extension's manifest includes the "tabs" permission.
     */
    @JsName("title")
    val title: String?

    /**
     * The URL of the tab's favicon.
     * This property is only present if the extension's manifest includes the "tabs" permission.
     * It may also be an empty string if the tab is loading.
     */
    @JsName("favIconUrl")
    val favIconUrl: String?

    /**
     * Either loading or complete.
     */
    @JsName("status")
    val status: String?

    /**
     * Whether the tab is in an incognito window.
     */
    @JsName("incognito")
    val incognito: Boolean

    /**
     * The width of the tab in pixels.
     *
     * Since Chrome 31.
     */
    @JsName("width")
    val width: Int?

    /**
     * The height of the tab in pixels.
     *
     * Since Chrome 31.
     */
    @JsName("height")
    val height: Int?

    /**
     * The session ID used to uniquely identify a Tab obtained from the sessions API.
     *
     * Since Chrome 31.
     */
    @JsName("sessionId")
    val sessionId: String?
}

external interface MutedInfo {

    /**
     * Whether the tab is prevented from playing sound (but hasn't necessarily recently produced sound).
     * Equivalent to whether the muted audio indicator is showing.
     */
    @JsName("muted")
    val muted: Boolean

    /**
     * The reason the tab was muted or unmuted.
     * Not set if the tab's mute state has never been changed.
     */
    @JsName("reason")
    val reason: String?

    /**
     * The ID of the extension that changed the muted state.
     * Not set if an extension was not the reason the muted state last changed.
     */
    @JsName("extensionId")
    val extensionId: String?
}

external interface ExecuteScriptDetails {

    /**
     * JavaScript or CSS code to inject.
     *
     * Warning:
     * Be careful using the code parameter.
     * Incorrect use of it may open your extension to cross site scripting attacks.
     */
    @JsName("code")
    var code: String?

    /** JavaScript or CSS file to inject. */
    @JsName("file")
    var file: String?

    /**
     * If allFrames is true, implies that the JavaScript or CSS should be injected into all frames of current page.
     * By default, it's false and is only injected into the top frame.
     * If true and frameId is set, then the code is inserted in the selected frame and all of its child frames.
     */
    @JsName("allFrames")
    var allFrames: Boolean?

    /**
     * The frame where the script or CSS should be injected. Defaults to 0 (the top-level frame).
     * Since Chrome 39.
     */
    @JsName("frameId")
    var frameId: Int?

    /**
     * If matchAboutBlank is true, then the code is also injected in about:blank and about:srcdoc frames if your extension has access to its parent document.
     * Code cannot be inserted in top-level about:-frames. By default it is false.
     * Since Chrome 39.
     */
    @JsName("matchAboutBlank")
    var matchAboutBlank: Boolean?

    /**
     * The soonest that the JavaScript or CSS will be injected into the tab. Defaults to "document_idle".
     */
    @JsName("runAt")
    var runAt: String?
}


@JsModule("webextension-polyfill")
@JsNonModule
external object Browser {
    @JsName("tabs")
    val tabs: Tabs
    @JsName("storage")
    val storage: Storage
    @JsName("notifications")
    val notifications: Notifications
}


external interface Notification {
    @JsName("type")
    val type: String
    @JsName("iconUrl")
    val iconUrl: String
    @JsName("title")
    val title: String
    @JsName("message")
    val message: String
}

external interface Notifications {
    @JsName("create")
    fun create(notification: Notification): Promise<dynamic>
}

external interface Tabs {
    fun remove(obj: dynamic): Promise<Unit>
    fun create(obj: dynamic): Promise<Unit>
    fun query(queryInfo: QueryInfo): Promise<Array<Tab>>
    fun executeScript(tabId: Int? = definedExternally,details: ExecuteScriptDetails): Promise<Array<dynamic>>
}

external interface Storage {
    @JsName("local")
    val local: LocalStorage
}

external interface LocalStorage {
    @JsName("set")
    fun set(data: dynamic): Promise<Unit>
    @JsName("get")
    fun get(key: String): Promise<dynamic>
}

