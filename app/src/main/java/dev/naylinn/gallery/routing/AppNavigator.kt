package dev.naylinn.gallery.routing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import dev.naylinn.gallery.ui.base.BaseActivity

class AppNavigator(private val activity: AppCompatActivity) : Navigator {

  private fun navigateTo(intent: Intent) = activity.startActivity(intent)
  
  private inline fun <reified T : BaseActivity> getIntent() = Intent(activity, T::class.java)



}
