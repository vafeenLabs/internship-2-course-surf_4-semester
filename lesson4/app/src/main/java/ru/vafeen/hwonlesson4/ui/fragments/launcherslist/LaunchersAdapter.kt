package ru.vafeen.hwonlesson4.ui.fragments.launcherslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.vafeen.hwonlesson4.R
import ru.vafeen.hwonlesson4.ui.launch.Launch

class LaunchersAdapter : RecyclerView.Adapter<LaunchersAdapter.ViewHolder>() {

    var launchers: List<Launch> = listOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val launchIcon: ImageView = itemView.findViewById(R.id.launchicon)

        val launchName: TextView = itemView.findViewById(R.id.launchname)

        val launchModel: TextView = itemView.findViewById(R.id.launchmodel)

        val launchStart: TextView = itemView.findViewById(R.id.launchstart)

        fun bind(launch: Launch) {
            launchName.text = launch.name

            launchModel.text = launch.model

            launchStart.text = launch.dateStart
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.launch,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = launchers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(launchers[position])

        holder.launchIcon.setImageResource(launchers[position].image)

    }
}