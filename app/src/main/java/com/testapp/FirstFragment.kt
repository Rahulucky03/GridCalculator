package com.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.testapp.adapter.GridAdapter
import com.testapp.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.btnSubmit.setOnClickListener {
            val input = binding.etInput.text.toString()
            val sqrt = getSqrt(input.toInt())
            setAdapter(sqrt)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun setAdapter(spanCount: Int) {
        with(binding.recyclerView) {
            val adapter = GridAdapter(spanCount * spanCount)
            layoutManager = GridLayoutManager(requireContext(), spanCount)
            this.adapter = adapter
        }

    }

    fun getSqrt(num: Int): Int {
        var t: Int
        var sqrt: Int = num / 2
        do {
            t = sqrt
            sqrt = (t + (num / t)) / 2

        } while ((t - sqrt) != 0)
        return sqrt
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}