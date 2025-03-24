package ru.vafeen.hwonlesson5.ui.activities.lastname

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.vafeen.hwonlesson5.databinding.ActivityLastnameBinding
import ru.vafeen.hwonlesson5.noui.logs.logExecutor
import ru.vafeen.hwonlesson5.ui.activities.age.AgeActivity
import ru.vafeen.hwonlesson5.values.PutGet

class LastNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLastnameBinding

    private var firstNameData: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLastnameBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intent.apply {
            firstNameData = getStringExtra(PutGet.FirstName.value)

            logExecutor(mes = "[lstNmAct] firstNameData=${firstNameData}")
        }


        content()
    }

    private fun content() {
        val newIntent = Intent(this, AgeActivity::class.java)

        binding.apply {

            textData.text = "firstname = $firstNameData"

            textData.visibility = View.VISIBLE

            editLastName.visibility = View.VISIBLE

            backButton.setOnClickListener {
                finish()
            }

            exitButton.setOnClickListener {
                this@LastNameActivity.finishAffinity()
            }

            forwardButton.setOnClickListener {

                newIntent.apply {
                    putExtra(PutGet.FirstName.value, firstNameData)

                    putExtra(PutGet.LastName.value, editLastName.text.toString())
                }

                startActivity(newIntent)
            }
        }
    }
}