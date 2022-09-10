package com.premium.noteappwithmyapi.ui.auth_fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.premium.noteappwithmyapi.R
import com.premium.noteappwithmyapi.databinding.FragmentMainBinding
import com.premium.noteappwithmyapi.network.ApiInstance
import com.premium.noteappwithmyapi.network.SeasonManager
import com.premium.noteappwithmyapi.ui.activities.MainActivity
import com.premium.noteappwithmyapi.utils.Resource
import com.premium.noteappwithmyapi.utils.Resources.initProgressDialog
import com.premium.noteappwithmyapi.utils.Resources.showProgressDialog
import com.premium.noteappwithmyapi.utils.Resources.showSnackBar
import com.premium.noteappwithmyapi.utils.Resources.stopProgressDialog
import com.premium.noteappwithmyapi.view_models.AuthViewModel
import kotlinx.coroutines.flow.collectLatest


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProgressDialog()

        authViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application).create(AuthViewModel::class.java)

        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.Login.setOnClickListener {
            authViewModel.loginUser(
                binding.emailUsername.text.toString(),
                binding.password.text.toString()
            )
            lifecycleScope.launchWhenStarted {
                authViewModel.loginState.collectLatest {
                    when(it){
                        is Resource.Error -> {
                            stopProgressDialog()
                            showSnackBar(it.message.toString())
                        }
                        is Resource.Loading -> {
                            showProgressDialog()
                        }
                        is Resource.Success -> {
                            stopProgressDialog()
                            it.data?.let { response ->
                                showSnackBar(response.message)
                                val token = SeasonManager(requireContext()).fetchToken()
                                Log.e("token", "${token}")
                                ApiInstance.token = token
                                Intent(requireActivity(), MainActivity::class.java).also { intent ->
                                    startActivity(intent)
                                    requireActivity().finish()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}