package com.sshtukin.gitjobs

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity which contains [JobsListFragment]
 *
 * @author Sergey Shtukin
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentHolder, JobsListFragment())
            .commit()
    }
}
