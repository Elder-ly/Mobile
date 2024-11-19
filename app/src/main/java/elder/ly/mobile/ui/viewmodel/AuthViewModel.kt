package elder.ly.mobile.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResult
import android.widget.Toast
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import elder.ly.mobile.BuildConfig
import elder.ly.mobile.Search
import elder.ly.mobile.SignUpStep1
import elder.ly.mobile.domain.model.User
import elder.ly.mobile.domain.service.AuthService
import elder.ly.mobile.data.Rest
import elder.ly.mobile.utils.clearUser
import elder.ly.mobile.utils.saveUser
import java.security.MessageDigest
import java.util.*

class AuthViewModel : ViewModel() {
    private lateinit var googleSignInClient: GoogleSignInClient

    fun setupGoogleSignIn(context: Context) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_CLIENT_ID)
            .requestEmail()
            .requestScopes(com.google.android.gms.common.api.Scope("https://www.googleapis.com/auth/calendar"))
            .build()

        googleSignInClient = GoogleSignIn.getClient(context, gso)
    }

    fun getGoogleSignInIntent(): Intent {
        return googleSignInClient.signInIntent
    }

    fun handleSignInResult(context: Context, navController: NavController, result: ActivityResult) {
        viewModelScope.launch {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account = task.getResult(ApiException::class.java)

                account?.let { googleAccount ->
                    val user = apiAuth(context, googleAccount)
                    if (user == null) {
                        navController.navigate(SignUpStep1)
                    } else {
                        saveUser(context, user)
                    }
                }
            } catch (e: ApiException) {
                Toast.makeText(context, "Erro ao logar com o Google, tente novamente mais tarde", Toast.LENGTH_LONG).show()
                println(e.message)
            }
        }
    }

    fun signOut(context: Context, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                googleSignInClient.signOut().addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        viewModelScope.launch {
                            clearUser(context)
                            onSuccess()
                        }
                    } else {
                        onError(Exception("Failed to sign out from Google"))
                    }
                }
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    private suspend fun apiAuth(context: Context, googleData: GoogleSignInAccount): User? {
        try {
            val service = Rest.api.create(AuthService::class.java)

            val response = googleData.email?.let { service.login(it) }
            if (response != null && response.isSuccessful) {
                if (response.body()?.id == null || response.body()?.tipoUsuario == null) {
                    return null
                }
                return User(
                    id = response.body()!!.id,
                    type = response.body()!!.tipoUsuario,
                    gender = null,
                    name = googleData.displayName,
                    email = googleData.email!!,
                    googleToken = googleData.idToken!!,
                    phoneNumber = null,
                    pictureURL = googleData.photoUrl.toString(),
                    residences = null,
                    resumes = null
                )
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Erro na comunicação com API", Toast.LENGTH_SHORT).show()
            println(e.message)
        }
        return null
    }

    // Usage example of signOut:
    /*
    viewModel.signOut(
        context = context,
        onSuccess = {
            navController.navigate(SignUpStep1)
        },
        onError = { exception ->
            Toast.makeText(context, "Error signing out: ${exception.message}", Toast.LENGTH_LONG).show()
        }
    )
    */
}
