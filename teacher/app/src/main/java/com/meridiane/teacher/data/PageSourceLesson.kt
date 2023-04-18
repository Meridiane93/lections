package com.meridiane.teacher.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.meridiane.teacher.domain.models.Lesson

class PageSourceLesson(
    private val repository: DemoRepository,
    private val data: Boolean?,
) : PagingSource<Int, Lesson>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Lesson> {

        return try {
            val pageNumber = params.key ?: 0
            val lesson = repository.getLesson(data)
            val prevKey = if (pageNumber > 0) pageNumber - 1 else null
            val nextKey = if (lesson.isNotEmpty()) pageNumber + 1 else null

            LoadResult.Page(data = lesson, prevKey = prevKey, nextKey = nextKey)
        } catch (e: Throwable) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Lesson>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

}
