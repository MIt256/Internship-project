package com.example.taskmanager.ui

import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentSignInBinding
import com.example.taskmanager.entities.NetworkResult
import com.example.taskmanager.entities.SignInEntity
import com.example.taskmanager.entities.SignUpEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        //restore state
        if (savedInstanceState != null) {
            binding.editTextTextEmailAddress.setText(savedInstanceState.getString(USER_EMAIL))
            binding.editTextTextPassword.setText(savedInstanceState.getString(USER_PASSWORD))
        }

        binding.bottomSignUp.setOnClickListener{onSignUpPressed()}
        binding.buttonSignIn.setOnClickListener{onSignInButtonPressed()}
        binding.forgotText.setOnClickListener{onForgotPasswordPressed()}

        //observe response
        viewModel.signInResponse.observe(this.viewLifecycleOwner) {
            when(it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                }

                is  NetworkResult.Success -> {
                    //todo add success
                    val action = SignInFragmentDirections.actionSignInFragmentToNavigationTask()
                    findNavController().navigate(action)
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_EMAIL, binding.editTextTextEmailAddress.text.toString())
        outState.putString(USER_PASSWORD, binding.editTextTextPassword.text.toString())
    }

    private fun onSignInButtonPressed(){
        val userInfo = SignInEntity(
            email = binding.editTextTextEmailAddress.text.toString(),
            password = binding.editTextTextPassword.text.toString()
        )
        viewModel.signIn(userInfo)
    }

    private fun onForgotPasswordPressed(){
        //todo add forgot password feature
        Toast.makeText(context, "Currently, this feature is not implemented", Toast.LENGTH_SHORT).show()
    }

    private fun onSignUpPressed(){
        val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        findNavController().navigate(action)
    }

    companion object {
        const val USER_EMAIL = "user_email"
        const val USER_PASSWORD = "user_password"
    }

}