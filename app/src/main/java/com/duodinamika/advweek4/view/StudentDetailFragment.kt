package com.duodinamika.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.duodinamika.advweek4.R
import com.duodinamika.advweek4.databinding.FragmentStudentDetailBinding
import com.duodinamika.advweek4.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso

class StudentDetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    var txtId: TextView?=null
    var txtName:TextView ?=null
    var txtBod:TextView ?=null
    var txtPhone:TextView ?=null
    var studentId:String?=null
    var studentImg: ImageView?=null
    private lateinit var binding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

//        txtId = view?.findViewById(R.id.txtId)
//        txtName = view?.findViewById(R.id.txtName)
//        txtBod = view?.findViewById(R.id.txtBod)
//        txtPhone = view?.findViewById(R.id.txtPhone)
//        studentImg = view?.findViewById(R.id.studentImg)

//        arguments?.let {
//            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
//        }

        studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
        detailViewModel.fetch(studentId!!, requireContext())
        Log.e("XXX", "onViewCreated: id: $studentId ", )
        observeViewModel()
//        studentId?.let {
//            detailViewModel.fetch(it, requireContext())
//        }



//        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
//            val studentId = student.id
//            val studentName = student.name
//            val studentBirthdate = student.bod
//            val studentPhoneNumber = student.phone
//            val studentImage = student.photoUrl
//
//            txtId?.setText(studentId)
//            txtName?.setText(studentName)
//            txtBod?.setText(studentBirthdate)
//            txtPhone?.setText(studentPhoneNumber)
//
//            val picasso = Picasso.Builder(requireContext())
//            picasso.listener { picasso, uri, exception ->
//                exception.printStackTrace()
//            }
//
//            picasso.build().load(studentImage).into(studentImg)
//
//        })


    }

    fun observeViewModel() {
        Log.e("xxx", "Before observing studentLD")
        detailViewModel.studentLD.observe(viewLifecycleOwner) { student ->
            Log.e("xxx", "Inside observeViewModel")
            if (student != null) {
                binding.student = student
                Log.e("xxx", "observeViewModel studid:${student.id}")
            } else {
                Log.e("xxx", "observeViewModel: Student is null")
            }
        }
        Log.e("xxx", "After observing studentLD")
    }
}