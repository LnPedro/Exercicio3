package com.pedrolino.menuexercicio3

import android.os.Bundle
import android.os.UserManager
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.annotation.NavigationRes
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.pedrolino.menuexercicio3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).hide(WindowInsetsCompat.Type.statusBars())
        setSupportActionBar(binding.toolbar)

        val toogle = ActionBarDrawerToggle(
            this,
            binding.drawerlayout,
            binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawerlayout.addDrawerListener(toogle)
        toogle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_smartphones -> openFragment(SmartphonesFragment())
                R.id.nav_eletronics -> openFragment(eletronicsFragment())
                R.id.nav_acessorios -> openFragment(accessoriesFragment())
                R.id.nav_tvs -> openFragment(TvsFragment())
                R.id.nav_computadores -> openFragment(ComputersFragment())
            }
            true
        }


        fragmentManager = supportFragmentManager
        openFragment(HomeFragment())

        binding.fab.setOnClickListener {
            Toast.makeText(this, "Categorias", Toast.LENGTH_SHORT).show()
        }

        onBackPressedDispatcher.addCallback(this) {
            if (binding.drawerlayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerlayout.closeDrawer(GravityCompat.START)

            } else {
                finish()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_smartphones -> openFragment(SmartphonesFragment())
            R.id.nav_eletronics -> openFragment(eletronicsFragment())
            R.id.nav_acessorios -> openFragment(accessoriesFragment())
            R.id.nav_tvs -> openFragment(TvsFragment())
            R.id.nav_computadores -> openFragment(ComputersFragment())
        }

        binding.drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}