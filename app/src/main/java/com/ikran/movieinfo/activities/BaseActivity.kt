package com.ikran.movieinfo.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.ikran.movieinfo.R
import kotlinx.android.synthetic.main.activity_base.*

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        navigationController = findNavController(R.id.fragmentNavHost)
        appBarConfiguration = AppBarConfiguration(navigationController.graph)
    }

    override fun onSupportNavigateUp() = navigationController.navigateUp()
            || super.onSupportNavigateUp()

    fun showLoading() = appProgressBar.show()

    fun hideLoading() = appProgressBar.hide()

    override fun onBackPressed() {
        appProgressBar.hide()
        super.onBackPressed()
    }

}