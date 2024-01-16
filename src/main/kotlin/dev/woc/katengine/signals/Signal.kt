package dev.woc.katengine.signals

typealias SignalHandlerId = UInt

class Signal<T> {
    private val handlers: MutableMap<SignalHandlerId, (T) -> Unit> = mutableMapOf()

    private var idCounter: SignalHandlerId = 0U

    fun emit(value: T) {
        handlers.forEach { it -> it.value(value) }
    }

    fun registerHandler(handler: (T) -> Unit): SignalHandlerId {
        val id = idCounter++
        handlers[id] = handler
        return id
    }

    fun unregisterHandler(id: SignalHandlerId) {
        handlers.remove(id)
    }
}