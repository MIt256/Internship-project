package com.example.taskmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.taskmanager.Adapter
import com.example.taskmanager.OnBoardingItem
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentWalkthroughBinding

class WalkthroughFragment : Fragment() {


    private var _binding: FragmentWalkthroughBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWalkthroughBinding.inflate(inflater, container, false)


        val words = arrayListOf(
            "One",
            "Two",
            "Three",
            "Four",
            "Five"
        )
        binding.viewPager2.adapter = Adapter(words)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

}