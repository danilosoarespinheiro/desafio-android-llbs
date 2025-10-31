package com.example.desafioandroid.presentation.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.desafioandroid.domain.model.Boards
import com.example.desafioandroid.BuildConfig
import com.example.desafioandroid.data.api.TrelloService
import com.example.desafioandroid.databinding.FragmentBoardsBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BoardsFragment : Fragment() {

    private val key = BuildConfig.API_KEY
    private val token = BuildConfig.API_TOKEN
    private val url = BuildConfig.API_URL

    private val gson: Gson by lazy { GsonBuilder().create() }

    private val okHttp: OkHttpClient by lazy { OkHttpClient.Builder().build() }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private val service: TrelloService by lazy {
        retrofit.create(TrelloService::class.java)
    }

    private var _binding: FragmentBoardsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBoardsBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility = View.VISIBLE

        service.getBoards(key = key, token = token)
            .enqueue(object : Callback<Boards> {

                override fun onFailure(call: Call<Boards>, t: Throwable) {
                    binding.progressBar.visibility = View.GONE

                    val message = t.message
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Boards>, response: Response<Boards>) {
                    binding.progressBar.visibility = View.GONE

                    if (response.isSuccessful) {
                        val boards = response.body()!!
                        binding.recyclerView.adapter = BoardAdapter(boards)
                    } else {
                        val message = response.message()
                        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
