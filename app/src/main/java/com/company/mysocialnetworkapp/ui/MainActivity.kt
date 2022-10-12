package com.company.mysocialnetworkapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.company.mysocialnetworkapp.R
import com.company.mysocialnetworkapp.ui.dialog.ExitDialogFragment
import com.company.mysocialnetworkapp.viewmodel.AuthViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: AuthViewModel by viewModels()

    private lateinit var exitDialogFragment: ExitDialogFragment
    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.postsFragment, R.id.signInFragment, R.id.signUpFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)

        exitDialogFragment = ExitDialogFragment()
        manager = supportFragmentManager

//        viewModel.data.observe(this) {
//            invalidateOptionsMenu()
//        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu_auth, menu)
//
//        menu.let {
//            it.setGroupVisible(R.id.unauthenticated, !viewModel.authenticated)
//            it.setGroupVisible(R.id.authenticated, viewModel.authenticated)
//        }
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.signin -> {
//                findNavController(R.id.nav_host_fragment)
//                    .navigate(R.id.action_global_signInFragment)
//                true
//            }
//            R.id.signup -> {
//                findNavController(R.id.nav_host_fragment)
//                    .navigate(R.id.action_global_signUpFragment)
//                true
//            }
//            R.id.signout -> {
//                exitDialogFragment.show(manager, "myDialog")
//                true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}