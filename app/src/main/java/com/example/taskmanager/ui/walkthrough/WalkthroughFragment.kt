package com.example.taskmanager.ui.walkthrough

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager.ui.walkthrough.Adapter
import com.example.taskmanager.R
import com.example.taskmanager.ui.walkthrough.WalkthroughItem
import com.example.taskmanager.databinding.FragmentWalkthroughBinding

import javax.inject.Inject

class WalkthroughFragment : Fragment() {


    private var _binding: FragmentWalkthroughBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: Adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWalkthroughBinding.inflate(inflater, container, false)

        //TODO not good?
        val walkthroughs = arrayListOf(
            WalkthroughItem(
                resources.getString(R.string.welcome_to_text),resources.getString(R.string.whats_going_text),R.drawable.ic_first_walkthrough,R.drawable.ic_bottom_one_walkthrough
            ), WalkthroughItem(
                resources.getString(R.string.work_happens_text),resources.getString(R.string.get_notified_text),R.drawable.ic_second_walkthrough,R.drawable.ic_bottom_two_walkthrough
            ), WalkthroughItem(
                resources.getString(R.string.tasks_and_assign_text),resources.getString(R.string.tasks_and_text),R.drawable.ic_third_walkthrough,R.drawable.ic_bottom_three_walkthrough
            )
        )
        adapter = Adapter(walkthroughs){
            val action = WalkthroughFragmentDirections.actionWalkthroughFragment3ToSignUpFragment()
            findNavController().navigate(action)
        }
        binding.viewPager2.adapter = adapter
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }
}

//todo add dots under text