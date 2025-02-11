package com.ramcosta.composedestinations.navargs.primitives.arraylist

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.navargs.DestinationsNavType
import com.ramcosta.composedestinations.navargs.primitives.DECODED_NULL
import com.ramcosta.composedestinations.navargs.primitives.ENCODED_NULL
import com.ramcosta.composedestinations.navargs.primitives.encodedComma
import com.ramcosta.composedestinations.navargs.primitives.valueOfIgnoreCase

@Suppress("UNCHECKED_CAST")
class DestinationsEnumArrayListNavType<E : Enum<*>>(
    private val enumType: Class<E>
) : DestinationsNavType<ArrayList<E>?>() {

    override fun put(bundle: Bundle, key: String, value: ArrayList<E>?) {
        bundle.putSerializable(key, value)
    }

    override fun get(bundle: Bundle, key: String): ArrayList<E>? {
        @Suppress("DEPRECATION")
        return bundle.getSerializable(key) as ArrayList<E>?
    }

    override fun parseValue(value: String): ArrayList<E>? {
        if (value == DECODED_NULL) return null

        if (value == "[]") return arrayListOf()

        val contentValue = value.subSequence(1, value.length - 1)
        return if (contentValue.contains(encodedComma)) {
            contentValue.split(encodedComma)
        } else {
            contentValue.split(",")
        }.mapTo(ArrayList()) { enumType.valueOfIgnoreCase(it) }
    }

    override fun serializeValue(value: ArrayList<E>?): String {
        if (value == null) return ENCODED_NULL
        return value.joinToString(",") { it.name }
    }

    override fun get(savedStateHandle: SavedStateHandle, key: String): ArrayList<E>? {
        return savedStateHandle.get<ArrayList<E>?>(key)
    }

}
