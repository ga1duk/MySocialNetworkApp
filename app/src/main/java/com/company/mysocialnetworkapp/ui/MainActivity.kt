package com.company.mysocialnetworkapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.company.mysocialnetworkapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

//    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        viewModel.data.observe(this) {
            invalidateOptionsMenu()
//        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_auth, menu)

//        menu.let {
//            it.setGroupVisible(R.id.unauthenticated, !viewModel.authenticated)
//            it.setGroupVisible(R.id.authenticated, viewModel.authenticated)
//        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.signin -> {
                findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_global_signInFragment)
                true
            }
            R.id.signup -> {
                findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_global_signUpFragment)
                true
            }
//            R.id.signout -> {
//                when (findNavController(R.id.nav_host_fragment).currentDestination?.id) {
//                    R.id.NewPostFragment -> exitDialogFragment.show(manager, "myDialog")
//                    else -> appAuth.removeAuth()
//                }
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}