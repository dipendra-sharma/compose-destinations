package com.ramcosta.composedestinations.codegen.templates

import com.ramcosta.composedestinations.codegen.codeGenBasePackageName
import com.ramcosta.composedestinations.codegen.commons.CORE_PACKAGE_NAME

//region anchors
const val ADDITIONAL_IMPORTS = "[ADDITIONAL_IMPORTS]"
const val DESTINATION_NAME = "[DESTINATION_NAME]"
const val BASE_ROUTE = "[ROUTE_ID]"
const val COMPOSED_ROUTE = "[COMPOSED_ROUTE]"
const val NAV_ARGUMENTS = "[NAV_ARGUMENTS]"
const val DEEP_LINKS = "[DEEP_LINKS]"
const val DESTINATION_STYLE = "[DESTINATION_STYLE]"
const val ARGS_TO_DIRECTION_METHOD = "[ARGS_TO_DIRECTION_METHOD]"
const val ARGS_FROM_METHODS = "[ARGS_FROM_METHODS]"
const val CONTENT_FUNCTION_CODE = "[CONTENT_FUNCTION_CODE]"
const val REQUIRE_OPT_IN_ANNOTATIONS_PLACEHOLDER = "[REQUIRE_OPT_IN_ANNOTATIONS_PLACEHOLDER]"
const val NAV_ARGS_DATA_CLASS = "[NAV_ARGS_DATA_CLASS]"
const val SUPERTYPE = "[SUPERTYPE]"
const val NAV_ARGS_CLASS_SIMPLE_NAME = "[NAV_ARGS_CLASS_SIMPLE_NAME]"
//endregion

val destinationTemplate="""
package $codeGenBasePackageName.destinations

import androidx.annotation.RestrictTo
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import $CORE_PACKAGE_NAME.scope.DestinationScope
import $CORE_PACKAGE_NAME.navigation.DestinationDependenciesContainer
import $CORE_PACKAGE_NAME.navigation.DependenciesContainerBuilder
import $CORE_PACKAGE_NAME.spec.DestinationSpec
import $CORE_PACKAGE_NAME.spec.DestinationStyle
import $CORE_PACKAGE_NAME.spec.Direction
import $CORE_PACKAGE_NAME.spec.DirectionDestinationSpec
import $CORE_PACKAGE_NAME.spec.NavGraphSpec
import $CORE_PACKAGE_NAME.spec.Route$ADDITIONAL_IMPORTS

${REQUIRE_OPT_IN_ANNOTATIONS_PLACEHOLDER}object $DESTINATION_NAME : $SUPERTYPE {
    $ARGS_TO_DIRECTION_METHOD
    @get:RestrictTo(RestrictTo.Scope.SUBCLASSES)
    override val baseRoute = "$BASE_ROUTE"

    override val route = $COMPOSED_ROUTE
    $NAV_ARGUMENTS$DEEP_LINKS$DESTINATION_STYLE
    @Composable
    override fun DestinationScope<$NAV_ARGS_CLASS_SIMPLE_NAME>.Content(
		dependenciesContainerBuilder: @Composable DependenciesContainerBuilder<$NAV_ARGS_CLASS_SIMPLE_NAME>.() -> Unit
    ) {
$CONTENT_FUNCTION_CODE
    }
    $ARGS_FROM_METHODS$NAV_ARGS_DATA_CLASS
}
""".trimIndent()