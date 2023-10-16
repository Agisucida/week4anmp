package com.duodinamika.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.duodinamika.advweek4.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        txtId = view?.findViewById(R.id.txtId)
        txtName = view?.findViewById(R.id.txtName)
        txtBod = view?.findViewById(R.id.txtBod)
        txtPhone = view?.findViewById(R.id.txtPhone)
        studentImg = view?.findViewById(R.id.studentImg)

        arguments?.let {
            studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
        }

        studentId?.let {
            detailViewModel.fetch(it, requireContext())
        }

        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer { student ->
            val studentId = student.id
            val studentName = student.name
            val studentBirthdate = student.bod
            val studentPhoneNumber = student.phone
            val studentImage = student.photoUrl

            txtId?.setText(studentId)
            txtName?.setText(studentName)
            txtBod?.setText(studentBirthdate)
            txtPhone?.setText(studentPhoneNumber)

            val picasso = Picasso.Builder(requireContext())
            picasso.listener { picasso, uri, exception ->
                exception.printStackTrace()
            }

            picasso.build().load(studentImage).into(studentImg)

        })


    }
}