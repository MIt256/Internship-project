package com.example.taskmanager.ui.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.UserSharedViewModel
import com.example.taskmanager.databinding.FragmentSignInBinding
import com.example.taskmanager.dto.NetworkResult
import com.example.taskmanager.dto.SignInEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: SignInViewModel by viewModels()
    private val userViewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        //restore state
        if (savedInstanceState != null) {
            binding.editTextEmailAddress.setText(savedInstanceState.getString(USER_EMAIL))
            binding.editTextPassword.setText(savedInstanceState.getString(USER_PASSWORD))
        }

        binding.bottomSignUp.setOnClickListener { onSignUpPressed() }
        binding.buttonSignIn.setOnClickListener { onSignInButtonPressed() }
        binding.forgotText.setOnClickListener { onForgotPasswordPressed() }
        binding.backArrow.setOnClickListener { onBackArrowPressed() }

        //observe response
        viewModel.signInResponse.observe(this.viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    //todo add error
                }

                is NetworkResult.Success -> {
                    //todo add success
                    userViewModel.setStatus(true)

                    val action =
                        SignInFragmentDirections.actionSignInFragmentToNavigationTask()
                    findNavController().navigate(action)
                }
            }
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_EMAIL, binding.editTextEmailAddress.text.toString())
        outState.putString(USER_PASSWORD, binding.editTextPassword.text.toString())
    }

    private fun onSignInButtonPressed() {
        val userInfo = SignInEntity(
            email = binding.editTextEmailAddress.text.toString(),
            password = binding.editTextPassword.text.toString()
        )
        viewModel.signIn(userInfo)
    }

    private fun onBackArrowPressed() {
        val action =
            SignInFragmentDirections.actionSignInFragmentToWalkthroughFragment()
        findNavController().navigate(action)
    }

    private fun onForgotPasswordPressed() {
        //todo add forgot password feature
        Toast.makeText(context, "Currently, this feature is not implemented", Toast.LENGTH_SHORT)
            .show()
    }

    private fun onSignUpPressed() {
        val action =
            SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
        findNavController().navigate(action)
    }

    private companion object {
        const val USER_EMAIL = "user_email"
        const val USER_PASSWORD = "user_password"
    }

}