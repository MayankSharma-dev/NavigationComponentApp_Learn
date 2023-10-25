package com.example.navigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponent.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    // retrieving values passed by the SafeArgs() plugin during navigation(advance intents!)
    private val args:WelcomeArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentWelcomeBinding.inflate(layoutInflater,container,false)

        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

                                       // getting the values
        binding.welcomeUsername.text = args.username
        binding.welcomePassword.text = args.password

        binding.welcomeBtn.setOnClickListener {

            val action = WelcomeDirections.actionWelcomeToHomeScreen()
            findNavController().navigate(action)

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}