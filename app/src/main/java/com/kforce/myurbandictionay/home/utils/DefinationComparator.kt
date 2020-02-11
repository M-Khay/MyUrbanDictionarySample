package com.kforce.myurbandictionay.home.utils

import com.kforce.myurbandictionay.home.repository.model.api.model.DefinationModel

class DefinationComparator {
    class ThumbsUpComparator {
        companion object : Comparator<DefinationModel> {
            override fun compare(a: DefinationModel, b: DefinationModel): Int = when {
                a.thumbsUp != b.thumbsUp -> a.thumbsUp - b.thumbsUp
                else -> 0
            }
        }
    }

    class ThumbsDownComparator {
        companion object : Comparator<DefinationModel> {
            override fun compare(a: DefinationModel, b: DefinationModel): Int = when {
                a.thumbsDown != b.thumbsDown -> a.thumbsDown - b.thumbsDown
                else -> 0
            }
        }
    }
}
