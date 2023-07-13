package com.isabellacp.dev.app.yomu.activities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.ActionMode
import androidx.core.view.WindowInsetsControllerCompat
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.isabellacp.dev.app.yomu.R
import com.isabellacp.dev.app.yomu.databinding.ActivityPlayerBinding


class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener,
    AppCompatCallback {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var passedVideoId: String
    private var delegate: AppCompatDelegate? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = AppCompatDelegate.create(this, this)
        delegate!!.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        delegate!!.setTheme(R.style.AppTheme)
        delegate!!.setContentView(binding.root)

        changeStatusBarColor(R.color.colorPrimary, isDarkMode(applicationContext))

        passedVideoId = intent.getStringExtra("videoId") as String
        openYoutubeLink(passedVideoId);
    }

    fun openYoutubeLink(youtubeID: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeID))
        val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + youtubeID))
        try {
            this.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            this.startActivity(intentBrowser)
        }

    }

    private fun isDarkMode(context: Context): Boolean {
        val darkModeFlag =
            context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return darkModeFlag == Configuration.UI_MODE_NIGHT_YES
    }

    private fun changeStatusBarColor(color: Int, isLight: Boolean) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        window.navigationBarColor = color

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }


    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        Toast.makeText(this, "Initialization Success", Toast.LENGTH_SHORT).show()
        if (!p2) {
            p1?.loadVideo(passedVideoId)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.e("res: ", p1.toString())
        Toast.makeText(this, "Initialization failed", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.player_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportActionModeStarted(mode: ActionMode?) {} // we will leave this empty for now

    override fun onSupportActionModeFinished(mode: ActionMode?) {} // we will leave this empty for now

    override fun onWindowStartingSupportActionMode(callback: ActionMode.Callback?): ActionMode? {
        TODO("Not yet implemented")
    }
}