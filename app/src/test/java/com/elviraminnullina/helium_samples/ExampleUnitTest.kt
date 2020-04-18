package com.elviraminnullina.helium_samples

import kotlinx.serialization.UnstableDefault
import org.junit.Test
import kotlinx.serialization.json.Json.Default.parse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json.Default.stringify
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Serializable
    data class Container<T>(val data: T)

    @Serializable
    class Data(val text: String)

    @Test
    fun addition_isCorrect() {
        testGenerics()
    }


    @OptIn(UnstableDefault::class)
    fun testGenerics() {
        // val json =  stringify(Container.serializer(Data.serializer()), Container(Data("test")))

        val json = "{\"data\":{\"text\":\"test\"}}"
        //Kotlin serialization
      /*  val dataSerializer: KSerializer<Data> = Data.serializer()
        val boxedDataSerializer: KSerializer<Container<Data>> = Container.serializer(dataSerializer)
        val kotlinParsed = parse(boxedDataSerializer, json)

        //Gson serialization
        val collectionType = object : TypeToken<Container<Data>>() {}.type
        val gsonParsed = Gson().fromJson<Container<Data>>(json, collectionType)

        assertEquals("test", kotlinParsed.data.text)
        assertEquals(gsonParsed.data.text, kotlinParsed.data.text)*/
    }
}
