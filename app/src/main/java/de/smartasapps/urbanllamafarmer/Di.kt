package de.smartasapps.urbanllamafarmer

import de.smartasapps.urbanllamafarmer.logic.LlamaLogic
import de.smartasapps.urbanllamafarmer.placement.ObjectPlacement
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

val myModule: Module = module {
    single { ObjectPlacement(get()) }
    single { LlamaLogic() }
}
