package ru.vafeen.hwonlesson4.ui.fragments.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vafeen.hwonlesson4.R
import ru.vafeen.hwonlesson4.databinding.LaunchBinding
import ru.vafeen.hwonlesson4.noui.logExecutor
import ru.vafeen.hwonlesson4.ui.launch.Launch
import ru.vafeen.hwonlesson4.ui.launch.LaunchPutGet

class UpComingLaunchFragment : Fragment(R.layout.launch) {
    private lateinit var binding: LaunchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LaunchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getSerializable(LaunchPutGet.LaunchKey.key) as? Launch

        logExecutor(mes = "launch = $data") // сюда приходят данные!!

        binding.apply {

            if (data != null) {
                launchname.text = data.name

                launchmodel.text = data.model

                launchstart.text = data.dateStart

                launchicon.setImageResource(data.image)
            }
        }
    }
}