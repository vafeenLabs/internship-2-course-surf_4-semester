package ru.vafeen.hwonlesson3.main


import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import ru.vafeen.hwonlesson3.databinding.ActivityMainBinding
import ru.vafeen.hwonlesson3.noui.keys.PutGetKeys
import ru.vafeen.hwonlesson3.noui.keys.Uris
import ru.vafeen.hwonlesson3.noui.permissions.requestPostNotifications

class MainActivity : AppCompatActivity() {

    companion object {
        var message: String? = null
        var secretKey: String? = null
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        enableEdgeToEdge()

        if (savedInstanceState != null) {
            message = savedInstanceState.getString(PutGetKeys.Message.key)
            secretKey = savedInstanceState.getString(PutGetKeys.SecretKey.key)
        }


        requestPostNotifications(context = this)

        binding.getKeyButton.setOnClickListener {
            getSecretKey()
        }
    }

    @SuppressLint("Range")
    fun getSecretKey() {

        val cursor = applicationContext.contentResolver.query(
            Uri.parse(Uris.SecretKeyUri.uri),
            null,
            null,
            null,
            null
        )

        cursor?.use {
            if (it.moveToFirst()) {

                val key = it.getString(it.getColumnIndex("text"))

                if (key != null) {
                    secretKey = key
                }

                Toast.makeText(this, secretKey, Toast.LENGTH_LONG).show()
            }
        }
        Toast.makeText(this, secretKey, Toast.LENGTH_LONG).show()

        binding.secretkey.text = secretKey

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.let {
            it.putString(
                PutGetKeys.Message.key,
                message
            )
            it.putString(
                PutGetKeys.SecretKey.key,
                secretKey
            )
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.let {
            message = it.getString(PutGetKeys.Message.key) ?: "null"
            binding.message.text = message

            secretKey = it.getString(PutGetKeys.SecretKey.key) ?: "null"
            binding.secretkey.text = secretKey
        }


    }


}