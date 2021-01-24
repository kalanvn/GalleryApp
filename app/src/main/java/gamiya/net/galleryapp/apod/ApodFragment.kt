package gamiya.net.galleryapp.apod

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import gamiya.net.galleryapp.R

class ApodFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_apod, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this).get(ApodFragmentViewModel::class.java)

//        viewModel.response.observe(viewLifecycleOwner, {
//            view.findViewById<TextView>(R.id.explanation_text).text = it
//        })

        val imageView = view.findViewById<ImageView>(R.id.imageview_apod)
        viewModel.apod.observe(viewLifecycleOwner, {
            it?.let {
                view.findViewById<TextView>(R.id.explanation_text).text = it.explanation
                val uri = it.url.toUri().buildUpon().scheme("https").build()
                Glide.with(this)
                    .load(uri)
                    .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                    .into(imageView)
            }
        })
        viewModel.getApod()
    }
}