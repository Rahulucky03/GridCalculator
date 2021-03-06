package com.testapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.testapp.adapter.PersonAdapter
import com.testapp.databinding.FragmentSecondBinding
import com.testapp.model.DataItem
import com.testapp.model.UserResponse
import com.testapp.nw.RetrofitClient
import com.testapp.utils.PaginationListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var paginationListener: PaginationListener
    private lateinit var personAdapter: PersonAdapter
    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.recyclerView) {
            personAdapter = PersonAdapter()
            val linearLayoutManager = LinearLayoutManager(requireContext())
            layoutManager = linearLayoutManager
            this.adapter = personAdapter
            paginationListener = object : PaginationListener(linearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                    getUsers(page)
                }
            }
            addOnScrollListener(paginationListener)
        }

        binding.swipeRefresh.setOnRefreshListener {
            resetScreen()
        }

        getUsers(1)

    }

    private fun resetScreen() {
        paginationListener.resetState()
        getUsers(1)
        personAdapter.clear()
    }

    private fun getUsers(page: Int) {
        binding.swipeRefresh.isRefreshing = true
        val getsuperHeroes = RetrofitClient.getMyApi()?.getsuperHeroes(page)
        getsuperHeroes?.enqueue(object : Callback<UserResponse?> {
            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                binding.swipeRefresh.isRefreshing = false
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        if (::personAdapter.isInitialized) {
                            personAdapter.addAll(response.body()?.data ?: emptyList<DataItem>())
                        }
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                binding.swipeRefresh.isRefreshing = false
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}