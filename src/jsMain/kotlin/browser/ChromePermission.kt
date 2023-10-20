package browser

import kotlinx.serialization.SerialName

//https://developer.chrome.com/docs/extensions/mv3/declare_permissions/#permissions
enum class ChromePermission {
    activeTab, //	Gives temporary access to the active tab through a user gesture. See activeTab for details.
    alarms, //	Gives access to the chrome.alarms API.
    background, // Makes Chrome start up early (as soon as the user logs into their computer—before the user launches Chrome), and shut down late (even after its last window is closed until the user explicitly quits Chrome).
    bookmarks, //	Gives access to the chrome.bookmarks API.
    browsingData, //	Gives access to the chrome.browsingData API.
    certificateProvider, //	Gives access to the chrome.certificateProvider API.
    clipboardRead, //	Required if the extension uses document.execCommand('paste').
    clipboardWrite, //	Required if the extension uses document.execCommand('copy') or document.execCommand('cut').
    contentSettings, //	Gives access to the chrome.contentSettings API.
    contextMenus, //	Gives access to the chrome.contextMenus API.
    cookies, //	Gives access to the chrome.cookies API.
    debugger, //	Gives access to the chrome.debugger API.
    declarativeContent, //	Gives access to the chrome.declarativeContent API.
    declarativeNetRequest, //	Gives your extension access to the chrome.declarativeNetRequest API. Some operations may require host permissions to perform.
    declarativeNetRequestWithHostAccess, //	Gives your extension access to the chrome.declarativeNetRequest API, but requires host permissions to the request URL and initiator to act on a request.
    declarativeNetRequestFeedback, //	Gives access to events and methods within the chrome.declarativeNetRequest API which returns information on declarative rules matched.
    desktopCapture, //	Gives access to the chrome.desktopCapture API.
    documentScan, //	Gives access to the chrome.documentScan API.
    downloads, //	Gives access to the chrome.downloads API.

    @SerialName("downloads.open")
    downloadsOpen, //	Permission required to use chrome.downloads.open()

    @SerialName("downloads.ui")
    downloadsUI, //	Permission required to use chrome.downloads.setUiOptions()

    @SerialName("enterprise.deviceAttributes")
    enterpriseDeviceAttributes, //	Gives access to the chrome.enterprise.deviceAttributes API.

    @SerialName("enterprise.hardwarePlatform")
    enterpriseHardwarePlatform, //	Gives access to the chrome.enterprise.hardwarePlatform API.

    @SerialName("enterprise.networkingAttributes")
    enterpriseNetworkingAttributes, //	Gives access to the chrome.enterprise.networkingAttributes API.

    @SerialName("enterprise.platformKeys")
    enterprisePlatformKeys, //	Gives access to the chrome.enterprise.platformKeys API.
    experimental, //	Required if the extension uses any chrome.experimental.* APIs.
    fileBrowserHandler, //	Gives access to the chrome.fileBrowserHandler API.
    fileSystemProvider, //	Gives access to the chrome.fileSystemProvider API.
    fontSettings, //	Gives access to the chrome.fontSettings API.
    gcm, //	Gives access to the chrome.gcm API.
    geolocation, //	Allows the extension to use the geolocation API without prompting the user for permission.
    history, //	Gives access to the chrome.history API.
    identity, //	Gives access to the chrome.identity API.
    idle, //	Gives access to the chrome.idle API.
    loginState, //	Gives access to the chrome.loginState API.
    management, //	Gives access to the chrome.management API.
    nativeMessaging, //	Gives access to the native messaging API.
    notifications, //	Gives access to the chrome.notifications API.
    offscreen, //	Gives access to the chrome.offscreen API.
    pageCapture, //	Gives access to the chrome.pageCapture API.
    platformKeys, //	Gives access to the chrome.platformKeys API.
    power, //	Gives access to the chrome.power API.
    printerProvider, //	Gives access to the chrome.printerProvider API.
    printing, //	Gives access to the chrome.printing API.
    printingMetrics, //	Gives access to the chrome.printingMetrics API.
    privacy, //	Gives access to the chrome.privacy API.
    processes, //	Gives access to the chrome.processes API.
    proxy, //	Gives access to the chrome.proxy API.
    scripting, //	Gives access to the chrome.scripting API.
    search, //	Gives access to the chrome.search API.
    sessions, //	Gives access to the chrome.sessions API.
    sidePanel, //	Gives access to the chrome.sidePanel API.
    storage, //	Gives access to the chrome.storage API.

    @SerialName("system.cpu")
    systemCpu, //	Gives access to the chrome.system.cpu API.

    @SerialName("system.display")
    systemDisplay, //	Gives access to the chrome.system.display API.

    @SerialName("system.memory")
    systemMemory, //	Gives access to the chrome.system.memory API.

    @SerialName("system.storage")
    systemStorage, //	Gives access to the chrome.system.storage API.
    tabCapture, //	Gives access to the chrome.tabCapture API.
    tabGroups, //	Gives access to the chrome.tabGroups API.
    tabs, //	Gives access to privileged fields of the Tab objects used by several APIs including chrome.tabs and chrome.windows. In many circumstances your extension will not need to declare the , //tabs, // permission to make use of these APIs.
    topSites, //	Gives access to the chrome.topSites API.
    tts, //	Gives access to the chrome.tts API.
    ttsEngine, //	Gives access to the chrome.ttsEngine API.
    unlimitedStorage, //	Provides an unlimited quota for chrome.storage, IndexedDB, Cache Storage and Origin Private File System. For more information, see Storage and cookies.
    vpnProvider, //	Gives access to the chrome.vpnProvider API.
    wallpaper, //	Gives access to the chrome.wallpaper API.
    webAuthenticationProxy, //	Gives access to the chrome.webAuthenticationProxy API.
    webNavigation, //	Gives access to the chrome.webNavigation API.
    webRequest, //	Gives access to the chrome.webRequest API.
    webRequestBlocking, //	Required if the extension uses the chrome.webRequest API in a blocking fashion.{
}
