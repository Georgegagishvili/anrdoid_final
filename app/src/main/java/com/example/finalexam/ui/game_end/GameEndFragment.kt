package com.example.finalexam.ui.game_end

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalexam.R
import com.example.finalexam.databinding.FragmentGameBinding
import com.example.finalexam.databinding.FragmentGameEndBinding
import com.example.finalexam.ui.game.GameFragmentDirections

class GameEndFragment : Fragment() {
    private lateinit var binding: FragmentGameEndBinding
    private val args : GameEndFragmentArgs by navArgs()

    companion object {
        fun newInstance() = GameEndFragment()
    }

    private lateinit var viewModel: GameEndViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding =  FragmentGameEndBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GameEndViewModel::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val prefs: SharedPreferences? =
            activity?.getSharedPreferences("User", Context.MODE_PRIVATE)
        val userName = prefs!!.getString(getString(R.string.username),null).toString()
        binding.userName.text = "Nice try $userName!"

        val correctAnswerAmount = args.correctAnswerAmount
        val category = args.category
        binding.correctAnswers.text = "$correctAnswerAmount/10"

        if(correctAnswerAmount > 4) {
            binding.resultText.text = "Good Job"
        }

        binding.menuButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToMenuFragment2()
            view.findNavController().navigate(action)
        }

        binding.restartButton.setOnClickListener {
            val action =
                GameEndFragmentDirections.actionGameEndFragmentToGameFragment(category)
            view.findNavController().navigate(action)
        }

    }

}