package com.example.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponent.databinding.FragmentLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // getting values from deepLinks using SafeArgs()
    private val args:LoginArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //it may be used to hide the bottomNavView in the Login Fragment.
        // it is working but also hiding bottomNavView from every Fragment, i.e not working.
//        val view:BottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)
//        view.visibility = View.GONE
        // \\

        // getting and setting the values from deeplink SafeArgs to username editField
        val deepLinkUsername = args.username
        binding.username.setText(deepLinkUsername)


        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val password = binding.password.text.toString()
            if(username.isNotEmpty() && password.isNotEmpty()){

                // sending arguments using Safeargs
                val action = LoginDirections.actionLogin2ToWelcome(username,password)
                findNavController().navigate(action)

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}