package com.example.finalproject.ui.recipe.details

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.finalproject.R
import com.example.finalproject.data.model.Meal
import android.webkit.CookieManager

class RecipeDetailFragment : Fragment(R.layout.fragment_recipe_details) {

    private val viewModel: RecipeDetailViewModel by viewModels()

    private lateinit var detailImage: ImageView
    private lateinit var detailName: TextView
    private lateinit var detailCategory: TextView
    private lateinit var detailArea: TextView
    private lateinit var detailInstructions: TextView
    private lateinit var showMoreButton: TextView
    private lateinit var watchVideoButton: Button

    // Video window views
    private lateinit var videoContainer: FrameLayout
    private lateinit var videoWebView: WebView
    private lateinit var closeVideoButton: Button

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        detailImage = view.findViewById(R.id.detailImage)
        detailName = view.findViewById(R.id.detailName)
        detailCategory = view.findViewById(R.id.detailCategory)
        detailArea = view.findViewById(R.id.detailArea)
        detailInstructions = view.findViewById(R.id.detailInstructions)
        showMoreButton = view.findViewById(R.id.showMoreButton)
        watchVideoButton = view.findViewById(R.id.watchVideoButton)

        videoContainer = view.findViewById(R.id.videoContainer)
        videoWebView = view.findViewById(R.id.videoWebView)
        closeVideoButton = view.findViewById(R.id.closeVideoButton)

        closeVideoButton.setOnClickListener {
            hideVideo()
        }

        val mealId = arguments?.getString("mealId")

        if (mealId == null) {
            Toast.makeText(requireContext(), "Recipe not found", Toast.LENGTH_SHORT).show()
            return
        }

        observeViewModel()

        viewModel.getMealDetails(mealId)
    }

    private fun observeViewModel() {

        viewModel.selectedMeal.observe(viewLifecycleOwner) { meal ->
            meal?.let { displayMeal(it) }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayMeal(meal: Meal) {

        detailName.text = meal.strMeal
        detailCategory.text = meal.strCategory
        detailArea.text = meal.strArea
        detailInstructions.text = meal.strInstructions ?: "No instructions available"

        Glide.with(requireContext())
            .load(meal.strMealThumb)
            .into(detailImage)

        setupExpand()
        setupVideo(meal)
    }

    private fun setupExpand() {

        detailInstructions.maxLines = 8

        showMoreButton.setOnClickListener {
            detailInstructions.maxLines = Int.MAX_VALUE
            showMoreButton.visibility = View.GONE
        }
    }

    private fun setupVideo(meal: Meal) {

        if (meal.strYoutube.isNullOrBlank()) {
            watchVideoButton.visibility = View.GONE
            return
        }

        watchVideoButton.visibility = View.VISIBLE

        watchVideoButton.setOnClickListener {
            showVideo(meal.strYoutube!!)
        }
    }

    // بتفتح نافذة الفيديو جوه الشاشة (Overlay) بدل ما تفتح يوتيوب برا التطبيق
    private fun showVideo(youtubeUrl: String) {

        val embedUrl = toYoutubeEmbedUrl(youtubeUrl)

        if (embedUrl == null) {
            Toast.makeText(requireContext(), "Invalid video link", Toast.LENGTH_SHORT).show()
            return
        }

        videoContainer.visibility = View.VISIBLE

        videoWebView.settings.javaScriptEnabled = true
        videoWebView.settings.domStorageEnabled = true
        videoWebView.settings.mediaPlaybackRequiresUserGesture = false
        videoWebView.settings.loadWithOverviewMode = true
        videoWebView.settings.useWideViewPort = true

        // مهم: يخلي يوتيوب يتعامل مع الـ WebView زي متصفح Chrome عادي
        videoWebView.settings.userAgentString =
            "Mozilla/5.0 (Linux; Android 13; Pixel 6) AppleWebKit/537.36 " +
                    "(KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36"

        CookieManager.getInstance().setAcceptThirdPartyCookies(videoWebView, true)

        val html = """
        <html>
        <body style="margin:0;padding:0;">
        <iframe width="100%" height="100%"
            src="$embedUrl?autoplay=1&playsinline=1&origin=https://www.youtube.com"
            frameborder="0"
            referrerpolicy="strict-origin-when-cross-origin"
            allow="autoplay; encrypted-media"
            allowfullscreen></iframe>
        </body>
        </html>
    """.trimIndent()

        videoWebView.loadDataWithBaseURL(
            "https://www.youtube-nocookie.com",
            html,
            "text/html",
            "utf-8",
            null
        )
    }

    private fun hideVideo() {
        videoWebView.loadUrl("about:blank")
        videoContainer.visibility = View.GONE
    }

    // بتاخد أي شكل لينك يوتيوب وترجع رابط embed، أو null لو الفورمات مش معروف
    private fun toYoutubeEmbedUrl(url: String): String? {
        val videoId = when {
            url.contains("youtu.be/") ->
                url.substringAfter("youtu.be/").substringBefore("?").substringBefore("&")

            url.contains("watch?v=") ->
                url.substringAfter("watch?v=").substringBefore("&")

            url.contains("/embed/") ->
                url.substringAfter("/embed/").substringBefore("?")

            else -> null
        }

        return if (videoId.isNullOrBlank()) null
        else "https://www.youtube-nocookie.com/embed/$videoId"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        videoWebView.loadUrl("about:blank")
    }
}