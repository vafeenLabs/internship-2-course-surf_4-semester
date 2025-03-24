package ru.vafeen.hwonlesson4


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vafeen.hwonlesson4.databinding.ActivityMainBinding
import ru.vafeen.hwonlesson4.databinding.LaunchBinding
import ru.vafeen.hwonlesson4.ui.rocket.Rocket
import ru.vafeen.hwonlesson4.ui.TabRowNaming
import ru.vafeen.hwonlesson4.ui.fragments.upcoming.UpComingItem
import ru.vafeen.hwonlesson4.ui.fragments.upcoming.UpComingItemFragment
import ru.vafeen.hwonlesson4.ui.fragments.upcoming.UpComingLaunchFragment
import ru.vafeen.hwonlesson4.ui.fragments.launcherslist.LaunchersAdapter
import ru.vafeen.hwonlesson4.ui.fragments.rocketslist.RocketsAdapter
import ru.vafeen.hwonlesson4.ui.launch.Launch
import ru.vafeen.hwonlesson4.ui.launch.LaunchPutGet


class MainActivity : AppCompatActivity() {

    companion object {
        var context: Context? = null
    }

    private lateinit var bindingMainActivity: ActivityMainBinding
    private lateinit var bindingLaunchBinding: LaunchBinding

    val launchersAdapter = LaunchersAdapter()
    val rocketsAdapter = RocketsAdapter()

    private val launchers = listOf(
        Launch(
            name = "Startlink 2",
            model = "CCAES SLC 40",
            dateStart = "16-10-2016",
            image = R.drawable.falconsat01
        ),
        Launch(
            name = "DemoSat",
            model = "AAAES SLC 40",
            dateStart = "06-07-2018",
            image = R.drawable.falcon9
        ),
        Launch(
            name = "Falcon 9 Test",
            model = "CCAES SEC 40",
            dateStart = "18-03-2019",
            image = R.drawable.demosat02
        ),
        Launch(
            name = "CRS - 2",
            model = "CAAES SLC 40",
            dateStart = "18-12-2019",
            image = R.drawable.crs
        ),
    )

    private val rockets = listOf(
        Rocket(
            name = "Falcon 1",
            active = false,
            image = R.drawable.falcon09
        ),
        Rocket(
            name = "Big Falcon Rocket",
            active = true,
            image = R.drawable.falconsat01
        ),
    )

    private val ids = listOf(
        R.id.upComingFragmentLaunchDate,
        R.id.upComingFragmentLaunchSite,
        R.id.upComingFragmentLaunchCountDown
    )

    private val singletonLaunch = Launch(
        name = "Startlink 2",
        model = "CCAES SLC 40",
        dateStart = "16-10-2016",
        image = R.drawable.crs
    )


    private val upComingItems = listOf(
        UpComingItem(
            title = "LAUNCH DATE",
            text = "Thu Oct 17 5:30:00 2019"
        ),
        UpComingItem(
            title = "LAUNCH SITE",
            text = "Cape Canaveral Air Force Station Space \nLaunch Complex 40"
        ),
        UpComingItem(
            title = "COUNT DOWN",
            "5 Hrs 30 mins more...",
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = this

        enableEdgeToEdge()

        bindingMainActivity = ActivityMainBinding.inflate(layoutInflater)

        bindingLaunchBinding = LaunchBinding.inflate(layoutInflater)

        setContentView(bindingMainActivity.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.upComingFragmentLaunch, UpComingLaunchFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(
                        LaunchPutGet.LaunchKey.key,
                        singletonLaunch
                    )
                }
            })
            .commit()



        supportFragmentManager.beginTransaction()
            .let {
                for (index in 0..ids.lastIndex) {
                    it.replace(ids[index], UpComingItemFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable(
                                LaunchPutGet.ItemKey.key,
                                upComingItems[index]
                            )
                        }
                    })
                }
                it
            }.commit()

        setLaunchers()

        setRockets()

        content()
    }

    private fun switchTabs(current: TabRowNaming) {
//        logExecutor(mes = "switchColors")

        when (current) {
            TabRowNaming.Upcoming -> {
                bindingMainActivity.apply {
                    upcoming.setTextColor(resources.getColor(R.color.red))

                    launchers.setTextColor(resources.getColor(R.color.black))

                    rockets.setTextColor(resources.getColor(R.color.black))

                    upComingViewSetVisible(visibility = true)

                    launchersSetVisible(visibility = false)

                    rocketsSetVisible(visibility = false)
                }

            }


            TabRowNaming.Launchers -> {
                bindingMainActivity.apply {
                    upcoming.setTextColor(resources.getColor(R.color.black))

                    launchers.setTextColor(resources.getColor(R.color.red))

                    rockets.setTextColor(resources.getColor(R.color.black))

                    upComingViewSetVisible(visibility = false)

                    launchersSetVisible(visibility = true)

                    rocketsSetVisible(visibility = false)

                }
            }

            TabRowNaming.Rockets -> {
                bindingMainActivity.apply {
                    upcoming.setTextColor(resources.getColor(R.color.black))

                    launchers.setTextColor(resources.getColor(R.color.black))

                    rockets.setTextColor(resources.getColor(R.color.red))

                    upComingViewSetVisible(visibility = false)

                    launchersSetVisible(visibility = false)

                    rocketsSetVisible(visibility = true)

                }

            }


        }
    }


    private fun upComingViewSetVisible(visibility: Boolean) {
        bindingMainActivity.apply {
            upComingFragmentLaunch.isVisible = visibility

            upComingFragmentLaunchDate.isVisible = visibility

            upComingFragmentLaunchSite.isVisible = visibility

            upComingFragmentLaunchCountDown.isVisible = visibility

        }
    }

    private fun setLaunchers() {
        val context = this

        launchersAdapter.launchers = launchers

        bindingMainActivity.launchesList.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = launchersAdapter
        }

        launchersAdapter.notifyDataSetChanged()
    }

    private fun setRockets() {
        val context = this

        rocketsAdapter.rockets = rockets

        bindingMainActivity.rocketsList.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = rocketsAdapter
        }

        launchersAdapter.notifyDataSetChanged()
    }

    private fun launchersSetVisible(visibility: Boolean) {
        bindingMainActivity.apply {

            launchesList.isVisible = visibility

        }
    }

    private fun rocketsSetVisible(visibility: Boolean) {
        bindingMainActivity.apply {

            rocketsList.isVisible = visibility

        }
    }

    private fun content() {

        switchTabs(TabRowNaming.Upcoming)

        bindingMainActivity.apply {
            upcoming.setOnClickListener {

                switchTabs(TabRowNaming.Upcoming)

            }

            launchers.setOnClickListener {

                switchTabs(TabRowNaming.Launchers)

            }

            rockets.setOnClickListener {

                switchTabs(TabRowNaming.Rockets)

            }

        }

    }


}