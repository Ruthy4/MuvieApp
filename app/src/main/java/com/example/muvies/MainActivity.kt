package com.example.muvies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.muvies.data.remote.connectivity.ConnectivityLiveData
import com.example.muvies.data.remote.connectivity.ConnectivityManager
import com.example.muvies.databinding.ActivityMainBinding
import com.example.muvies.utils.hideView
import com.example.muvies.utils.showToast
import com.example.muvies.utils.showView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var connectionLiveData: ConnectivityLiveData

    @Inject
    lateinit var connectivityManager: ConnectivityManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        connectionLiveData = ConnectivityLiveData(this)
        observeConnection()
        val bottomNavView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        bottomNavView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailsFragment -> {
                    bottomNavView.hideView()
                }
                else -> {
                    bottomNavView.showView()
                }
            }
        }
    }

    private fun observeConnection() {
        connectionLiveData.observe(this) {
            when (it) {
                true -> { Timber.d("Internet Available") }
                false -> {
                    Timber.d("No Internet Available")
                    showToast("No Internet")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        connectivityManager.registerConnectionObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityManager.unregisterConnectionObserver(this)
    }
}
