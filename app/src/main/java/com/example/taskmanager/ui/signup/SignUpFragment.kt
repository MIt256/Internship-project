package com.example.taskmanager.ui.signup

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
import com.example.taskmanager.data.remote.utils.NetworkResult
import com.example.taskmanager.databinding.FragmentSignUpBinding
import com.example.taskmanager.data.remote.model.accounts.SignUpEntity
import com.example.taskmanager.ui.signup.vm.SignUpViewModel
import com.example.taskmanager.utils.ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class   SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()
    private val userViewModel: UserSharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        //restore state
        if (savedInstanceState != null) {
            binding.editTextPersonName.setText(savedInstanceState.getString(USER_NAME))
            binding.editTextEmailAddress.setText(savedInstanceState.getString(USER_EMAIL))
            binding.editTextPassword.setText(savedInstanceState.getString(USER_PASSWORD))
        }
        //for buttons
        binding.bottomSignIn.setOnClickListener { onSignInPressed() }
        binding.buttonSignUp.setOnClickListener { onSignUpButtonPressed() }
        binding.profileImage.setOnClickListener { onProfileImagePressed() }
        binding.backArrow.setOnClickListener { onBackArrowPressed() }

        // vm.repres.onEach {  }.catch {  }.launchIn(lifecycle.coroutineScope)

        //observe response
        viewModel.signUpResponse.observe(this.viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Loading -> {
                    //todo add loading
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(context, it.errorMessage, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Success -> {
                    userViewModel.setStatus(true)
                    //todo add success
                    val action =
                        SignUpFragmentDirections.actionSignUpFragmentToNavigationTask()
                    findNavController().navigate(action)
                }
            }
        }


        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(USER_NAME, binding.editTextPersonName.text.toString())
        outState.putString(USER_EMAIL, binding.editTextEmailAddress.text.toString())
        outState.putString(USER_PASSWORD, binding.editTextPassword.text.toString())
    }

    private fun onSignInPressed() {
        val action =
            SignUpFragmentDirections.actionSignUpFragmentToSignInFragment()
        findNavController().navigate(action)
    }

    private fun onBackArrowPressed() {
        val action =
            SignUpFragmentDirections.actionSignUpFragmentToWalkthroughFragment()
        findNavController().navigate(action)
    }

    private fun onProfileImagePressed() {
        //todo add profile image feature
        Toast.makeText(context, "Currently, this feature is not implemented", Toast.LENGTH_SHORT)
            .show()
    }

    private fun onSignUpButtonPressed() {
//        val userInfo = SignUpEntity(
//            username = binding.editTextPersonName.text.toString(),
//            email = binding.editTextEmailAddress.text.toString(),
//            password = binding.editTextPassword.text.toString()
//        )
//        viewModel.signUp(userInfo)
        ID.IDString.id = "12341414"
        val action =
            SignUpFragmentDirections.actionSignUpFragmentToNavigationTask()
        findNavController().navigate(action)
    }

    private companion object {
        const val USER_NAME = "user_name"
        const val USER_EMAIL = "user_email"
        const val USER_PASSWORD = "user_password"
    }
}