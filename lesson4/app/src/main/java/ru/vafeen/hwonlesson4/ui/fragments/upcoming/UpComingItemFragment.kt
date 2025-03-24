package ru.vafeen.hwonlesson4.ui.fragments.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vafeen.hwonlesson4.R
import ru.vafeen.hwonlesson4.databinding.UpcomingInfoItemBinding
import ru.vafeen.hwonlesson4.noui.logExecutor
import ru.vafeen.hwonlesson4.ui.launch.LaunchPutGet

class UpComingItemFragment : Fragment(R.layout.upcoming_info_item) {
    private lateinit var binding: UpcomingInfoItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UpcomingInfoItemBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = arguments?.getSerializable(LaunchPutGet.ItemKey.key) as? UpComingItem

        logExecutor(mes = "item = $data") // сюда приходят данные!!

        binding.apply {

            if (data != null) {
                title.text = data.title

                text.text = data.text
            }
        }

    }
}