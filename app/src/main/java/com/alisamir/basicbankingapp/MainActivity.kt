package com.alisamir.basicbankingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.alisamir.basicbankingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var controller: NavController
    lateinit var appBar: AppBarConfiguration
    private val splash:splashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                splash.loading.value
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        controller = navHost.navController
        appBar = AppBarConfiguration(setOf(R.id.usersFragment,R.id.transactionsFragment))
        NavigationUI.setupWithNavController(binding.navBottom,controller)
        setupActionBarWithNavController(controller,appBar)
        controller.addOnDestinationChangedListener { _, nd:NavDestination, _ ->
            if(nd.id==R.id.userProfileFragment || nd.id==R.id.chooseUserFragment){
                binding.navBottom.visibility = View.GONE
            }else{
                binding.navBottom.visibility = View.VISIBLE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        if(controller.currentDestination?.id == R.id.chooseUserFragment){
            onBackPressed()
            return true
        }
        return controller.navigateUp()||super.onSupportNavigateUp()
    }

}