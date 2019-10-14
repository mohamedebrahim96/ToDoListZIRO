package com.example.todolistziro.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistziro.R
import android.view.animation.DecelerateInterpolator
import android.animation.ObjectAnimator
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todolistziro.data.Note
import com.example.todolistziro.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_home_notes.*


class HomeNotes : BaseFragment() {

    private var noteViewModel: NoteViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home_notes, container, false)

        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        animation()
        getData()

    }

    private fun getData() {
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel!!.allNotes.observe(this, object : Observer<List<Note>> {
            override fun onChanged(@Nullable notes: List<Note>) {
                var personalCounter:Int = 0
                var workCounter:Int = 0
                var healthCounter:Int = 0

                for (i in notes){
                    if(i.priority == 0)
                        workCounter++
                    else if(i.priority == 1)
                        personalCounter++
                    else if(i.priority == 2)
                        healthCounter++
                }
                personal_value_textview.setText(personalCounter.toString())
                work_value_textview.setText(workCounter.toString())
                health_value_textview.setText(healthCounter.toString())


            }
        })
    }

    private fun animation() {
        val animation = ObjectAnimator.ofInt(
            onboarding_activity_progress_bar,
            "progress",
            0,
            70
        ) // see this max value coming back here, we animate towards that value
        animation.duration = 5000 // in milliseconds
        animation.interpolator = DecelerateInterpolator()
        animation.start()    }


}