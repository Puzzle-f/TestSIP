package com.example.testsip.ui.users.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.testsip.data.UsersItem
import com.example.testsip.databinding.FragmentDetailsUserBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment()  {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private var _binding: FragmentDetailsUserBinding? = null
    private val binding get() = _binding!!
    private var user: UsersItem? = null
    private val KEY_USER = "key_user"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun readArgument(bundle: Bundle){
        user = bundle.getParcelable(KEY_USER) as UsersItem?
        user?.let {
            detailsViewModel.emitDetailsUser(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        if(args != null){
            readArgument(args)
        }
        initObservers()
        binding.container.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            detailsViewModel.user
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { user ->
                    if (user != null) {
                        with(binding) {
                            id.text = user.id.toString()
                            name.text = user.name
                            userName.text = user.username
                            email.text = user.email
                            address.text = user.address.toString()
                            phone.text = user.phone
                            webSite.text = user.website
                            company.text = "${user.company}"
                        }
                    }
                }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}