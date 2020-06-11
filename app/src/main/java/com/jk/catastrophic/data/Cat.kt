package com.jk.catastrophic.data

data class Cat(
    val breeds: List<Breeds>?,
    val categories: List<Categories>?,
    val id: String?,
    val url: String?,
    val width: String?,
    val height: String?,
    var percentage: Double = 0.0
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
