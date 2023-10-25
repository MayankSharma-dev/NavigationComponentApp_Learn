package com.example.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.navigationcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // navController can be accessed directly in onCreate() inorder to access it navHostFragment is required.
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_parent) as NavHostFragment
        // it will be used to set and control bottomNavView, navigationView(drawerLayout) & support ActionBar
        navController = navHostFragment.findNavController()

        // #1.2 (to fix #1.1 -
        // appBarConfiguration object need to be create which changes config in appBar)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeScreen,R.id.detailScreen),

        // in order to set burger-Icon in toolbar for drawerLayout(navigationView)
        // and to change between back button or burgerIcon automatically, drawerLayout object need to be added here.
        binding.drawerLayout

        )
        // This will set Home & Detail Fragments as Top-Level Destinations
        // but in order to work it needs to be passed to #setupActionBarWithNavController(navController)
        // as another parameter


        //setting up Toolbar
        setSupportActionBar(binding.toolbar)
        // setting up ActionBar with navController (for supporting back button functionality)
        setupActionBarWithNavController(navController, appBarConfiguration)


        //setting up bottomNavigationView with NavController(NavigationComponent)
        binding.bottomNav.setupWithNavController(navController)
        // #1.1 problem
        // This will works but with a problem only Showing Home fragment as a Top-Level Destination but
        // not the Detail Fragment as Top-Level Destination due to which you may see back navigation button in
        // Detail Fragment which leads back to Home Fragment which is not correct as both fragments should be Top-level
        // solution will be in #1.2


        // setting up NavigationView
        binding.navigationView.setupWithNavController(navController)

    }

    // for activating working of navController functions in Toolbar
    override fun onSupportNavigateUp(): Boolean {
        // this is working fine, it for without drawerLayout(navigationView)
        //return navController.navigateUp() || super.onSupportNavigateUp()

        // but when we add NavigationView appBarConfig object needs to be passed in navigateUp(appBarConfiguration).
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // inflating optionsMenu or activating optionsMenu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items,menu)
        return true
    }

    // to handle events in the optionsMenu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            // for intent! to setting Fragment
            R.id.settings -> {
                return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
            }

            // for GlobalActions {Terms Fragment was set as GlobalAction}
            R.id.termsConditions -> {
                val action = NavGraphDirections.actionGlobalTerms()
                navController.navigate(action)
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    /*
    // just Copied from Comments
    // and didn't knew the use of this code
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        menu?.findItem(R.id.termsAndConditions)?.isEnabled =
            navHostFragment.childFragmentManager.fragments[0] !is TermsFragment
        return true
    }
    */

}