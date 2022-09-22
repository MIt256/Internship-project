package com.example.taskmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.entities.NetworkResult
import com.example.taskmanager.databinding.FragmentSignUpBinding
import com.example.taskmanager.entities.SignUpEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        //restore state
        if (savedInstanceState != null) {
            binding.editTextTextPersonName.setText(savedInstanceState.getString(USER_NAME))
            binding.editTextTextEmailAddress.setText(savedInstanceState.getString(USER_EMAIL))
            binding.editTextTextPassword.setText(savedInstanceState.getString(USER_PASSWORD))
        }
        //for buttons
        binding.bottomSignIn.setOnClickListener{onSignInPressed()}
        binding.buttonSignUp.setOnClickListener{onSignUpButtonPressed()}
        binding.profileImage.setOnClickListener{onProfileImagePressed()}
        binding.backArrow.setOnClickListener { onBackArrowPressed() }

       // vm.repres.onEach {  }.catch {  }.launchIn(lifecycle.coroutineScope)

        //observe response
        viewModel.signUpResponse.observe(this.viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                }

                is  NetworkResult.Success -> {
                    //todo add success
                    val action = SignUpFragmentDirections.actionSignUpFragmentToNavigationTask()
                    findNavController().navigate(action)
                }
            }
        }


        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_NAME, binding.editTextTextPersonName.text.toString())
        outState.putString(USER_EMAIL, binding.editTextTextEmailAddress.text.toString())
        outState.putString(USER_PASSWORD, binding.editTextTextPassword.text.toString())
    }

    private fun onSignInPressed(){
        val action = SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
        findNavController().navigate(action)
    }

    private fun onBackArrowPressed(){
        val action = SignUpFragmentDirections.actionSignUpFragmentToWalkthroughFragment()
        findNavController().navigate(action)
    }

    private fun onProfileImagePressed(){
        //todo add profile image feature
        Toast.makeText(context, "Currently, this feature is not implemented", Toast.LENGTH_SHORT).show()
    }

    private fun onSignUpButtonPressed(){
        val userInfo = SignUpEntity(
            username = binding.editTextTextPersonName.text.toString(),
            email = binding.editTextTextEmailAddress.text.toString(),
            password = binding.editTextTextPassword.text.toString()
        )
        viewModel.signUp(userInfo)
    }

    companion object {
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_PASSWORD = "user_password"
    }
}