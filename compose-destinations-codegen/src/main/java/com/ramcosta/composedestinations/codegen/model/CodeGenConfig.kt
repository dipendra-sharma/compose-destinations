package com.ramcosta.composedestinations.codegen.model

data class CodeGenConfig(
    val packageName: String?,
    val moduleName: String?,
    val mode: CodeGenMode,
    val useComposableVisibility: Boolean,
)

sealed class CodeGenMode {
    object NavGraphs: CodeGenMode()
    object Destinations: CodeGenMode()
    class SingleModule(val generateNavGraphs: Boolean): CodeGenMode()
}