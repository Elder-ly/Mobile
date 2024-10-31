package elder.ly.mobile.domain.model

data class Adresses(
    val id : Long,
    val zipCode : String,
    var street : String,
    var complement : String,
    var neighborhood : String,
    var number : String,
    var city : String,
    var uf : String,
    val residences: List<Residence>
)
