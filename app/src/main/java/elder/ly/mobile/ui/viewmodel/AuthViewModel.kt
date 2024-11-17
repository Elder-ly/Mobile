package elder.ly.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.content.Context
import androidx.credentials.GetCredentialRequest
import androidx.credentials.CredentialManager
import android.widget.Toast
import androidx.credentials.exceptions.GetCredentialException
import androidx.navigation.NavController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import elder.ly.mobile.BuildConfig
import elder.ly.mobile.Search
import elder.ly.mobile.SignUpStep1
import elder.ly.mobile.domain.model.User
import elder.ly.mobile.domain.service.AuthService
import elder.ly.mobile.data.Rest
import elder.ly.mobile.utils.saveUser
import java.security.MessageDigest
import java.util.*

class AuthViewModel : ViewModel() {

    fun googleSignIn(context: Context, navController: NavController) {
        val credentialManager = CredentialManager.create(context)

        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setNonce(getNonce())
        .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
        .build()

        viewModelScope.launch {
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = context
                )
                val credential = result.credential

                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                val user = apiAuth(context, googleIdTokenCredential)

                if(user?.id == null){
                    if (user != null) {
                        saveUser(context, user)
                    }
                    navController.navigate(SignUpStep1)
                }else{
                    saveUser(context, user)
                    navController.navigate(Search)
                }
            }catch (e: GetCredentialException){
                Toast.makeText(context, "Erro ao logar com o Google, tente novamente mais tarde", Toast.LENGTH_LONG).show()
                println(e.message)
            }catch (e: GoogleIdTokenParsingException){
                Toast.makeText(context, "Erro ao logar com o Google, tente novamente mais tarde", Toast.LENGTH_LONG).show()
                println(e.message)
            }
        }
    }

    private suspend fun apiAuth(context: Context, googleData: GoogleIdTokenCredential): User? {
        try {
            val service = Rest.api.create(AuthService::class.java)

            val response = service.login(googleData.id)
            if(response.isSuccessful){
                if(response.body()?.id == null || response.body()?.tipoUsuario == null){
                    return null
                }
                return User(
                    id = response.body()!!.id,
                    type = response.body()!!.tipoUsuario,
                    gender = null,
                    name = googleData.displayName,
                    email = googleData.id,
                    googleToken = googleData.idToken,
                    phoneNumber = googleData.phoneNumber,
                    pictureURL = googleData.profilePictureUri.toString(),
                    residences = null,
                    resumes = null
                )
            }
        }catch (e: Exception){
            Toast.makeText(context, "Erro na comunicação com API", Toast.LENGTH_SHORT).show()
            println(e.message)
        }

        return User(
            id = null,
            type = null,
            gender = null,
            name = googleData.displayName,
            email = googleData.id,
            googleToken = googleData.idToken,
            phoneNumber = googleData.phoneNumber,
            pictureURL = googleData.profilePictureUri.toString(),
            residences = null,
            resumes = null
        )
    }

    private fun getNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hashedNonce = digest.fold("") { str, it -> str + "%02x".format(it) }
        return hashedNonce;
    }
}
