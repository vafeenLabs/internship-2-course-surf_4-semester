package ru.vafeen.hwonlesson5.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.vafeen.hwonlesson5.databinding.ActivityMainBinding
import ru.vafeen.hwonlesson5.ui.activities.firstname.FirstNameActivity
import ru.vafeen.hwonlesson5.values.PutGet

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var firstname: String? = null

    private var lastname: String? = null

    private var isRegistered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.apply {
                isRegistered = getBooleanExtra(PutGet.IsRegistered.value, false)

                firstname = getStringExtra(PutGet.FirstName.value)

                lastname = getStringExtra(PutGet.LastName.value)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        content()
    }

    private fun content() {
        val intent = Intent(this, FirstNameActivity::class.java)

        binding.apply {

            buttonExit.visibility = View.VISIBLE

            if (!isRegistered) {

                greeting.visibility = View.GONE

                buttonBeginSigningUp.visibility = View.VISIBLE

            } else {
                greeting.text = "Добро пожаловать, $firstname $lastname"

                buttonBeginSigningUp.visibility = View.GONE
            }

            binding.buttonBeginSigningUp.setOnClickListener {
                startActivity(intent)
            }

            binding.buttonExit.setOnClickListener {
                finish()
            }
        }

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putBoolean(PutGet.IsRegistered.value, isRegistered)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.apply {
            isRegistered = getBoolean(PutGet.IsRegistered.value)

            firstname = getString(PutGet.FirstName.value)

            lastname = getString(PutGet.LastName.value)
        }
    }
}
