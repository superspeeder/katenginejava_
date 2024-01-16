package dev.woc.katengine

import dev.woc.katengine.signals.Signal

sealed class AppEvent(val app: App);

class AppCreateEvent(app: App) : AppEvent(app)
class AppInternalCreateEvent(app: App) : AppEvent(app)
class AppDestroyEvent(app: App) : AppEvent(app)
class AppRenderEvent(app: App, val delta: Double) : AppEvent(app)

abstract class App {

    val createSignal = Signal<AppCreateEvent>()
    val destroySignal = Signal<AppDestroyEvent>()
    val renderSignal = Signal<AppRenderEvent>()

    internal val internalCreateSignal = Signal<AppInternalCreateEvent>()

    init {
        internalCreateSignal.registerHandler{
            KatEngine.init()
        }
    }

    fun launch() {
        internalCreateSignal.emit(AppInternalCreateEvent(this))
    }
}