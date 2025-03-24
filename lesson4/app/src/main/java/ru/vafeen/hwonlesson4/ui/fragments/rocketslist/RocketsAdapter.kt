package ru.vafeen.hwonlesson4.ui.fragments.rocketslist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.vafeen.hwonlesson4.MainActivity
import ru.vafeen.hwonlesson4.R
import ru.vafeen.hwonlesson4.ui.rocket.Rocket
import ru.vafeen.hwonlesson4.ui.rocket.RocketIsActive

class RocketsAdapter : RecyclerView.Adapter<RocketsAdapter.ViewHolder>() {

    var rockets: List<Rocket> = listOf()
    val context = this

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val rocketIcon: ImageView = itemView.findViewById(R.id.rocketicon)

        val rocketName: TextView = itemView.findViewById(R.id.rocketname)

        val rocketIsActive: TextView = itemView.findViewById(R.id.rocketisactive)

        fun bind(rocket: Rocket) {
            rocketName.text = rocket.name

            if (rocket.active) {
                rocketIsActive.text = RocketIsActive.Active.str

                rocketIsActive.setBackgroundResource(R.drawable.rocket_button_shape_active)
            } else {
                rocketIsActive.text = RocketIsActive.Inactive.str

                rocketIsActive.setBackgroundResource(R.drawable.rocket_button_shape_inactive)

            }

            rocketIsActive.setOnClickListener {
                if (rocket.active) {

                    Toast.makeText(
                        MainActivity.context,
                        "Одна из ракет ${rocket.name} запущена на орбиту!",
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    Toast.makeText(
                        MainActivity.context,
                        "Ракета не готова к запуску",
                        Toast.LENGTH_LONG
                    ).show()

                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.rocket,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = rockets.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(rockets[position])

        holder.rocketIcon.setImageResource(rockets[position].image)

    }
}