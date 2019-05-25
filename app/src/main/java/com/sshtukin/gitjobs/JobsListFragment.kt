package com.sshtukin.gitjobs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sshtukin.gitjobs.model.Job
import kotlinx.android.synthetic.main.fragment_jobslist.*
import kotlinx.android.synthetic.main.job_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobsListFragment : Fragment() {

    private var jobsAdapter: JobsAdapter = JobsAdapter()
    private var jobsList: MutableList<Job> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_jobslist, container, false)
    }

    override fun onStart() {
        super.onStart()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = jobsAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        val mRetrofit = RetrofitClient.getClient(GIT_URL)
        val mClient = mRetrofit.create(GitApi::class.java)
        val call = mClient.getJobResults("java", "london")
        callEnqueue(call)
    }

    private fun callEnqueue(call: Call<List<Job>>) {
        call.enqueue(object : Callback<List<Job>> {
            override fun onResponse(call: Call<List<Job>>, response: Response<List<Job>>) {
                jobsList = response.body() as MutableList<Job>
                jobsAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Job>>, t: Throwable) {
                Log.e(TAG, t.message)
            }
        })
    }

    inner class JobsAdapter: RecyclerView.Adapter<JobHolder>() {

        override fun getItemCount(): Int {
            return jobsList.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobHolder {
            return JobHolder(LayoutInflater.from(parent.context).inflate(R.layout.job_item, parent, false))
        }

        override fun onBindViewHolder(holder: JobHolder, position: Int) {
            holder.tvCompany.text = jobsList[position].company
            holder.tvPosition.text = jobsList[position].position
            holder.tvLocation.text = jobsList[position].location
        }
    }

    inner class JobHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCompany: TextView = view.tvCompany
        val tvPosition: TextView = view.tvPosition
        val tvLocation: TextView = view.tvLocation
    }

    companion object {
        private const val GIT_URL = "https://jobs.github.com"
        private const val TAG = "JobsListFragment"
    }
}