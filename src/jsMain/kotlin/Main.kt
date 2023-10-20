import browser.*
import helloworld.onChangeEvent
import helloworld.onContentLoadedEventAsync
import kotlinx.browser.document
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.await
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLSelectElement


suspend fun getTabs(queryInfo: QueryInfo): Array<Tab> {
    return Browser.tabs.query(queryInfo).await()
}

suspend fun getCurrentTab(): Tab {
    val queryInfo = object {
        @JsName("active")
        val active = true

        @JsName("currentWindow")
        val currentWindow = true
    } as QueryInfo
    return getTabs(queryInfo).first()
}

@OptIn(DelicateCoroutinesApi::class)
fun getCurrentTabUrlAsync() =
    GlobalScope.async {
        getCurrentTab().url ?: throw RuntimeException("tab.url should be a string")
    }

suspend fun sendNotification() {
    return Browser.notifications.create(object {
        @JsName("type")
        val type = "basic"

        @JsName("iconUrl")
        val iconUrl = "icon.png"

        @JsName("title")
        val title = "Tabs reloaded"

        @JsName("message")
        val message = "Your tabs have been reloaded"
    } as Notification).await()
}

/**
 * Gets the saved background color for url.
 *
 * @param url URL whose background color is to be retrieved.
 * @param promise fulfilled with the saved background color for
 *     the given url on success, or rejected if no color is retrieved.
 */
fun getSavedBackgroundColorAsync(url: String) =
    GlobalScope.async {
        // See https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/API/storage/StorageArea.
        val items = Browser.storage.local.get(url).await()
        items[url] as? String
    }

/**
 * Sets the given background color for url.
 *
 * @param url URL for which background color is to be saved.
 * @param color The background color to be saved.
 */
fun saveBackgroundColor(url: String, color: String) {
    val items = js("{}")
    items[url] = color
    // See https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/API/storage/StorageArea.
    // We do not use the returned Promise since we don't need to perform any action once the
    // background color is saved.
    Browser.storage.local.set(items)
}

fun changeBackgroundColor(color: String) {
    val script = """document.body.style.backgroundColor="$color";"""
    // See https://developer.mozilla.org/en-US/docs/Mozilla/Add-ons/WebExtensions/API/tabs/executeScript.
    // browser.tabs.executeScript allows us to programmatically inject JavaScript
    // into a page. Since we omit the optional first argument "tabId", the script
    // is inserted into the active tab of the current window, which serves as the
    // default.
    Browser.tabs.executeScript(
        details = object {
            @JsName("code")
            val code = script
        } as ExecuteScriptDetails
    )
}

// This extension loads the saved background color for the current tab if one
// exists. The user can select a new background color from the dropdown for the
// current page, and it will be saved as part of the extension's isolated
// storage. The browser.storage API is used for this purpose. This is different
// from the window.localStorage API, which is synchronous and stores data bound
// to a document's origin. Also, using browser.storage.sync instead of
// browser.storage.local allows the extension data to be synced across multiple
// user devices.
@OptIn(DelicateCoroutinesApi::class)
actual fun main(args: Array<String>) {
    console.log("Main")

    document.onContentLoadedEventAsync {
        val createTabBtn = document.getElementById("createTabBtn") as HTMLButtonElement
        createTabBtn.addEventListener("click", {
            Browser.tabs.create(object {
                @JsName("url")
                val url: String = "https://vk.com"
            });
        })

        val closeTabBtn = document.getElementById("closeTabBtn") as HTMLButtonElement
        closeTabBtn.addEventListener("click", {
            GlobalScope.async {
                val currentTabId = getCurrentTab().id
                println("Current tab id: $currentTabId")
                Browser.tabs.remove(object {
                    @JsName("tabIds")
                    val tabIds: Array<Number> = arrayOf(currentTabId!!)
                });
            }
        })

        val button = document.getElementById("mybutton") as HTMLButtonElement
        button.addEventListener("click", {
            button.innerText = "button was clicked"
        })
        val notificationBtn = document.getElementById("notificationBtn") as HTMLButtonElement
        notificationBtn.addEventListener("click", {
            GlobalScope.async {
                sendNotification()
            }
        })

        getTabs(object {} as QueryInfo).forEach { println("p: ${it.title}") }

        val url = getCurrentTabUrlAsync().await()
        val dropdown = document.getElementById("dropdown") as HTMLSelectElement
        // Load the saved background color for this page and modify the dropdown
        // value, if needed.
        val savedColor = getSavedBackgroundColorAsync(url).await()
        if (savedColor != null) {
            changeBackgroundColor(savedColor)
            dropdown.value = savedColor
        }
        // Ensure the background color is changed and saved when the dropdown
        // selection changes.
        dropdown.onChangeEvent {
            changeBackgroundColor(dropdown.value)
            saveBackgroundColor(url, dropdown.value)
        }
    }
}
