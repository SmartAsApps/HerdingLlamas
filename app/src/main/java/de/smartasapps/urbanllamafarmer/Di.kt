package de.smartasapps.urbanllamafarmer

import de.smartasapps.urbanllamafarmer.logic.LlamaLogic
import de.smartasapps.urbanllamafarmer.placement.ObjectPlacement
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

val myModule: Module = applicationContext {
    bean { ObjectPlacement(get()) }
    bean { LlamaLogic() }
}