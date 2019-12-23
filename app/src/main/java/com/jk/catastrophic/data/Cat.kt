/* 
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */

package com.jk.catastrophic.data
// result generated from /json

data class Cat(
    val breeds: List<Breeds>?,
    val categories: List<Categories>?,
    val id: String?,
    val url: String?,
    val width: String?,
    val height: String?
)

data class Breeds(
    val weight: Weight?,
    val id: String?,
    val name: String?,
    val cfa_url: String?,
    val vetstreet_url: String?,
    val vcahospitals_url: String?,
    val temperament: String?,
    val origin: String?,
    val country_codes: String?,
    val country_code: String?,
    val description: String?,
    val life_span: String?,
    val indoor: String?,
    val lap: String?,
    val alt_names: String?,
    val adaptability: String?,
    val affection_level: String?,
    val child_friendly: String?,
    val dog_friendly: String?,
    val energy_level: String?,
    val grooming: String?,
    val health_issues: String?,
    val intelligence: String?,
    val shedding_level: String?,
    val social_needs: String?,
    val stranger_friendly: String?,
    val vocalisation: String?,
    val experimental: String?,
    val hairless: String?,
    val natural: String?,
    val rare: String?,
    val rex: String?,
    val suppressed_tail: String?,
    val short_legs: String?,
    val wikipedia_url: String?,
    val hypoallergenic: String?
)


data class Categories(val id: String?, val name: String?)


data class Weight(val imperial: String?, val metric: String?)
