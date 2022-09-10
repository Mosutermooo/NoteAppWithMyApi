package com.premium.noteappwithmyapi.ui.auth_fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.premium.noteappwithmyapi.databinding.FragmentRegisterBinding
import com.premium.noteappwithmyapi.utils.Resource
import com.premium.noteappwithmyapi.utils.Resources.initProgressDialog
import com.premium.noteappwithmyapi.utils.Resources.setupToolBar
import com.premium.noteappwithmyapi.utils.Resources.showProgressDialog
import com.premium.noteappwithmyapi.utils.Resources.showSnackBar
import com.premium.noteappwithmyapi.utils.Resources.stopProgressDialog
import com.premium.noteappwithmyapi.view_models.AuthViewModel


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProgressDialog()
        setupToolBar(
            binding.toolbar,
            requireActivity() as AppCompatActivity,
        )

        authViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application = requireActivity().application).create(AuthViewModel::class.java)

        binding.Register.setOnClickListener {
            authViewModel.register(
                binding.email.text.toString(),
                binding.username.text.toString(),
                binding.password.text.toString()
            )
            lifecycleScope.launchWhenStarted {
                authViewModel.registerState.collect {
                    when(it){
                        is Resource.Error -> {
                            stopProgressDialog()
                            it.message?.let {message->
                                showSnackBar(message)
                            }
                        }
                        is Resource.Loading -> {
                            showProgressDialog()
                        }
                        is Resource.Success -> {
                            it.data?.let { data ->
                                Log.e("data", "$data")
                                showSnackBar("Successfully registered your account")
                                requireActivity().onBackPressed()
                            }
                        }
                    }
                }
            }
        }

    }

}