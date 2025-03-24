package ru.vafeen.hwonlesson5.ui.activities.firstname

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.vafeen.hwonlesson5.databinding.ActivityFirstnameBinding
import ru.vafeen.hwonlesson5.noui.logs.logExecutor
import ru.vafeen.hwonlesson5.ui.activities.lastname.LastNameActivity
import ru.vafeen.hwonlesson5.values.PutGet


class FirstNameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstnameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFirstnameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        content()
    }

    private fun content() {
        val newIntent = Intent(this, LastNameActivity::class.java)

        binding.apply {
            backButton.visibility = View.VISIBLE

            exitButton.visibility = View.VISIBLE

            forwardButton.visibility = View.VISIBLE

            editFirstName.visibility = View.VISIBLE

            backButton.setOnClickListener {
                finish()
            }

            exitButton.setOnClickListener {
                this@FirstNameActivity.finishAffinity()
            }

            forwardButton.setOnClickListener {
                logExecutor(mes = "[firstNameAct] firstname = ${editFirstName.text}")

                newIntent.apply {
                    putExtra(PutGet.FirstName.value, editFirstName.text.toString())
                }

                startActivity(newIntent)
            }
        }
    }
}