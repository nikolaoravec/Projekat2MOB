package rs.raf.projekat2.valerija_nagl_RN682018.data.models


data class UserData(
    val name: String
)

fun UserData.toUser(): User {
    return User(name)
}