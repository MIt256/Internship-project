package com.example.taskmanager.ui.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager.ui.walkthrough.Adapter
import com.example.taskmanager.R
import com.example.taskmanager.UserSharedViewModel
import com.example.taskmanager.ui.walkthrough.WalkthroughItem
import com.example.taskmanager.databinding.FragmentWalkthroughBinding
import com.example.taskmanager.ui.task.TaskFragmentDirections
import com.example.taskmanager.ui.task.TaskViewModel

import javax.inject.Inject

class WalkthroughFragment : Fragment() {

    private lateinit var binding: FragmentWalkthroughBinding
    private val userViewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentWalkthroughBinding.inflate(inflater, container, false)

        if (userViewModel.isAuthenticated.value == true) {
            val action =
                WalkthroughFragmentDirections.actionWalkthroughFragmentToNavigationTask()
            findNavController().navigate(action)
        }

        //TODO not good?
        val walkthroughs = arrayListOf(
            WalkthroughItem(
                resources.getString(R.string.welcome_to_text),
                resources.getString(R.string.whats_going_text),
                R.drawable.ic_first_walkthrough,
                R.drawable.ic_bottom_one_walkthrough
            ), WalkthroughItem(
                resources.getString(R.string.work_happens_text),
                resources.getString(R.string.get_notified_text),
                R.drawable.ic_second_walkthrough,
                R.drawable.ic_bottom_two_walkthrough
            ), WalkthroughItem(
                resources.getString(R.string.tasks_and_assign_text),
                resources.getString(R.string.tasks_and_text),
                R.drawable.ic_third_walkthrough,
                R.drawable.ic_bottom_three_walkthrough
            )
        )
        binding.viewPager.adapter = Adapter(walkthroughs) {
            val action = WalkthroughFragmentDirections.actionWalkthroughFragmentToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}

//todo add dots under text