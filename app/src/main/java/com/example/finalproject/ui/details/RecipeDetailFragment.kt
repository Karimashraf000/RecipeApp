package com.example.finalproject.ui.details

private fun showVideo(videoUrl: String) {

    val videoContainer =
        requireView().findViewById<android.view.View>(
            R.id.videoContainer
        )

    val videoWebView =
        requireView().findViewById<android.webkit.WebView>(
            R.id.videoWebView
        )

    val closeButton =
        requireView().findViewById<android.widget.Button>(
            R.id.closeVideoButton
        )

    videoContainer.visibility =
        android.view.View.VISIBLE

    videoWebView.settings.javaScriptEnabled = true

    videoWebView.loadUrl(videoUrl)

    closeButton.setOnClickListener {

        videoWebView.loadUrl("about:blank")

        videoContainer.visibility =
            android.view.View.GONE
    }
}
videoButton.setOnClickListener {

    val youtubeUrl = meal.strYoutube

    if (!youtubeUrl.isNullOrEmpty()) {

        showVideo(youtubeUrl)

    } else {

        Toast.makeText(
            requireContext(),
            "Video is not available",
            Toast.LENGTH_SHORT
        ).show()
    }
}