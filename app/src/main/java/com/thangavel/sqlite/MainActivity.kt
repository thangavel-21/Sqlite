package com.thangavel.sqlite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thangavel.sqlite.databinding.ActivityMainBinding
import com.thangavel.sqlite.fragments.AllNotesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_manager, AllNotesFragment())
            .commit()
    }
}